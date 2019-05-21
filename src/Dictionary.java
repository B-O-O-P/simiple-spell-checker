import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Dictionary class.
 */
public class Dictionary {
    private HashSet<String> words;

    /**
     * Default constructor.
     *
     * @param words {@link java.util.Set} of words for dictionary.
     */
    public Dictionary(HashSet<String> words) {
        this.words = words;
    }

    Dictionary(Path file) {
        try {
            this.words = new HashSet<>(Arrays.asList(Files.readString(file).split(" ")));
        } catch (IOException e) {
            System.err.println("ERROR: I/O Exception with input file for dictionary: " + e.getMessage());
        }
    }

    HashSet<String> getWords() {
        return words;
    }

    boolean contains(String word) {
        return words.contains(word);
    }
}
