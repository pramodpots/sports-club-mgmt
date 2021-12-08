
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
}
