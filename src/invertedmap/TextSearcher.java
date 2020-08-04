package invertedmap;

import java.io.FileNotFoundException;
import java.util.*;

public class TextSearcher {
    private HashMap<String, HashSet<String>> invertedMap;

    public TextSearcher() {
        this.invertedMap = new HashMap<>();
    }

    public void initMap() {
        try {
            for (String docID : FileReader.getDocIDs()) {
                addDocToMap(docID);
            }
        } catch (NullPointerException e) {
            System.err.println("File Documents Are Interrupted");
            e.printStackTrace();
        }
    }

    private void addDocToMap(String docID) {
        try {
            Scanner scanner = FileReader.getFileScanner(docID);
            while (scanner.hasNext()) {
                String word = scanner.next();
                addKeyToMap(word.toLowerCase(), docID);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addKeyToMap(String key, String docID) {

        if(invertedMap.containsKey(key)) {
            invertedMap.get(key).add(docID);
        } else {
            HashSet<String> docIDs = new HashSet<>();
            docIDs.add(docID);
            invertedMap.put(key, docIDs);
        }

    }

    public HashSet<String> getDocIDs(String key) {
        return invertedMap.containsKey(key) ? invertedMap.get(key) : new HashSet<>();
    }

}
