package processor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public void addPoss(String... poss) {
        this.poss.addAll(Arrays.asList(poss));
    }

    public void addNegs(String... negs) {
        this.negs.addAll(Arrays.asList(negs));
    }

    public void addNorms(String... norms) {
        this.norms.addAll(Arrays.asList(norms));
    }
}