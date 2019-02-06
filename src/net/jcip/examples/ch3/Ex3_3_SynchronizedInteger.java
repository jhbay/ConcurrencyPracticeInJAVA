package net.jcip.examples.ch3;

import net.jcip.annotations.*;

/**
 * SynchronizedInteger
 * <p/>
 * Thread-safe mutable integer holder
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Ex3_3_SynchronizedInteger {
    // 동기화된 상태로 정수값을 보관하는 클래스.
	@GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
