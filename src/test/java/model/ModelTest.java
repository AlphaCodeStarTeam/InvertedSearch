package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ModelTest {

    @Test
    public void fileReaderTest() throws FileNotFoundException {
        FileReader.setDocFolderPath("Documents\\");
        Scanner scanner = FileReader.getFileScanner("ReadMe.md");
        String[] expected = new File("Documents").list();
        Assertions.assertArrayEquals(expected, FileReader.getDocIDs().toArray(new String[0]));

    }
}
