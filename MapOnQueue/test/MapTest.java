import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    @Test
    public final void testAddCase() {
        Map<String, String> a = this.constructorTest();
        Map<String, String> b = this.constructorRef();

        assertEquals(a.size(), b.size());
    }

    @Test
    public final void mapAddTest01() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> b = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        assertEquals(b, a);
    }

    @Test
    public final void mapAddTest02() {
        Map<String, String> b = this.createFromArgsTest("key1", "sameValue",
                "key2", "sameValue");
        Map<String, String> a = this.createFromArgsRef("key1", "sameValue",
                "key2", "sameValue");

        assertEquals(b, a);
    }

    @Test
    public final void mapRemoveTest01() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> b = this.createFromArgsRef("key2", "value2");

        Pair<String, String> c = a.remove("key1");

        String str = "value1";
        assertEquals(str, c.value());
        assertEquals(b, a);
    }

    @Test
    public final void mapRemoveTest02() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> b = this.createFromArgsRef("key1", "value1");

        Pair<String, String> c = a.remove("key2");

        String str = "value2";
        assertEquals(str, c.value());
        assertEquals(b, a);
    }

    @Test
    public final void mapRemoveTest03() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> b = this.createFromArgsRef("key2", "value2");

        Pair<String, String> c = a.remove("key1");

        String str = "value1";
        assertEquals(str, c.value());
        assertEquals(b, a);
    }

    @Test
    public final void mapRemoveAny() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1");
        Map<String, String> b = this.createFromArgsRef();

        Pair<String, String> c = a.remove("key1");

        String str = "value1";
        assertEquals(str, c.value());
        assertEquals(b, a);
    }

    @Test
    public final void valueTest1() {
        Map<String, String> a = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> b = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        assertEquals(b.value("key1"), a.value("key1"));
        assertEquals(b, a);
    }

    /**
     * test2 value method
     */
    @Test
    public final void valueTest2() {
        Map<String, String> a = this.createFromArgsTest("a", "b", "c", "b");
        Map<String, String> b = this.createFromArgsRef("a", "b", "c", "b");
        assertEquals(b.value("a"), a.value("c"));
    }

    @Test
    public final void hasKeyTest1() {
        Map<String, String> a = this.createFromArgsTest("a", "b", "c", "d");

        boolean ans = a.hasKey("c");
        boolean wans = a.hasKey("f");

        assertTrue(ans);
        assertTrue(!wans);
    }

    @Test
    public final void hasKeyTest2() {
        Map<String, String> a = this.createFromArgsTest();

        boolean ans = a.hasKey("c");

        assertTrue(!ans);
    }

    @Test
    public final void sizeTest1() {
        Map<String, String> a = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> b = this
                .createFromArgsRef("d", "e", "fsadsad", "g");

        assertEquals(b.size(), a.size());
    }

    @Test
    public final void sizeTest2() {
        Map<String, String> a = this.createFromArgsTest("a", "b", "c", "d",
                "e", "f");
        Map<String, String> b = this.createFromArgsRef("a", "b", "cde", "f");

        assertTrue(b.size() != a.size());
    }
}
