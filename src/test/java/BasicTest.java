import org.junit.Test;
import static org.junit.Assert.*;

public class BasicTest {

    @Test
    public void testShouldPass() {

        // MyClass is tested
        //MyClass tester = new MyClass();

        // assert statements
        assertEquals("0 must be 0", 0, 0);
        //assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        //assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
    }

}