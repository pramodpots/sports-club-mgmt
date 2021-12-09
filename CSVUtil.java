import java.io.*;
import java.util.ArrayList;

/**
 * This class is designed to work with actual csv files
 * 1. Writes List of strings to "SheffieldSportsClub.csv" file
 * 2. Reads csv files and returns Arraylist of Strings
 */
public class CSVUtil {

    // To support import functionality this function reads all the entries from customerlist.csv file
    public static ArrayList<String> readCSVFile() {
        String file = "customerlist.csv";
        return getMemberCSVStringsList(file);
    }

    // To have all exiting members ready on restart of system,
    // this function reads all the entries from "SheffieldSportsClub.csv" file
    public static ArrayList<String> loadAllMembersOnStart() {
        String file = "SheffieldSportsClub.csv";
        return getMemberCSVStringsList(file);
    }

    // generic function to read csv file form file name
    private static ArrayList<String> getMemberCSVStringsList(String fileName) {
        ArrayList<String> csvStringsList = new ArrayList<String>();
        try {
            // create file if not present
            File file = new File(fileName);
            file.createNewFile();
            String line;

            // start reading from the file
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                // using try with so resources are cleared automatically
                while ((line = br.readLine()) != null) {
                    csvStringsList.add(line.strip());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvStringsList;
    }

    // used to export all existing members in system to SheffieldSportsClub.csv file
    public static void writeCSVFile(ArrayList<String> lines) {
        String fileName = "SheffieldSportsClub.csv";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("done!" + "exported " + lines.size() + " members");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}