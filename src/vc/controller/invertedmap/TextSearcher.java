package vc.controller.invertedmap;

import model.FileReader;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * This Class Uses Inverted Index Method For Mapping The Words Inside Documents And Uses A HashMap For Doing So
 * Search Inverted Index If You Are Not Familiar With It
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     HashMap
 */

public class TextSearcher {
    private HashMap<String, HashSet<String>> invertedMap;

    public TextSearcher() {
        this.invertedMap = new HashMap<>();
        initMap();
    }

    //This Method Gets DocIDs From Model And Uses Another Function To Save Its Words
    private void initMap() {
        try {
            for (String docID : FileReader.getDocIDs()) {
                addDocToMap(docID);
            }
        } catch (NullPointerException e) {
            System.err.println("File Documents Are Interrupted");
            e.printStackTrace();
        }
    }

    //By Using A Scanner This Method Saves The Words Inside A Doc As A Key Into The Inverted Map
    private void addDocToMap(String docID) {
        try {
            Scanner scanner = FileReader.getFileScanner(docID);
            while (scanner.hasNext()) {
                String word = scanner.next();
                addKeyToMap(word.toLowerCase(), docID);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getDoc(String docID) throws FileNotFoundException {
        StringBuilder docContextBuilder = new StringBuilder("");
        Scanner scanner = FileReader.getFileScanner(docID);
        while (scanner.hasNextLine()) {
            docContextBuilder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return docContextBuilder.toString();
    }

    //Gets Two Parameters, Keyword And Its DocID And Save It Inside The Map
    private void addKeyToMap(String key, String docID) {

        if(invertedMap.containsKey(key)) {
            invertedMap.get(key).add(docID);
        } else {
            HashSet<String> docIDs = new HashSet<>();
            docIDs.add(docID);
            invertedMap.put(key, docIDs);
        }

    }

    //By Getting A KeyWord Returns A Set Of The Docs Which Consist That KeyWord
    public HashSet<String> getDocIDs(String key) {
        return invertedMap.containsKey(key) ? invertedMap.get(key) : new HashSet<>();
    }

}
