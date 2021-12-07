import java.io.*;
import java.util.ArrayList;

public class CSVOperations {

    public static ArrayList<String> readCSVFile() {
        String file = "customerlist.csv";
        return getMemberCSVStringsList(file);
    }

    public static ArrayList<String> loadAllMembersOnStart() {
        String file = "SheffieldSportsClub.csv";
        return getMemberCSVStringsList(file);
    }

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

    public static void writeCSVFile(ArrayList<String> lines) {
        String fileName = "SheffieldSportsClub.csv";
        System.out.println(lines.size());
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}