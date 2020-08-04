package processor;

import invertedmap.TextSearcher;

import java.util.HashSet;
import java.util.Scanner;

/**
 * This Class Is For User Input Analysis And Show The Result Of Search
 * Modify Interface Helps To Manipulate The Final Result
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     SearchContainer
 * @see     TextSearcher
 */

@FunctionalInterface
interface Modify {
    void apply();
}

public class Controller {
    private final Scanner scanner;
    private final TextSearcher textSearcher;
    private Modify modify;
    private SearchContainer container;

    //The Initializer Block Due To Instantiate modify
    {
        modify = () -> {
            modifyResultForNorms();
            modifyResultForPoss();
            modifyResultForNegs();
        };
    }
    public Controller() {
        this.scanner = new Scanner(System.in);
        textSearcher = new TextSearcher();
        textSearcher.initMap();
        run();
    }

    /*Simple Example Of User Input:
    * was are +he +limit -is
    * meaning: must conclude both "was" and "are" or include either "he" or "limit" and must not have any "is"
    * */
    private void run() {
        while (true) {
            System.out.println("Please Enter Your Word : ");
            container = new SearchContainer(this.scanner);
            modify.apply();
            System.out.println("Results : " + container.getResultSet().toString());
        }
    }

    /**
     * This Function Is For Manipulating Results For Normal Inputs,
     * The Keys That Have Neither '+' Nor '-' From Their Beginning.
     * It Uses A High Performance Algorithm To Intersect The DocSets Of Inputs
     */
    private void modifyResultForNorms() {
        for (String norm : container.getNorms()) {
            container.getResultSet().addAll(textSearcher.getDocIDs(norm));
        }
        container.getResultSet().removeAll(getAndPrimedSet());
    }

    private HashSet<String> getAndPrimedSet() {
        HashSet<String> hashSetPrime = new HashSet<>();
        for (String norm : container.getNorms()) {
            //Getting The Union Of Keys ResultSets
            HashSet<String> hashSet = new HashSet<>(container.getResultSet());
            //Removing This Key ResultSet From The Union
            hashSet.removeAll(textSearcher.getDocIDs(norm));
            //Adding The Subtraction Set Above To The Prime Set
            hashSetPrime.addAll(hashSet);
        }
        //Returning The DocIDs Which Doesn't Include All Norms In Whole
        return hashSetPrime;
    }

    /**
     * This Function Is For Manipulating Results For Positive Inputs,
     * The Keys That Have '+' From Their Beginning.
     */
    private void modifyResultForPoss() {
        for (String word : container.getPoss()) {
            container.getResultSet().addAll(textSearcher.getDocIDs(word));
        }
    }

    /**
     * This Function Is For Manipulating Results For Negative Inputs,
     * The Keys That Have '-' From Their Beginning.
     */
    private void modifyResultForNegs() {
        for (String word : container.getNegs()) {
            container.getResultSet().removeAll(textSearcher.getDocIDs(word));
        }
    }
}
