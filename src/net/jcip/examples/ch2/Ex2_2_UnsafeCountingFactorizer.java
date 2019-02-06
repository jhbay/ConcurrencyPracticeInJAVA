package net.jcip.examples.ch2;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * UnsafeCountingFactorizer
 *
 * Servlet that counts requests without the necessary synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

// BAD Code
// BAD Code

@NotThreadSafe
public class Ex2_2_UnsafeCountingFactorizer extends GenericServlet implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        
        //BAD Code. 동기화의 구문이 없음.
        ++count;
        // ++의 동작순서: read count, count +1, write count.
        // 만약 count 가 객체식별자UID였다면, 데이터 무결성의 이슈가 터짐. 
        
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
