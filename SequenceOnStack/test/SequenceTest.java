import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    @Test
    public final void testAddCaseFront() {
        Sequence<String> q = this.createFromArgsTest("a", "b");
        Sequence<String> qExpected = this.createFromArgsRef("c", "a", "b");

        q.add(0, "c");

        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddCaseBoundary() {
        Sequence<String> q = this.createFromArgsTest("a", "b");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b", "c");

        q.add(q.length(), "c");

        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddEmpty() {
        Sequence<String> q = this.createFromArgsTest("a", "b", "c", "d");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b", "c", "",
                "d");

        q.add(3, "");

        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveCaseFront() {
        Sequence<String> q = this.createFromArgsTest("c", "a", "b");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b");

        String a = q.remove(0);
        String exp = "c";

        assertEquals(qExpected, q);
        assertEquals(exp, a);
    }

    @Test
    public final void testRemoveCaseBoundary() {
        Sequence<String> q = this.createFromArgsTest("a", "b", "c");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b");

        String a = q.remove(q.length() - 1);
        String exp = "c";

        assertEquals(qExpected, q);
        assertEquals(exp, a);
    }

    @Test
    public final void testRemoveEmpty() {
        Sequence<String> q = this.createFromArgsTest("a", "b", "c", "", "d");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b", "c", "d");

        String a = q.remove(3);
        String exp = "";

        assertEquals(qExpected, q);
        assertEquals(a, exp);
    }

    @Test
    public final void testEmptyLength() {
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();

        int leng = q.length();

        assertEquals(qExpected, q);
        assertEquals(0, leng);
    }

    @Test
    public final void testLength() {
        Sequence<String> q = this.createFromArgsTest("a", "b", "c", "", "d");
        Sequence<String> qExpected = this.createFromArgsRef("a", "b", "c", "",
                "d");

        int leng = q.length();

        assertEquals(qExpected, q);
        assertEquals(5, leng);
    }

}
