
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

/**
 * Class to work with dates
 * convert string to date
 * convert date to string
 * calculate Age on 1st september of this year based on date of birth
 */
public class DateUtil {
    // Constants
    public static final int BASE_DAY = 1; // 1st day of month
    public static final int BASE_MONTH = 9; // september

    /**
     * gets date and calculates date on 1st september of this year.
     * @param dateOfBirth
     * @return calculated age (int)
     */
    public static int getCalculateAge(String dateOfBirth) {
        // generating proper comparable date objects
        LocalDate currentDate = LocalDate.now();
        LocalDate baseDate = LocalDate.of(currentDate.getYear(), BASE_MONTH, BASE_DAY); // Date: 01/09/YYYY (current
                                                                                        // year)
        LocalDate dateOfBirthDateObj = convertStringToDate(dateOfBirth); // date obj for dob

        Period period = Period.between(dateOfBirthDateObj, baseDate); // gives period difference in years, months, days

        return period.getYears(); // diff of years will be the age on base date

    }

    /**
     * Coverts date in string to actual comparable date object
     * @param strDate Expected in format (dd/mm/yy) or (dd/mm/yyyy)
     * @return date in string formatted as dd/mm/yyyy
     */
    public static LocalDate convertStringToDate(String strDate) {
        try {
            String[] splitDate = strDate.split("/");
            int day = Integer.parseInt(splitDate[0]);
            int month = Integer.parseInt(splitDate[1]);
            int year = Integer.parseInt(splitDate[2]);
            int currentYear = LocalDate.now().getYear();
            if (year < 100) { // that means its yy format year
                // adding 2 digit year to 2000 is more than current year it means
                // date of birth is from 1900 + 2 digit year
                year = (year + 2000) < currentYear ? year + 2000 : year + 1900;
            }
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LocalDate.now(); // if something goes wrong return current date
    }

    /**
     * converts date object to string
     * @param date
     * @return converted date in string
     */
    public static String convertDateToString(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        int currentYear = LocalDate.now().getYear();
        if (year < 100) { // that means its yy format year
            // adding 2 digit year to 2000 is more than current year it means
            // date of birth is from 1900 + 2 digit year
            year = (year + 2000) < currentYear ? year + 2000 : year + 1900;
        }
        String strDate = "" + day + "/" + month + "/" + year;
        return strDate;
    }

    /**
     * This function checks if the passed date is after current date (today)
     * e.g. function will return 'true' if current date is 10/11/2021 and passed date (end date) is 7/7/2023
     * @param endDate ideally should be future date to return true
     * @return
     */
    public static boolean isDateAfterJanuary1st(String endDate) {
        if (endDate.isEmpty()) return false; // if empty string return false

        boolean dateAfterToday = false; // default
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate january1st = LocalDate.of(currentDate.getYear(),1,1);
            LocalDate localEndDate = convertStringToDate(endDate);
            dateAfterToday = localEndDate.isAfter(january1st);
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return dateAfterToday;
    }

    /**
     * calculates months between passed date and 1st january of this year
     * @param date
     * @return
     */
    public static int getNumOfMonthsSinceJanuary1(String date) {
        LocalDate fromDate = convertStringToDate(date); // we want number of months since jan 1 from given date
        LocalDate currentDate = LocalDate.now(); // current month with 1st date
        LocalDate january1st = LocalDate.of(currentDate.getYear(),1,1);
        Period period = Period.between(january1st, fromDate); // gives period difference in years, months, days

        int numberOfMonths = period.getMonths() + 1; // need to consider current month as well
        return numberOfMonths;
    }

    /**
     * calculates number of months between two given date strings
     * @param startDate string start date
     * @param endDate string end date
     * @return int num of months
     */
    public static int getNumOfMonthsBetweenDates(String startDate, String endDate) {
        LocalDate localStartDate = convertStringToDate(startDate);
        LocalDate localEndDate = convertStringToDate(endDate);
        Period period = Period.between(localStartDate, localEndDate);
        int numOfMonthsBetween = period.getMonths() + 1;
        return numOfMonthsBetween;
    }

    /**
     * calculates number of months from given date to current date
     * @param startDate string start date
     * @return int num of months
     */
    public static int getNumOfMonthsFromDate(String startDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate localStartDate = convertStringToDate(startDate);
        Period period = Period.between(localStartDate, currentDate);
        int numOfMonthsBetween = period.getMonths() + 1;
        return numOfMonthsBetween;
    }
}
