import java.util.HashMap;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    // TODO - declare static and instance data members

    private final String firstName;
    private final String lastName;
    private final String emailAddress;

    private static HashMap<String, Integer> nameCounts;
    static {
        nameCounts = new HashMap<String, Integer>();
    }

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        // TODO - fill in body
        this.firstName = firstName;
        this.lastName = lastName;
        if (nameCounts.containsKey(lastName)) {
            int number = nameCounts.get(lastName);
            number++;
            nameCounts.put(lastName, number);
        } else {
            nameCounts.put(lastName, 1);
        }
        this.emailAddress = lastName.toLowerCase() + "."
                + nameCounts.get(lastName) + "@osu.edu";
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        // TODO - fill in body
        String name = this.firstName + " " + this.lastName;

        // Added to make skeleton compilable
        return name;
    }

    @Override
    public String emailAddress() {

        // TODO - fill in body

        // Added to make skeleton compilable
        return this.emailAddress;
    }

    @Override
    public String toString() {

        // TODO - fill in body
        String result = "Name: " + this.firstName + " " + this.lastName
                + ",  Email: " + this.emailAddress;

        // Added to make skeleton compilable
        return result;
    }

}
