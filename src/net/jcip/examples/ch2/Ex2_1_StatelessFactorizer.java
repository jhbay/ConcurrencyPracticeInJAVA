package net.jcip.examples.ch2;

import java.math.BigInteger;
import javax.servlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jcip.annotations.*;

/**
 * StatelessFactorizer
 *
 * A stateless servlet
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Ex2_1_StatelessFactorizer extends GenericServlet implements Servlet {
	final Logger logger = LoggerFactory.getLogger(Ex2_1_StatelessFactorizer.class);

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
//    	System.out.println("Hello");
//        logger.debug(String.format("resp: %s \n factors: %s", resp, factors.toString()));
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
