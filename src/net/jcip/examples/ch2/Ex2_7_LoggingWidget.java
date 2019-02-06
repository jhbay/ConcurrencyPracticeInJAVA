package net.jcip.examples.ch2;

/*public */ class Widget{
	public synchronized void doSomething(){
		//... 
	}
}

// 데드락에 빠질위험성 있음.
// Widget, Ex2_7_LoggingWidget 둘다 synchronized 함수 doSomething()
public class Ex2_7_LoggingWidget extends Widget {
	public synchronized void doSomething(){
		//... 
		System.out.println(toString() + ": calling doSomething");
		
		// 암무적인 락을 호출에서 락을 얻을수 없게되고, 데드락에 빠짐.
		super.doSomething();
	}
	
}
