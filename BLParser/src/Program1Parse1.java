import components.map.Map;
import components.map.Map.Pair;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Jonas Chan, Keith Chin
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires [<"INSTRUCTION"> is a proper prefix of tokens]
     * @ensures
     * 
     *          <pre>
     * if [an instruction string is a proper prefix of #tokens] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     *          </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body
        String instructionToken = tokens.dequeue();
        String nameOfInstruction = tokens.dequeue();

        // Check to see if instruction name is a primitive call name
        String[] stringArray = { "move", "infect", "turnleft", "turnright",
                "skip" };
        for (String element : stringArray) {
            Reporter.assertElseFatalError(!element.equals(nameOfInstruction),
                    "Error: New Instruction name must not be name"
                            + " of primitive instruction \"" + element + "\"");

        }

        String isToken = tokens.dequeue();

        // Check to see if "IS" comes after the identifier
        Reporter.assertElseFatalError(isToken.equals("IS"),
                "Error: Keyword \"IS\" expected, found: \"" + isToken + "\"");

        body.parseBlock(tokens);
        String endToken = tokens.dequeue();

        // Check to see if "END" is before the identifier name
        Reporter.assertElseFatalError(endToken.equals("END"),
                "Error: Keyword \"END\" expected, found: \"" + endToken + "\"");

        String endNameOfInstruction = tokens.dequeue();

        // Check to see if the same identifier name is at the end
        Reporter.assertElseFatalError(
                endNameOfInstruction.equals(nameOfInstruction),
                "Error: IDENTIFIER \"" + endNameOfInstruction
                        + "\" at end of instruction \"" + nameOfInstruction
                        + "\" must match instruction name");

        return nameOfInstruction;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        String programToken = tokens.dequeue();
        Program newProgram = new Program1Parse1();

        // Check to see if PROGRAM is the first in tokens

        Reporter.assertElseFatalError(programToken.equals("PROGRAM"),
                "Error: Keyword \"PROGRAM\" expected, found: \"" + programToken
                        + "\"");

        String prgmIdToken = tokens.dequeue();
        String isToken = tokens.dequeue();

        // Check to see if "IS" comes after the program identifier
        Reporter.assertElseFatalError(isToken.equals("IS"),
                "Error: Keyword \"IS\" expected, found: \"" + isToken + "\"");

        Map<String, Statement> context = newProgram.newContext();
        String instrOrBeginToken = tokens.front();

        while (instrOrBeginToken.equals("INSTRUCTION")) {
            Statement body = newProgram.newBody();
            String instructionName = parseInstruction(tokens, body);
            for (Pair<String, Statement> element : context) {
                Reporter.assertElseFatalError(
                        !element.key().equals(instructionName),
                        "Error: Instruction \"" + instructionName
                                + "\" cannot be already defined");
            }
            context.add(instructionName, body);
            instrOrBeginToken = tokens.front();

        }

        Reporter.assertElseFatalError(instrOrBeginToken.equals("BEGIN"),
                "Error: Keyword \"BEGIN\" expected, found: \""
                        + instrOrBeginToken + "\"");

        instrOrBeginToken = tokens.dequeue();
        Statement programBody = newProgram.newBody();
        programBody.parseBlock(tokens);

        String endToken = tokens.dequeue();

        // Check to see if "END" is before the program identifier name
        Reporter.assertElseFatalError(endToken.equals("END"),
                "Error: Keyword \"END\" expected, found: \"" + endToken + "\"");

        String endPrgmIdToken = tokens.dequeue();
        // Check to see if the same identifier name is at the end

        Reporter.assertElseFatalError(endPrgmIdToken.equals(prgmIdToken),
                "Error: IDENTIFIER \"" + endPrgmIdToken
                        + "\" at end of instruction \"" + prgmIdToken
                        + "\" must match instruction name");

        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "Error: END-OF-INPUT expected," + " found: \"" + tokens.front()
                        + "\"");

        this.replaceName(prgmIdToken);
        this.replaceBody(programBody);
        this.replaceContext(context);
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
