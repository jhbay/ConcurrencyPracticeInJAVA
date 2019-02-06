package net.jcip.examples.ch3;

/**
 * SafeListener
 * <p/>
 * Using a factory method to prevent the this reference from escaping during construction
 *
 * @author Brian Goetz and Tim Peierls
 */

//생성 메소드에서 this 변수가 외부로 유출되지않도록 팩토리 메소드를 사용한다.
public class Ex3_8_SafeListener {
    private final EventListener listener;

    // 생성메서드를 private화 한후,
    private Ex3_8_SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    // public으로 지정된 팩토리 활용해 리스너 리턴..
    public static Ex3_8_SafeListener newInstance(EventSource source) {
        Ex3_8_SafeListener safe = new Ex3_8_SafeListener();
        source.registerListener(safe.listener);
        return safe;
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

