import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        // TODO - fill in body
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ;
        System.out.print("Please enter a valid file location: ");
        String inputLocation = "";
        try {
            inputLocation = in.readLine();
        } catch (IOException e) {
            System.err.println("Error input stream from System: " + e);
            return;
        }

        BufferedReader inputFile;
        try {
            inputFile = new BufferedReader(new FileReader(inputLocation));
        } catch (IOException e) {
            System.err.println("Error opening input stream from file: " + e);
            return;
        }

        System.out.print("Please enter a output file name: ");
        String outputLocation = "";
        try {
            outputLocation = in.readLine();
        } catch (IOException e) {
            System.err.println("Error input stream from System: " + e);
            return;
        }

        BufferedWriter outputFile;
        try {
            outputFile = new BufferedWriter(new FileWriter(outputLocation));
        } catch (IOException e) {
            System.err.println("Error opening input stream from file: " + e);
            return;
        }

        String inputLines = "";
        try {
            inputLines = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading line from file input stream: "
                    + e);
            return;
        }

        try {
            while (inputLines != null) {
                outputFile.write(inputLines);
                outputFile.newLine();
                inputLines = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading line from file input stream: "
                    + e);
            return;
        }

        try {
            in.close();
            inputFile.close();
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error closing streams: " + e);
            return;
        }

    }
}
