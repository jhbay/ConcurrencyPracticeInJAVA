package net.jcip.examples.ch3;

import java.math.BigInteger;
import java.util.*;

import net.jcip.annotations.*;

/**
 * OneValueCache
 * <p/>
 * Immutable holder for caching a number and its factors
 *
 * @author Brian Goetz and Tim Peierls
 */

// 입력값과 인수분해된 결과를 묶는 불변객체. 
// Ex2_2_UnsafeCountingFactorizer 참고.

// 
@Immutable
public class Ex3_12_OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public Ex3_12_OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}