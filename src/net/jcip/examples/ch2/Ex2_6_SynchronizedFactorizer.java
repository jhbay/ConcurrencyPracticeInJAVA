package net.jcip.examples.ch2;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * SynchronizedFactorizer
 *
 * Servlet that caches last result, but with unnacceptably poor concurrency
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Ex2_6_SynchronizedFactorizer extends GenericServlet implements Servlet {
    
	// 락으로부터 보호됨.
	@GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;

    // BAD Code.
    // synchronized, 한번에 한 쓰레드만 실행할수있게 만듬.
    // 하지만, 너무 극단적이라 인수분해 서블릿을 여러 클라이언트가 동시에 사용불가능. 응답성이 떨어짐
    public synchronized void service(ServletRequest req,
                                     ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber))
            encodeIntoResponse(resp, lastFactors);
        else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
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
        return new BigInteger[] { i };
    }
}

