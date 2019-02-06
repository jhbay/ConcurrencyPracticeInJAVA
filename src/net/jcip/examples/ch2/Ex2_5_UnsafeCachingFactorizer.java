package net.jcip.examples.ch2;

import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * UnsafeCachingFactorizer
 *
 * Servlet that attempts to cache its last result without adequate atomicity
 *
 * @author Brian Goetz and Tim Peierls
 */

// BAD Code.
// 단일연산을 적절히 사용하지 못한 상태에서 결과값을 캐싱하려는 서블릿.
@NotThreadSafe
public class Ex2_5_UnsafeCachingFactorizer extends GenericServlet implements Servlet {
    
	// 캐시 입력값저장
	private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest req, ServletResponse resp) {
    	// 결과값 추출.
    	BigInteger i = extractFromRequest(req);
     // 캐시 입력값 대조 후, 결과값 삽입.
    	//  여러개의 변수가 불변조건 i.equals(lastNumber.get()) 한개로 구성되면, 변수들이 독립적이지 않음. 
        // 타이밍이 좋지않을시, lastNumber와 lastFactors을 동시에 갱신불가.
    	if (i.equals(lastNumber.get()))
            encodeIntoResponse(resp, lastFactors.get());
        else {
            BigInteger[] factors = factor(i);
         
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
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

