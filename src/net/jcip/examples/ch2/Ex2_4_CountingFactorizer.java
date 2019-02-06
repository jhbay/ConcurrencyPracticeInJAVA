package net.jcip.examples.ch2;

import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * CountingFactorizer
 *
 * Servlet that counts requests using AtomicLong
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Ex2_4_CountingFactorizer extends GenericServlet implements Servlet {
    // 단일 연산화
	private final AtomicLong count = new AtomicLong(0);

    public long getCount() { 
    	// 단일 연산화
    	return count.get(); 
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
    
        // 단일 연산화
        // concurrent.atomic 패키지 - 숫자, 객체 참조값에 단일 연산으로 변경하는 단일 연산변수 클래스.
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {return null; }
    BigInteger[] factor(BigInteger i) { return null; }
}
