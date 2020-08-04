package processor;

import java.util.HashSet;
import java.util.Scanner;

interface InputGetter {
    void getUserInputs(Scanner scanner);
}

public class SearchContainer implements InputGetter{
    private HashSet<String> norms;
    private HashSet<String> poss;
    private HashSet<String> negs;
    private HashSet<String> resultSet;

    public SearchContainer(Scanner scanner) {
        norms = new HashSet<>();
        poss = new HashSet<>();
        negs = new HashSet<>();
        resultSet = new HashSet<>();
        getUserInputs(scanner);
    }

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