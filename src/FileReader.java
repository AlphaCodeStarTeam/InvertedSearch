import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private static final String DOC_FOLDER_PATH = "Temp\\";

    public static Scanner getFileScanner(String fileName) throws FileNotFoundException {
        return new Scanner(new File(DOC_FOLDER_PATH + fileName));
    }

    public static List<String> getDocIDs() {
        return Arrays.asList(new File(DOC_FOLDER_PATH).list());
    }
}
