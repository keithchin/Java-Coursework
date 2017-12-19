import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}.
 *
 * @author
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (int i = args.length - 1; i >= 0; i--) {
            stack.push(args[i]);
        }
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (int i = args.length - 1; i >= 0; i--) {
            stack.push(args[i]);
        }
        return stack;
    }

    @Test
    public final void testDefaultConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> test = this.constructorTest();
        Stack<String> ref = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
    }

    @Test
    public final void testPushEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest();
        Stack<String> ref = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        test.push("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
    }

    @Test
    public final void testPushMultipleToEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest();
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d", "e");
        /*
         * Call method under test
         */
        test.push("e");
        test.push("d");
        test.push("c");
        test.push("b");
        test.push("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
    }

    @Test
    public final void pushToNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("d");
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d");
        /*
         * Call method under test
         */
        test.push("c");
        test.push("b");
        test.push("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
    }

    @Test
    public final void testPopToEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a");
        Stack<String> ref = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        String a = test.pop();
        String b = ref.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(b, a);
    }

    @Test
    public final void testPopMultipleTimes() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a", "b", "c", "d", "e");
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d", "e");
        /*
         * Call method under test
         */
        String a = test.pop();
        String a1 = ref.pop();
        String b = test.pop();
        String b1 = ref.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(a1, a);
        assertEquals(b1, b);
    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest();
        Stack<String> ref = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = test.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(ref, test);
    }

    @Test
    public final void testLengthNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a", "b");
        Stack<String> ref = this.createFromArgsRef("a", "b");
        /*
         * Call method under test
         */
        int i = test.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
        assertEquals(ref, test);
    }

    @Test
    public final void testLarge() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a", "b", "c", "d", "e");
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d", "e");
        /*
         * Call method under test
         */
        int i = test.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(5, i);
        assertEquals(ref, test);
    }

    @Test
    public final void testTop() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a", "b", "c", "d", "e");
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d", "e");
        /*
         * Call method under test
         */
        String a = test.top();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", a);
        assertEquals(ref, test);
    }

    @Test
    public final void testReplaceTop() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("a", "b", "c", "d", "e");
        Stack<String> ref = this.createFromArgsRef("a", "b", "c", "d", "e");
        /*
         * Call method under test
         */
        String a = test.replaceTop("abc");
        String b = ref.replaceTop("abc");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", a);
        assertEquals(b, a);
        assertEquals(ref, test);
    }
}
