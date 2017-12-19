import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Jonas Chan
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero
    @Test
    public final void testConstructorDefault() {
        NaturalNumber n3 = this.constructorTest();
        NaturalNumber n1 = this.constructorRef();

        assertEquals(n1, n3);
    }

    //Testing creating naturalnumber from int
    @Test
    public final void testConstructor1() {
        NaturalNumber n3 = this.constructorTest(827);
        NaturalNumber n1 = this.constructorRef(827);

        assertEquals(n1, n3);
    }

    //Testing creating naturalnumber from string
    @Test
    public final void testConstructor2() {
        NaturalNumber n3 = this.constructorTest("2382");
        NaturalNumber n1 = this.constructorRef("2382");

        assertEquals(n1, n3);
    }

    //Testing creating naturalnumber from string which is over max integer value
    @Test
    public final void testConstructor3() {
        NaturalNumber n3 = this.constructorTest("2132983238923");
        NaturalNumber n1 = this.constructorRef("2132983238923");

        assertEquals(n1, n3);
    }

    //Testing creating naturalnumber from natural number
    @Test
    public final void testConstructor4() {
        NaturalNumber a = this.constructorTest("23232");
        NaturalNumber b = this.constructorRef("23232");

        NaturalNumber n3 = this.constructorTest(a);
        NaturalNumber n1 = this.constructorRef(b);

        assertEquals(n1, n3);
    }

    // testing an one digit number times 10 then add 0
    @Test
    public final void testMultiplyBy10test1() {
        NaturalNumber test = this.constructorTest(3);
        NaturalNumber Ref = this.constructorRef(3);

        test.multiplyBy10(0);
        Ref.multiplyBy10(0);
        assertEquals(Ref, test);
    }

    // testing an one digit number times 10 then add 2
    @Test
    public final void testMultiplyBy10test2() {
        NaturalNumber test = this.constructorTest(3);
        NaturalNumber Ref = this.constructorRef(3);

        test.multiplyBy10(2);
        Ref.multiplyBy10(2);
        assertEquals(Ref, test);
    }

    // Double digit, 0 multiplication test
    @Test
    public final void testMultiplyBy10test3() {
        NaturalNumber test = this.constructorTest(25);
        NaturalNumber Ref = this.constructorRef(25);

        test.multiplyBy10(0);
        Ref.multiplyBy10(0);
        assertEquals(Ref, test);
    }

    // Double digit, multiplication by 10 and add single digit test
    @Test
    public final void testMultiplyBy10test4() {
        NaturalNumber test = this.constructorTest(25);
        NaturalNumber Ref = this.constructorRef(25);

        test.multiplyBy10(5);
        Ref.multiplyBy10(5);
        assertEquals(Ref, test);
    }

    //Testing multiplication and add largest integer before 10
    @Test
    public final void testMultiplyBy10test5() {
        NaturalNumber test = this.constructorTest(45);
        NaturalNumber Ref = this.constructorRef(45);

        test.multiplyBy10(9);
        Ref.multiplyBy10(9);
        assertEquals(Ref, test);
    }

    //Max integer value multiplication then add zero
    @Test
    public final void testMultiplyBy10test6() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber Ref = this.constructorRef(Integer.MAX_VALUE);

        test.multiplyBy10(0);
        Ref.multiplyBy10(0);
        assertEquals(Ref, test);
    }

    //Max integer value multiplication then add a single digit
    @Test
    public final void testMultiplyBy10test7() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber Ref = this.constructorRef(Integer.MAX_VALUE);

        test.multiplyBy10(7);
        Ref.multiplyBy10(7);
        assertEquals(Ref, test);
    }

    //Max integer value multiplication then add largest single digit
    @Test
    public final void testMultiplyBy10test8() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber Ref = this.constructorRef(Integer.MAX_VALUE);

        test.multiplyBy10(9);
        Ref.multiplyBy10(9);
        assertEquals(Ref, test);
    }

    // Testing the max integer value +1 times 10 then add zero
    @Test
    public final void testMultiplyBy10test9() {
        String intBoundary = "2147483648";
        NaturalNumber test = this.constructorTest(intBoundary);
        NaturalNumber Ref = this.constructorRef(intBoundary);

        test.multiplyBy10(0);
        Ref.multiplyBy10(0);
        assertEquals(Ref, test);
    }

    // Testing the max integer value +1 times 10 then add a single digit
    @Test
    public final void testMultiplyBy10test10() {
        String intBoundary = "2147483648";
        NaturalNumber test = this.constructorTest(intBoundary);
        NaturalNumber Ref = this.constructorRef(intBoundary);

        test.multiplyBy10(5);
        Ref.multiplyBy10(5);
        assertEquals(Ref, test);
    }

    // Testing the max integer value +1 times 10 then add largest single digit
    @Test
    public final void testMultiplyBy10test11() {
        String intBoundary = "2147483648";
        NaturalNumber test = this.constructorTest(intBoundary);
        NaturalNumber Ref = this.constructorRef(intBoundary);

        test.multiplyBy10(9);
        Ref.multiplyBy10(9);
        assertEquals(Ref, test);
    }

    //One digit division
    @Test
    public final void testDivideBy10test1() {
        NaturalNumber test = this.constructorTest(7);
        NaturalNumber Ref = this.constructorRef(7);
        int testRemainder = test.divideBy10();
        int refRemainder = Ref.divideBy10();

        assertEquals(refRemainder, testRemainder);
        assertEquals(Ref, test);
    }

    //Two digit division
    @Test
    public final void testDivideBy10test2() {
        NaturalNumber test = this.constructorTest(28);
        NaturalNumber Ref = this.constructorRef(28);
        int testRemainder = test.divideBy10();
        int refRemainder = Ref.divideBy10();

        assertEquals(refRemainder, testRemainder);
        assertEquals(Ref, test);
    }

    //Max Integer division
    @Test
    public final void testDivideBy10test3() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber Ref = this.constructorRef(Integer.MAX_VALUE);
        int testRemainder = test.divideBy10();
        int refRemainder = Ref.divideBy10();

        assertEquals(testRemainder, refRemainder);
        assertEquals(Ref, test);
    }

    //Max Integer + 1 division
    @Test
    public final void testDivideBy10test4() {
        String intBoundary = "2147483648";
        NaturalNumber test = this.constructorTest(intBoundary);
        NaturalNumber Ref = this.constructorRef(intBoundary);

        int rem1 = test.divideBy10();
        int rem2 = Ref.divideBy10();
        assertEquals(Ref, test);
        assertEquals(rem2, rem1);
    }

    //Test is zero
    @Test
    public final void testIsZerotest1() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber Ref = this.constructorRef(0);

        boolean testResult = test.isZero();
        boolean refResult = Ref.isZero();

        assertEquals(refResult, testResult);
    }

    //When not = 0 , single digit
    @Test
    public final void testIsZerotest2() {
        NaturalNumber test = this.constructorTest(4);
        NaturalNumber Ref = this.constructorRef(4);

        boolean myResult = test.isZero();
        boolean refResult = Ref.isZero();

        assertEquals(refResult, myResult);
    }

    //Max integer test
    @Test
    public final void testIsZerotest3() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber Ref = this.constructorRef(Integer.MAX_VALUE);

        boolean testResult = test.isZero();
        boolean refResult = Ref.isZero();
        assertEquals(refResult, testResult);
    }

    //Max integer + 1 test
    @Test
    public final void testIsZero_case04() {
        String intBoundary = "2147483648";
        NaturalNumber test = this.constructorTest(intBoundary);
        NaturalNumber Ref = this.constructorRef(intBoundary);

        boolean testResult = test.isZero();
        boolean refResult = Ref.isZero();
        assertEquals(refResult, testResult);
    }

}
