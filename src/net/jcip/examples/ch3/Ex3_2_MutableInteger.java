package net.jcip.examples.ch3;

import net.jcip.annotations.*;

/**
 * MutableInteger
 * <p/>
 * Non-thread-safe mutable integer holder
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class Ex3_2_MutableInteger {
	// 동기화되지 않은 상태로 정수값을 보관하는 클래스
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
    
}








