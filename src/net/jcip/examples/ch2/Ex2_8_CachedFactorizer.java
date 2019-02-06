package net.jcip.examples.ch2;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * CachedFactorizer
 * <p/>
 * Servlet that caches its last request and result
 *
 * @author Brian Goetz and Tim Peierls
 */

// 전체 메소드를 동기화 했지만, 
// synchronized 코드블록은 2개 - 캐시에 결과를 갖고잇는지 검사파트, 캐시된 입력값과 결과를 새로운 값으로 변경파트

@ThreadSafe
public class Ex2_8_CachedFactorizer extends GenericServlet implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    // 캐시가 사용된 횟수 체크
    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        
        //캐시에 결과를 갖고잇는지 검사파트
        synchronized (this) {
        	// 캐시가 사용된 횟수 체크
        	++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        
        //캐시된 입력값과 결과를 새로운 값으로 변경파트
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
