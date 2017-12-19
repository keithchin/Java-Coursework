import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @author Jonas Chan
 *
 * @convention
 *
 *             <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 *             </pre>
 *
 * @correspondence
 *
 *                 <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 *                 </pre>
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        // TODO - fill in body
        this.rep = "";

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {

        // TODO - fill in body
        this.createNewRep();

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        // TODO - fill in body
        this.createNewRep();
        if (i != 0) {
            this.rep = Integer.toString(i);
        }

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        // TODO - fill in body
        this.createNewRep();
        if (!s.equals("0")) {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        // TODO - fill in body
        this.createNewRep();
        NaturalNumber a = n;

        if (a.toString().equals("0")) {
            this.rep = "";
        } else {
            this.rep = a.toString();
        }

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        // TODO - fill in body
        if (this.rep.equals("")) {
            this.rep = Integer.toString(k);
        } else {
            this.rep = this.rep + Integer.toString(k);
        }

    }

    @Override
    public final int divideBy10() {

        // TODO - fill in body
        String end = "";
        int rem = 0;

        if (!this.rep.isEmpty()) {
            end = this.rep.substring(this.rep.length() - 1);
            this.rep = this.rep.substring(0, this.rep.length() - 1);
            rem = Integer.parseInt(end);
        }

        return rem;
    }

    @Override
    public final boolean isZero() {

        // TODO - fill in body
        boolean ans = false;

        if (this.rep.isEmpty()) {
            ans = true;
        }

        // This line added just to make the component compilable.
        return ans;
    }

}
