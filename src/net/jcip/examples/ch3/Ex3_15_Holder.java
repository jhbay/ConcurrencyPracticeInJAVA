package net.jcip.examples.ch3;

/**
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Ex3_15_Holder {
    private int n;

    public Ex3_15_Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }
    
    // 실험방법을 모르겟다...
//    public static void main(String[] args){
//    	Ex3_15_Holder ho = new Ex3_15_Holder(3);
//    	ho.assertSanity();
//    }
    
}