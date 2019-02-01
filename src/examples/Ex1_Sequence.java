package examples;

import net.jcip.annotations.*;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

// 쓰레드 안전하지않은 일련번호 생성 클래스
//@NotThreadSafe
//public class UnsafeSequence {
//    private int value;
//
//    /**
//     * Returns a unique value.
//     */
//    public int getNext() {
//        return value++;
//    }
//}

//쓰레드 안전한 일련번호 생성 클래스
@ThreadSafe
public class Ex1_Sequence {
    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}

