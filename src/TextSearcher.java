import java.io.FileNotFoundException;
import java.util.*;

public class TextSearcher {
    private HashMap<String, HashMap<String, Integer>> invertedMap;

    public TextSearcher() {
        this.invertedMap = new HashMap<>();
    }

    public void initMap() {
        try {
            for (String docID : FileReader.getDocIDs()) {
                addDocToMap(docID);
            }
        } catch (Exception e) {
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
            HashMap<String, Integer> keyHashMap = invertedMap.get(key);

            if(keyHashMap.containsKey(docID)) {
                keyHashMap.replace(docID, keyHashMap.get(docID) + 1);
            } else {
                keyHashMap.put(docID, 1);
            }

        } else {
            HashMap<String, Integer> keyHashMap = new HashMap<>();
            keyHashMap.put(docID, 1);
            invertedMap.put(key, keyHashMap);
        }

    }

    public HashMap<String, Integer> getDocIDs(String key) {
        return invertedMap.containsKey(key) ? invertedMap.get(key) : new HashMap<>();
    }
}
