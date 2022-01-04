package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

    public static List<String> readUsernames(String filename) {
        List<String> usernames = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String username = s.nextLine();
                usernames.add(username);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading usernames from file " + filename);
            e.printStackTrace();
        }
        return usernames;
    }

    public static boolean writeOutputFile(String outputFilename, String data) {
        try {
            FileWriter fw = new FileWriter(outputFilename, true);
            fw.write(data);
            fw.close();
            System.out.println("Successfully wrote to file. Size (char): " + data.length());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred writing to file " + outputFilename);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createOutputFile(String filename) {
        File file  = new File(filename);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
