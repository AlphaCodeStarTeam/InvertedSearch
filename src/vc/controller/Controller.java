package vc.controller;

import invertedmap.TextSearcher;
import vc.SearchQuery;

import java.util.HashSet;
import java.util.Scanner;

/**
 * This Class Is For User Input Analysis And Show The Result Of Search
 * Modify Interface Helps To Manipulate The Final Result
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     SearchQuery
 * @see     TextSearcher
 */

interface Modify {
    void apply(HashSet<String> resultSet, SearchQuery query);
}
public class Controller {
    private final Scanner scanner;
    private final TextSearcher textSearcher;
    private final Modify modify;

    //The Initializer Block Due To Instantiate modify
    {
        modify = (resultSet, query) -> {
            modifyResultForNorms(resultSet, query.getNorms());
            modifyResultForPoss(resultSet, query.getPoss());
            modifyResultForNegs(resultSet, query.getNegs());
        };
    }
    public Controller() {
        this.scanner = new Scanner(System.in);
        textSearcher = new TextSearcher();
        textSearcher.initMap();
    }

    public HashSet<String> executeQuery(SearchQuery query) {
        HashSet<String> resultSet = new HashSet<>();
        modify.apply(resultSet, query);
        return resultSet;
    }

    /**
     * This Function Is For Manipulating Results For Normal Inputs,
     * The Keys That Have Neither '+' Nor '-' From Their Beginning.
     * It Uses A High Performance Algorithm To Intersect The DocSets Of Inputs
     */
    private void modifyResultForNorms(HashSet<String> resultSet, HashSet<String> norms) {
        for (String norm : norms) {
            resultSet.addAll(textSearcher.getDocIDs(norm));
        }
        resultSet.removeAll(getAndPrimedSet(resultSet, norms));
    }

    private HashSet<String> getAndPrimedSet(HashSet<String> resultSet, HashSet<String> norms) {
        HashSet<String> hashSetPrime = new HashSet<>();
        for (String norm : norms) {
            //Getting The Union Of Keys ResultSets
            HashSet<String> hashSet = new HashSet<>(resultSet);
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
    private void modifyResultForPoss(HashSet<String> resultSet, HashSet<String> poss) {
        for (String word : poss) {
            resultSet.addAll(textSearcher.getDocIDs(word));
        }
    }

    /**
     * This Function Is For Manipulating Results For Negative Inputs,
     * The Keys That Have '-' From Their Beginning.
     */
    private void modifyResultForNegs(HashSet<String> resultSet, HashSet<String> negs) {
        for (String word : negs) {
            resultSet.removeAll(textSearcher.getDocIDs(word));
        }
    }

    public String getDoc(String docID) {
        return textSearcher.getDoc(docID);
    }
}
