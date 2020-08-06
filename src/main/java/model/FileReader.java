package model;

import processor.controller.invertedmap.TextSearcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This Class Works As A Model To Do The File Work
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     TextSearcher
 */
public class FileReader {
    private static String DOC_FOLDER_PATH = "Documents\\";

    public static Scanner getFileScanner(String fileName) throws FileNotFoundException {
        return new Scanner(new File(DOC_FOLDER_PATH + fileName));
    }

    public static List<String> getDocIDs() {
        return Arrays.asList(new File(DOC_FOLDER_PATH).list());
    }

    public static void setDocFolderPath(String docFolderPath) {
        DOC_FOLDER_PATH = docFolderPath;
    }
}
