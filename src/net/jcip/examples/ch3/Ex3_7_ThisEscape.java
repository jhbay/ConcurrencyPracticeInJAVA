package net.jcip.examples.ch3;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Ex3_7_ThisEscape {
    public Ex3_7_ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            // BAD Code.
        	// 내부 클래스의 인스턴스EventListener가 부모 클래스Ex3_7_ThisEscape에 대한 참조.
        	// EventListener가 공개되면 Ex3_7_ThisEscape 클래스도 외부공개된다.
        	public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}

