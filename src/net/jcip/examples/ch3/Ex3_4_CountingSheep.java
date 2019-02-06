package net.jcip.examples.ch3;

/**
 * CountingSheep
 * <p/>
 * Counting sheep
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Ex3_4_CountingSheep {
	// asleep은 반드시 volatile로 선언되야한다.
    volatile boolean asleep;

    void setAsleep(boolean asleep){
    	this.asleep = asleep;
    }
    
    void tryToSleep() {
        while (!asleep)
            countSomeSheep();
    }

    void countSomeSheep() {
        // One, two, three...
    	System.out.println(asleep);
    }
}








