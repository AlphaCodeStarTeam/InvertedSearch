import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private TextSearcher textSearcher;

    public Controller() {
        this.scanner = new Scanner(System.in);
        textSearcher = new TextSearcher();
        textSearcher.initMap();
        run();
    }

    private void run() {
        while (true) {
            System.out.println("Please Enter Your Word : ");
            String searchedWord = scanner.nextLine();
            HashMap<String, Integer> docsWithRepetition = textSearcher.getDocIDs(searchedWord.toLowerCase());
            System.out.println(searchedWord + " (" + docsWithRepetition.size() + "X) :");
            System.out.println("Occurences : { ");
            for (String docID : docsWithRepetition.keySet()) {
                System.out.println("\t " + docID + " (" + docsWithRepetition.get(docID) + "X)");
            }
            System.out.println("}");
        }
    }
}
