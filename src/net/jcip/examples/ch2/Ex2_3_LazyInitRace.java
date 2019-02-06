package net.jcip.examples.ch2;

import net.jcip.annotations.*;

/**
 * LazyInitRace
 *
 * Race condition in lazy initialization
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class Ex2_3_LazyInitRace {
    private ExpensiveObject instance = null;

    // BAD code. 
    // 만약 2개 쓰레드가 동시 실행. 각자 다른 인스턴스값을 가지게된다. 
    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}

class ExpensiveObject { }

