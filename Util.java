
import java.time.LocalDate;
import java.time.Period;

public class Util {
    // Constants
    public static final int BASE_DAY = 1; // 1st day of month
    public static final int BASE_MONTH = 9; // september

    // This file path will be used as database. Currently, file is in same dir so
    // only filename is used.
    // If file location is changed update the here full file path.
    public static final String DB_FILE_PATH = "customerlist.csv";

    public static int getCalculateAge(String dateOfBirth) {
        // generating proper comparable date objects
        LocalDate currentDate = LocalDate.now();
        LocalDate baseDate = LocalDate.of(currentDate.getYear(), BASE_MONTH, BASE_DAY); // Date: 01/09/YYYY (current
                                                                                        // year)
        LocalDate dateOfBirthDateObj = convertStringToDate(dateOfBirth); // date obj for dob

        Period period = Period.between(dateOfBirthDateObj, baseDate); // gives period difference in years, months, days

        return period.getYears(); // diff of years will be the age on base date

    }

    public static LocalDate convertStringToDate(String strDate) {
        try {
            String[] splitedDate = strDate.split("/");
            int day = Integer.parseInt(splitedDate[0]);
            int month = Integer.parseInt(splitedDate[1]);
            int year = Integer.parseInt(splitedDate[2]);
            int currentYear = LocalDate.now().getYear();
            if (year < 100) { // that means its yy format year
                year = (year + 2000) < currentYear ? year + 2000 : year + 1900;
            }
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LocalDate.now();
    }

    public static String convertDateToString(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        int currentYear = LocalDate.now().getYear();
        if (year < 100) { // that means its yy format year
            year = (year + 2000) < currentYear ? year + 2000 : year + 1900;
        }
        String strDate = "" + day + "/" + month + "/" + year;
        return strDate;
    }

    // This function will return date with added one year. This should be used to
    // calculate membership end date
    public static LocalDate addYearToDate(LocalDate date) {
        date = date.plusYears(1);
        return date;
    }

    /** Utility functions to work with csv files **/
    /** Utility functions to work with csv files **/
}
