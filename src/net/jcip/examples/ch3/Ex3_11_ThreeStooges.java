package net.jcip.examples.ch3;

import java.util.*;

import net.jcip.annotations.*;

/**
 * ThreeStooges
 * <p/>
 * Immutable class built out of mutable underlying objects,
 * demonstration of candidate for lock elision
 *
 * @author Brian Goetz and Tim Peierls
 */

// 일반 객체를 사용한, 불변 객체 구성.
@Immutable
 public final class Ex3_11_ThreeStooges {
//	조건) 객체 생성 후, 객체 상태를 변경할수 없다.
//	내부의 모든 변수는 final로 설정되어 있어야한다.
//	적잘한 방법으로 생성되야 한다, 예컨대, this 변수에 대한 참조가 외부로 유출되지 말아야한다.

//	Set 변수의 수정방법이 없다.
//	객체의 모든 상태는 final변수를 통해 사용할수밖에 없다.
//	생성 메서드에서 this 변수에 대한 참조가 외부로 유출되지 않는다. 
	
	private final Set<String> stooges = new HashSet<String>();

    public Ex3_11_ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}