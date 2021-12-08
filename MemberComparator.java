
import java.util.Comparator;

/**
 * Setting rules to compare member objects
 * 1. Last name
 * 2. First name
 * 3. Date of birth
 *
 * priority is 1,2,3 here 1 is highest and 3 is lowest
 * if all 1,2,3 are same for 2 members they are considered as duplicate
 */
public class MemberComparator implements Comparator<Member> {

    @Override
    public int compare(Member m1, Member m2) { // used m1,m2 as local variables
        int comparedLastNames = m1.getLastName().compareTo(m2.getLastName()); // compare last names

        // if lastnames are the same
        if (comparedLastNames == 0) {
            int comparedFirstNames = m1.getFirstName().compareTo(m2.getFirstName()); // campare first names

            // if both last and first names are the same, compare date of births
            if (comparedFirstNames == 0) {
                String m1DOB = m1.getDateOfBirth();
                String m2DOB = m2.getDateOfBirth();

                if (!m1DOB.isEmpty() && !m2DOB.isEmpty()) { // if actual dob is present only then compare dates
                    return DateUtil.convertStringToDate(m1DOB).compareTo(DateUtil.convertStringToDate(m2DOB));
                }
                // Covert to date will not work if empty date so in that case compare normal
                // string without conversion
                return m1DOB.compareTo(m2DOB);
            } else {
                return comparedFirstNames;
            }
        }
        return comparedLastNames;
    }
}
