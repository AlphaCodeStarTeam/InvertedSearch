package processor;

import java.util.HashSet;

/**
 * This Class Is For Getting And Holding User Inputs And The Results
 * InputGetter Interface Is Due To Getting User Inputs
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     HashSet
 */

public class SearchQuery {
    private HashSet<String> norms;
    private HashSet<String> poss;
    private HashSet<String> negs;

    /**
     * Constructs a new, empty Container, And Then,
     * Starts To Get Input From The User With The Scanner That Has passed By Parameters.
     */
    public SearchQuery() {
        norms = new HashSet<>();
        poss = new HashSet<>();
        negs = new HashSet<>();
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

    public void addPos(String pos) {
        poss.add(pos);
    }

    public void addNeg(String neg) {
        negs.add(neg);
    }

    public void addNorm(String norm) {
        norms.add(norm);
    }
}