import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * 
 *
 * @author Jonas Chan
 */
public final class WordCounter {

    public static void getWords(SimpleReader inFile,
            Map<String, Integer> Words) {
        assert inFile != null : "Violation of: fileName is not null";
        assert Words != null : "Violation of: priceMap is not null";

        //Declaring separator characters in a set
        Set<Character> separatorSet = new Set1L<>();
        separatorSet.add(' ');
        separatorSet.add(',');
        separatorSet.add('.');
        separatorSet.add('-');
        separatorSet.add('\t');

        //Read till end of file
        while (!inFile.atEOS()) {
            String line = inFile.nextLine();
            //Ensure words are not case sensitive
            line = line.toLowerCase();

            //Separating words from the line and separators
            int position = 0;
            while (position < line.length()) {
                String next = "";
                char a = line.charAt(position);

                if (separatorSet.contains(a)) {
                    while (separatorSet.contains(a)
                            && position < line.length()) {
                        next = next + a;
                        position++;
                        if (position < line.length()) {
                            a = line.charAt(position);
                        }
                    }
                } else {
                    while (!separatorSet.contains(a)
                            && position < line.length()) {
                        next = next + a;
                        position++;
                        if (position < line.length()) {
                            a = line.charAt(position);
                        }
                    }
                    //Checking for words that already exits in the MAP
                    if (!Words.hasKey(next)) {
                        Words.add(next, 1);
                    } else {
                        Integer count = Words.value(next);
                        count++;
                        Words.replaceValue(next, count);
                    }
                }
            }

        }
        inFile.close();
    }

    /**
     *
     * @param Words
     *            Map of String and Integer consisting of words and word count
     * @param outHtml
     *            output file
     * @param InputFileName
     *            name of Input file
     */
    public static void outputHtml(Map<String, Integer> Words,
            SimpleWriter outHtml, String InputFileName) {
        assert outHtml != null : "Violation of: out is not null";
        assert outHtml.isOpen() : "Violation of: out.is_open";

        outHtml.println("<html>");
        outHtml.println("<head>");
        outHtml.println("<title>" + InputFileName + "</title>");
        outHtml.println("</head>");
        outHtml.println("<body>");
        outHtml.println(
                "<h2>Words Counted in data/" + InputFileName + ".txt</h2>");
        outHtml.println("<hr />");

        outHtml.println("<table border=1>");
        outHtml.println("<tr>" + "<th>Words</th>  <th>Counts</th>" + "</tr>");

        Map<String, Integer> temp = new Map1L<>();
        //Output Map in Html format by alphabectical order
        while (Words.size() != 0) {
            //Getting the smallest string
            String smallest = getSmallest(Words);
            Map.Pair<String, Integer> p = Words.remove(smallest);
            String key = p.key();
            Integer value = p.value();

            outHtml.println("<tr>");
            outHtml.println("<td>" + key + "</td>");
            outHtml.println("<td>" + value + "</td>");
            outHtml.println("</tr>");

            temp.add(key, value);
        }
        Words.transferFrom(temp);

        outHtml.println("</table>");
        outHtml.println("</body>");
        outHtml.println("</html>");
    }

    /**
     *
     * @param words
     *            Map of String and Integer consisting of words and word count
     * @return The smallest key from the MAP
     */
    public static String getSmallest(Map<String, Integer> words) {
        Map<String, Integer> temp = new Map1L<>();
        Map.Pair<String, Integer> p = words.removeAny();
        String smallest = p.key();
        Integer value = p.value();
        temp.add(smallest, value);
        while (words.size() > 0) {
            p = words.removeAny();
            String key = p.key();
            value = p.value();
            if (smallest.compareTo(key) > 0) {
                smallest = key;
            }
            temp.add(key, value);
        }
        words.transferFrom(temp);
        return smallest;
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.print("Enter Input File Name :");
        String InputFileName = in.nextLine();
        out.print("Enter Output File Name :");
        String OutputFileName = in.nextLine();

        SimpleReader inFile = new SimpleReader1L(InputFileName + ".txt");
        SimpleWriter outFile = new SimpleWriter1L(OutputFileName + ".html");

        //Create new map to contain word and number
        Map<String, Integer> Words = new Map1L<>();
        //Getting word and count from file to map
        getWords(inFile, Words);

        //Output MAP into Html format file
        outputHtml(Words, outFile, InputFileName);

        in.close();
        out.close();
    }
}
