import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private TextSearcher textSearcher;
    private HashSet<String> norms, poss, negs, resultSet;

    public Controller() {
        this.scanner = new Scanner(System.in);
        textSearcher = new TextSearcher();
        textSearcher.initMap();
        run();
    }

    private void run() {
        while (true) {
            System.out.println("Please Enter Your Word : ");
            String[] splitInput = scanner.nextLine().split(" ");
            norms = new HashSet<>();
            poss = new HashSet<>();
            negs = new HashSet<>();

            for (String word : splitInput) {
                char starter = word.charAt(0);

                switch (starter) {
                    case '+':
                        poss.add(word.substring(1));
                        break;
                    case '-':
                        negs.add(word.substring(1));
                        break;
                    default:
                        norms.add(word);
                }

            }

            modifyResultForNorms();
            modifyResultForPoss();
            modifyResultForNegs();

            System.out.println("Results : " + resultSet.toString());

            resultSet = null;
        }
    }

    private void modifyResultForNorms() {
        for (String norm : norms) {
            HashSet<String> hashSet = textSearcher.getDocIDs(norm);
            if(hashSet.isEmpty()) {
                resultSet = new HashSet<>();
                return;
            }

            if (resultSet == null) {
                resultSet = hashSet;
            } else {
                resultSet = andToResultSet(hashSet);

                if (resultSet.isEmpty()) {
                    return;
                }
            }

        }
    }

    private HashSet<String> andToResultSet(HashSet<String> hashSet) {
        HashSet<String> andSet = new HashSet<>();
        boolean delimiter = hashSet.size() > resultSet.size();
        HashSet<String> biggerSet = delimiter ? hashSet : resultSet, smallerSet = delimiter ? resultSet : hashSet;

        for (String docID : smallerSet) {
            if(biggerSet.contains(docID)) {
                andSet.add(docID);
            }
        }

        return andSet;
    }

    private void modifyResultForPoss() {
        for (String word : poss) {
            resultSet.addAll(textSearcher.getDocIDs(word));
        }
    }

    private void modifyResultForNegs() {
        for (String word : negs) {
            resultSet.removeAll(textSearcher.getDocIDs(word));
        }
    }

}
