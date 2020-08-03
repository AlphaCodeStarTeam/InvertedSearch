import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private TextSearcher textSearcher;
    private HashSet<String> norms, poss, negs, resultSet;
    private HashMap<String, Integer> docIDToRep;
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
        resultSet = new HashSet<>();
        docIDToRep = new HashMap<>();
        for (String norm : norms) {
            for (String docID : textSearcher.getDocIDs(norm)) {
                docIDToRep.put(docID, (docIDToRep.containsKey(docID) ? (docIDToRep.get(docID) + 1) : 1));
            }
        }
        for (String docID : docIDToRep.keySet()) {
            if(docIDToRep.get(docID) == norms.size()) {
                resultSet.add(docID);
            }
        }
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
