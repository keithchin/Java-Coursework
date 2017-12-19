import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Jonas Chan, Way Chean Tan
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     * 
     *          <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     * 
     *          <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size
    @Test
    public final void testAdd1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "apple", "banana", "cherry");
        m.add("apple");
        m.add("banana");
        m.add("cherry");

        assertEquals(m.size(), 3);
        assertEquals(mExpected.size(), 3);
        assertEquals(mExpected, m);

    }

    @Test
    public final void testAdd2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                " ");
        m.add(" ");

        assertEquals(m.size(), 1);
        assertEquals(mExpected.size(), 1);
        assertEquals(mExpected, m);

    }

    @Test
    public final void testAdd3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "apple", " ", "123", "BANANA");
        m.add("apple");
        m.add(" ");
        m.add("123");
        m.add("BANANA");

        assertEquals(m.size(), 4);
        assertEquals(mExpected.size(), 4);
        assertEquals(mExpected, m);

    }

    @Test
    public final void testchangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        boolean test = m.isInInsertionMode();
        boolean ref = mExpected.isInInsertionMode();

        assertTrue(!test);
        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testchangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "d",
                "z", "a", "b", "s", "d", "s");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "d", "z", "a", "b", "s", "d", "s");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        boolean test = m.isInInsertionMode();
        boolean ref = mExpected.isInInsertionMode();

        assertTrue(!test);
        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        boolean test = m.isInInsertionMode();
        boolean ref = mExpected.isInInsertionMode();

        assertTrue(test);
        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInInsertionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        boolean test = m.isInInsertionMode();
        boolean ref = mExpected.isInInsertionMode();

        assertTrue(!test);
        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "apple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "apple");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, " ");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                " ");

        mExpected.removeFirst();
        m.removeFirst();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "apple", "BANANA", "!@#$%^&*()_+");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "apple", "BANANA", "!@#$%^&*()_+");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    //Unsorted input
    @Test
    public final void testRemoveFirst5() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "d",
                "z", "a", "b", "s");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "d", "z", "a", "b", "s");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    //Two same input
    @Test
    public final void testRemoveFirst7() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "d",
                "z", "a", "b", "s", "d", "s");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "d", "z", "a", "b", "s", "d", "s");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();
        String test2 = m.removeFirst();
        String ref2 = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(ref2, test2);
        assertEquals(mExpected, m);
    }

    //many same input
    @Test
    public final void testRemoveFirst8() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "d",
                "z", "a", "b", "s", "d", "s", "z", "z", "a", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "d", "z", "a", "b", "s", "d", "s", "z", "z", "a", "b");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();
        String test2 = m.removeFirst();
        String ref2 = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(ref2, test2);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(m.size(), 0);
        assertEquals(mExpected.size(), 0);
        assertEquals(mExpected.size(), m.size());
    }

    @Test
    public final void testSize1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, " ",
                "BANANA", "!@#$%^&*()_+");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "BANANA", "!@#$%^&*()_+", " ");

        assertEquals(mExpected.size(), 3);
        assertEquals(m.size(), 3);
        assertEquals(mExpected.size(), m.size());
    }

    @Test
    public final void testSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        for (int i = 0; i < 99999; i++) {
            mExpected.add("apple");
            m.add("apple");
        }

        assertEquals(mExpected, m);
        assertEquals(mExpected.size(), m.size());
    }

    @Test
    public final void testOrder1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "3");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "3");

        assertEquals(mExpected, m);
        assertEquals(mExpected.order(), m.order());
    }

    @Test
    public final void testOrder2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                " ", "1", "2", "3", "apple", "banana");

        m.add(" ");
        m.add("1");
        m.add("2");
        m.add("3");
        m.add("apple");
        m.add("banana");

        assertEquals(mExpected, m);
        assertEquals(mExpected.order(), m.order());
    }

    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(mExpected.order(), m.order());
    }

}
