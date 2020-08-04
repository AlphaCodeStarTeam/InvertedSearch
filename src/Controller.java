import java.util.HashSet;
import java.util.Scanner;

@FunctionalInterface
interface Modify {
    void apply();
}

public class Controller {
    private final Scanner scanner;
    private TextSearcher textSearcher;
    private Modify modify;
    private HashSet<String> norms, poss, negs, resultSet;

    {
        modify = new Modify() {
            @Override
            public void apply() {
                modifyResultForNorms();
                modifyResultForPoss();
                modifyResultForNegs();
            }
        };
    }
    public Controller() {
        this.scanner = new Scanner(System.in);
        textSearcher = new TextSearcher();
        textSearcher.initMap();
        run();
    }

    private void run() {
        while (true) {
            System.out.println("Please Enter Your Word : ");
            initSets();
            getUserInputs();
            modify.apply();
            System.out.println("Results : " + resultSet.toString());
        }
    }

    private void initSets() {
        norms = new HashSet<>();
        poss = new HashSet<>();
        negs = new HashSet<>();
        resultSet = new HashSet<>();
    }

    private void getUserInputs() {
        for (String word : scanner.nextLine().split(" ")) {
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
    }

    private void modifyResultForNorms() {
        for (String norm : norms) {
            resultSet.addAll(textSearcher.getDocIDs(norm));
        }
        resultSet.removeAll(getAndPrimedSet());
    }

    private HashSet<String> getAndPrimedSet() {
        HashSet<String> hashSetPrim = new HashSet<>();
        for (String norm : norms) {
            HashSet<String> hashSet = new HashSet<>(resultSet);
            hashSet.removeAll(textSearcher.getDocIDs(norm));
            hashSetPrim.addAll(hashSet);
        }
        return hashSetPrim;
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
