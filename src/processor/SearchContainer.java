package processor;

import invertedmap.TextSearcher;

import java.util.HashSet;
import java.util.Scanner;

/**
 * This Class Is For Getting And Holding User Inputs And The Results
 * InputGetter Interface Helps Getting User Inputs
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     HashSet
 */

interface InputGetter {
    void getUserInputs(Scanner scanner);
}

public class SearchContainer implements InputGetter{
    private HashSet<String> norms;
    private HashSet<String> poss;
    private HashSet<String> negs;
    private HashSet<String> resultSet;

    /**
     * Constructs a new, empty Container, And Then,
     * Starts To Get Input From The User With The Scanner That Has passed By Parameters.
     */
    public SearchContainer(Scanner scanner) {
        norms = new HashSet<>();
        poss = new HashSet<>();
        negs = new HashSet<>();
        resultSet = new HashSet<>();
        getUserInputs(scanner);
    }

    /**
     * This Function Is For Getting Inputs From The User
     * It Separates Keys To Sets By Their Start.
     */
    public void getUserInputs(Scanner scanner) {
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

    public HashSet<String> getNorms() {
        return norms;
    }

    public HashSet<String> getPoss() {
        return poss;
    }

    public HashSet<String> getNegs() {
        return negs;
    }

    public HashSet<String> getResultSet() {
        return resultSet;
    }
}