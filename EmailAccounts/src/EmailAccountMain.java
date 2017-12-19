import java.util.HashSet;
import java.util.Set;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Set<EmailAccount> setOfAccounts = new HashSet<EmailAccount>();

        String fullName = "empty";

        out.print("Please enter full name: ");
        fullName = in.nextLine();

        while (!fullName.isEmpty()) {

            int i = 0;
            String firstName = "", lastName = "";
            int indexOfSpace = fullName.indexOf(' ');
            firstName = fullName.substring(0, indexOfSpace);
            lastName = fullName.substring(indexOfSpace + 1, fullName.length());
            EmailAccount newAccount = new EmailAccount1(firstName, lastName);
            setOfAccounts.add(newAccount);
            for (EmailAccount account : setOfAccounts) {
                out.println(account);
            }

            out.println();

            out.print("Please enter full name: ");
            fullName = in.nextLine();

        }
        in.close();
        out.close();
    }

}
