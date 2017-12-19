import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Jonas Chan, Justin Zhang
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
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert!map.hasKey(args[i]) : ""
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
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert!map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    @Test
    public final void testDefaultConstructor() {

        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> controlMap = this.createFromArgsRef();

        assertEquals(testMap.size(), 0);
        assertEquals(testMap.toString(), "{}");
        assertEquals(testMap, controlMap);
    }

    @Test
    public final void testAddEmpty() {

        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> controlMap = this.createFromArgsRef("One", "1");

        testMap.add("One", "1");

        assertEquals(testMap.size(), 1);
        assertEquals(testMap, controlMap);
    }

    /**
     * add() test
     */
    @Test
    public final void AddTest() {
        Map<String, String> Test = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> Expected = this.createFromArgsRef("apple", "banana",
                "coconut", "durian");
        assertEquals(Expected.size(), Test.size());
    }

    /**
     * add() test
     */
    @Test
    public final void AddExpected() {
        Map<String, String> Test = this.createFromArgsTest();
        Test.add("apple", "banana");
        Map<String, String> Expected = this.createFromArgsRef("apple",
                "banana");
        assertEquals(Test, Expected);
    }

    /**
     * add() test
     */
    @Test
    public final void AddTest3() {
        Map<String, String> Test = this.createFromArgsTest("apple", "banana");
        Test.add("coconut", "durian");
        Map<String, String> Expected = this.createFromArgsRef("apple", "banana",
                "coconut", "durian");
        assertEquals(Test, Expected);
    }

    /**
     * add() test - large number
     */
    @Test
    public final void AddTest4() {
        Map<String, String> Test = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> Expected = this.createFromArgsRef("apple", "banana",
                "coconut", "durian");

        for (int i = 0; i < 99999; i++) {
            Test.add(Integer.toString(i), Integer.toString(i));
            Expected.add(Integer.toString(i), Integer.toString(i));
        }

        assertEquals(Expected.size(), Test.size());
    }

    @Test
    public final void testRemoveLeavingEmpty() {

        Map<String, String> testMap = this.createFromArgsTest("One", "1");
        Map<String, String> controlMap = this.createFromArgsRef();

        Pair<String, String> result = testMap.remove("One");

        assertEquals(testMap.size(), 0);
        assertEquals("One", result.key());
        assertEquals("1", result.value());
        assertEquals(testMap, controlMap);
    }

    @Test
    public final void RemoveTest() {
        Map<String, String> m1 = this.createFromArgsTest("apple", "banana", "",
                "123", "LOL", "coconut");
        Map<String, String> mExpected = this.createFromArgsRef("apple",
                "banana", "", "123", "LOL", "coconut");
        Pair<String, String> temp1 = m1.remove("LOL");
        Pair<String, String> temp2 = mExpected.remove("LOL");
        assertEquals(temp1, temp2);
        assertEquals(mExpected, m1);
    }

    @Test
    public final void RemoveTest2() {
        Map<String, String> Test = this.createFromArgsTest("KeyA", "ValueA",
                "KeyB", "ValueB");
        Map<String, String> Expected = this.createFromArgsRef("KeyA", "ValueA",
                "KeyB", "ValueB");

        Pair<String, String> myPair1 = Test.remove("KeyA");
        Pair<String, String> refPair1 = Expected.remove("KeyA");

        Pair<String, String> myPair2 = Test.remove("KeyB");
        Pair<String, String> refPair2 = Expected.remove("KeyB");

        assertEquals(Expected.size(), Test.size());
        assertEquals(refPair1, myPair1);
        assertEquals(refPair2, myPair2);

    }

    @Test
    public final void testRemoveAnyLeavingEmpty() {

        Map<String, String> testMap = this.createFromArgsTest("One", "1");
        Map<String, String> controlMap = this.createFromArgsRef();

        Pair<String, String> result = testMap.removeAny();

        assertEquals(testMap.size(), 0);
        assertEquals("One", result.key());
        assertEquals("1", result.value());
        assertEquals(testMap, controlMap);
    }

    @Test
    public final void removeAnyTest1() {
        Map<String, String> Test = this.createFromArgsTest("myKeyA", "myValueA",
                "myKeyB", "myValueB");
        Map<String, String> Expected = this.createFromArgsRef("refKeyA",
                "refValueA", "refkeyB", "refValueB");

        Test.removeAny();
        Expected.removeAny();
        assertEquals(Expected.size(), Test.size());
    }

    @Test
    public final void removeAnyTest2() {
        Map<String, String> Test = this.createFromArgsTest("A", "", "B",
                "banana", "C", "coconut", "D", " durian");
        Map<String, String> Expected = this.createFromArgsRef("A", "", "B",
                "banana", "C", "coconut", "D", " durian");
        Map.Pair<String, String> temp1 = Test.removeAny();
        Map.Pair<String, String> temp2 = Expected.removeAny();
        assertEquals(Test, Expected);
        assertEquals(temp1, temp2);

    }

    @Test
    public final void removeAnyExpected() {
        Map<String, String> Test = this.createFromArgsTest("myKeyA", "myValueA",
                "myKeyB", "myValueB");
        Map<String, String> Expected = this.createFromArgsRef("myKeyA",
                "myValueA", "myKeyB", "myValueB");

        Pair<String, String> temp = Test.removeAny();
        String key = temp.key();
        assertTrue(Expected.hasKey(key));
    }

    @Test
    public final void valueTest() {
        Map<String, String> m1 = this.createFromArgsTest("K", "V");
        Map<String, String> mExpected = this.createFromArgsRef("K", "V");
        String key = "K";
        String v1 = m1.value(key);
        String v2 = mExpected.value(key);
        assertEquals(v1, v2);
        assertEquals(mExpected, m1);
    }

    @Test
    public final void valueExpected() {
        Map<String, String> Test = this.createFromArgsTest("a", "b", "c", "b");
        Map<String, String> Expected = this.createFromArgsRef("a", "b", "c",
                "b");
        assertEquals(Expected.value("a"), Test.value("c"));
    }

    @Test
    public final void hasKeyEmptyTest() {
        Map<String, String> s1 = this.createFromArgsTest();
        Map<String, String> s2 = this.createFromArgsRef();
        boolean hasKey1 = s1.hasKey("a");
        boolean hasKey2 = s2.hasKey("a");
        assertTrue(!hasKey1);
        assertEquals(hasKey2, hasKey1);
    }

    @Test
    public final void testHasKeyTrue() {

        Map<String, String> testMap = this.createFromArgsTest("One", "1", "Two",
                "2", "Three", "3", "Four", "4");
        Map<String, String> controlMap = this.createFromArgsRef("One", "1",
                "Two", "2", "Three", "3", "Four", "4");

        boolean result = testMap.hasKey("Three");

        assertEquals(testMap.size(), 4);
        assertEquals(testMap, controlMap);
        assertTrue(result);
    }

    @Test
    public final void testHasKeyFalse() {

        Map<String, String> testMap = this.createFromArgsTest("One", "1", "Two",
                "2", "Three", "3", "Four", "4");
        Map<String, String> controlMap = this.createFromArgsRef("One", "1",
                "Two", "2", "Three", "3", "Four", "4");

        boolean result = testMap.hasKey("Threee");

        assertEquals(testMap.size(), 4);
        assertEquals(testMap, controlMap);
        assertTrue(!result);
    }

    @Test
    public final void sizeTest() {
        Map<String, String> Test = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> Expected = this.createFromArgsRef("apple", "banana",
                "coconut", "durian");

        assertEquals(Test.size(), Expected.size());

    }

    @Test
    public final void sizeExpected() {
        Map<String, String> Test = this.createFromArgsTest("apple", "banana",
                "coconut", "durian");
        Map<String, String> Expected = this.createFromArgsRef("apple", "banana",
                "coconut", "durian");
        assertEquals(Test.size(), Expected.size());
        assertEquals(Test, Expected);

    }

    @Test
    public final void sizeTestEmpty() {
        Map<String, String> Test = this.createFromArgsTest();
        Map<String, String> Expected = this.createFromArgsRef();
        assertEquals(Test, Expected);
        assertEquals(Test.size(), Expected.size());
        assertEquals(Test.size(), 0);
        assertEquals(Expected.size(), 0);

    }

}
