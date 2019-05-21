import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class which is used to check spelling of word by given dictionary. Reads word and name of file in System.in
 */
public class SpellChecker {

    private static void checkWord(String word, Dictionary dictionary) {
        if (dictionary.contains(word)) {
            System.out.println("Word is correct.");
        } else {
            BKTree tree = BKTree.build(new ArrayList<>(dictionary.getWords()));
            System.out.println("Word is incorrect, perhaps you mean:");
            for (String w : tree.getSimilarWords(word)){
                System.out.println(w);
            }
        }
    }

    /**
     * Reads the word from System.in and checks it by given dictionary.
     * Dictionary represented as txt file and it's filename is read from System.in.
     *
     * @param args no args
     * @throws IOException if reading word or filename is unsuccessful or unable to open dictionary file
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stringReader = new BufferedReader(new InputStreamReader(System.in));

        String search = stringReader.readLine();
        String dictionaryFile = stringReader.readLine();
        Dictionary dictionary = new Dictionary(Paths.get(dictionaryFile));

        checkWord(search, dictionary);
    }
}
