import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
            resultSet = new HashSet<>();

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
            modifyResultForNorm();
            modifyResultForPoss();
            modifyResultForNegs();

            System.out.println("Results : " + resultSet.toString());

            resultSet = null;
        }
    }

    private void modifyResultForNorm() {
        for (String norm : norms) {
            resultSet.addAll(textSearcher.getDocIDs(norm));
        }

        HashSet<String> hashSetPrim = new HashSet<>();
        for (String norm : norms) {
            HashSet<String> hashSet = new HashSet<>(resultSet);
            hashSet.removeAll(textSearcher.getDocIDs(norm));
            hashSetPrim.addAll(hashSet);
        }

        resultSet.removeAll(hashSetPrim);
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
