import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BKTree {
    private static final int LIMIT = 3;
    private Node root;
    private List<Node> nodes = new ArrayList<>();

    private static class Node {
        private final String word;
        private final Map<Integer, Integer> nodeIndex;

        private Node(String word) {
            this.word = word;
            nodeIndex = new HashMap<>();
        }
    }

    private BKTree() {

    }

    static BKTree build(ArrayList<String> dictionary) {
        BKTree tree = new BKTree();
        tree.root = new Node(dictionary.get(0));
        for (int i = 1; i < dictionary.size(); ++i) {
            makeBKTree(tree, tree.root, dictionary.get(i));
        }
        return tree;
    }

    private static void makeBKTree(BKTree tree, Node root, String word) {
        int distance = findMinimumEditDistance(word, root.word);
        Integer ptr = root.nodeIndex.get(distance);

        if (null != ptr) {
            makeBKTree(tree, tree.nodes.get(ptr), word);
        } else {
            tree.nodes.add(new Node(word));
            root.nodeIndex.put(distance, tree.nodes.size() - 1);
        }
    }

    List<String> getSimilarWords(String search) {
        List<String> result = new ArrayList<>();
        getSimilarWords(root, result, search);
        return result;
    }

    private void getSimilarWords(Node root, List<String> result, String search) {
        int distance = findMinimumEditDistance(search, root.word);

        if (distance <= LIMIT) {
            result.add(root.word);
        }

        int start = distance - LIMIT;
        if (start < 0) {
            start = 1;
        }

        while (start < distance + LIMIT) {
            Integer ptr = root.nodeIndex.get(start);

            if (null != ptr) {
                getSimilarWords(nodes.get(ptr), result, search);
            }

            start++;
        }

    }

    private static int findMinimumEditDistance(String source, String destination) {
        int sourceLength = source.length();
        int destinationLength = destination.length();
        int[][] editDP = new int[sourceLength + 1][destinationLength + 1];

        for (int s = 0; s <= sourceLength; ++s) {
            for (int d = 0; d <= destinationLength; ++d) {
                if (0 == s) {
                    editDP[s][d] = d;
                } else if (0 == d) {
                    editDP[s][d] = s;
                } else if (source.charAt(s - 1) == destination.charAt(d - 1)) {
                    editDP[s][d] = editDP[s - 1][d - 1];
                } else {
                    editDP[s][d] = 1 + Math.min(Math.min(editDP[s][d - 1], editDP[s - 1][d]), editDP[s - 1][d - 1]);
                }
            }
        }

        return editDP[sourceLength][destinationLength];
    }
}
