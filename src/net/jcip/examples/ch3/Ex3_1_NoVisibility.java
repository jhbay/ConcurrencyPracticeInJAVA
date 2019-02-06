package net.jcip.examples.ch3;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class Ex3_1_NoVisibility {
	// static 변수 공유.
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
    	// BAD Code
    	// ready가 true일때, number 변수값이 출력되지만, 동기화가 되지않은 사례.
    	// 만약 ready의 값을 읽지 못한다면? 무한루프. ex) rendering - ready를 먼저 읽고 number값이 null상태.
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}



// thread 실험.

//public class Ex3_1_NoVisibility {
//	private static boolean ready;
//	private static int number;
//	
//	private static class ReaderThread extends Thread {
//		// BAD Code
//		// ready가 true일때, number 변수값이 출력되지만, 동기화가 되지않은 사례.
//		// 만약 ready의 값을 읽지 못한다면? 무한루프. ex) rendering - ready를 먼저 읽고 number값이 null상태.
//	
//	    Ex3_4_CountingSheep ex34 = new Ex3_4_CountingSheep();
//		public void run() {
//	        ex34.setAsleep(true);
//	        ex34.countSomeSheep();
//	    }
//	}
//	
//	public static void main(String[] args) {
//		Thread th01 = new ReaderThread();
//		Thread th02 = new ReaderThread();
//	    for(int i =0; i<3; i++){
//	    	th01.run();
//	    	th02.run();
//	    }
//	    
//	}
//}
