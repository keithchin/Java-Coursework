import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileOSU {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileOSU() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        // TODO - fill in body
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Enter an input file:");
        String input = in.nextLine();
        out.println("Enter an input file:");
        String output = in.nextLine();
        SimpleReader inputFile = new SimpleReader1L(input);
        SimpleWriter outputFile = new SimpleWriter1L(output);
        while (!inputFile.atEOS()) {
            outputFile.println(inputFile.nextLine());
        }

    }

}
