
import javax.swing.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String date = "6/8/1996";

        LocalDate nd = LocalDate.now();
        // convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
    }
}
