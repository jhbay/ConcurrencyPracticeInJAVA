package net.jcip.examples.ch3;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * VolatileCachedFactorizer
 * <p/>
 * Caching the last result using a volatile reference to an immutable holder object
 *
 * @author Brian Goetz and Tim Peierls
 */


// 최신값을 불변 객체에 넣어 volatile변수에 보관함으로, cache에 보관된 변경된 값을 즉시 사용할수 있다. 
//Ex3_12_OneValueCache 클래스가 불변이면서 cache변수를 사용하면, 정확히 한번씩만 사용되기에 캐시연산에는 혼동이 없다. -> 스레드 안전.

@ThreadSafe
public class Ex3_13_VolatileCachedFactorizer extends GenericServlet implements Servlet {
    private volatile Ex3_12_OneValueCache cache = new Ex3_12_OneValueCache(null, null);

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new Ex3_12_OneValueCache(i, factors);
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
