package net.jcip.examples.ch3;

import javax.xml.ws.Holder;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 *
 * @author Brian Goetz and Tim Peierls
 */

// BAD Code. 동기화 하지않아, 안전하지 않은 객체 공개법. 
public class Ex3_14_StuffIntoPublic {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}