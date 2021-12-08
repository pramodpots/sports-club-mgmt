
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Main class of the system
 * Contains all the basic details about member
 */
public abstract class Member {
    // instance fields
    private int uniqueID;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String contactNumber;
    private String healthConditionInfo; // string stating health issues if any
    private String allergyInfo; // string with allergy information
    private String membershipStartDate;
    private String membershipEndDate; // calculated automatically

    /**
     * class constructor getting comma seperated string which holds all the fields in single string
     * this should be used when loading the existing customers form csv files
     * @param memberCSVString
     */
    public Member(String memberCSVString) {
        // adding extra 20 commas to make code simple and consistent
        // without multiple if else cases
        memberCSVString += ",,,,,,,,,,,,,,,,,,,,";
        String[] attributes = memberCSVString.split("\\,", 40);
        this.uniqueID = generateId(attributes[0]);
        this.lastName = attributes[1];
        this.firstName = attributes[2];
        this.setDateOfBirth(attributes[3]);
        this.gender = attributes[4];
        this.address = attributes[5];
        this.contactNumber = attributes[6];
        this.healthConditionInfo = attributes[7];
        this.allergyInfo = attributes[8];
        this.membershipStartDate = attributes[9];
        this.membershipEndDate = attributes[10];
    }

    public Member() {
        uniqueID = generateId();
    }

    /** getter setter to access instance fields **/

    public int getUniqueID() {
        return uniqueID;
    }

    // convert unique id to string for usability
    public String getFormattedUniqueId() {
        return String.valueOf(uniqueID);
    }

    private void setUniqueID() { // Not accessible from outside
        this.uniqueID = generateId();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * validate the dateofbirth and save
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        try {
            if (!dateOfBirth.isEmpty()) {
                dateOfBirth = DateUtil.convertDateToString(DateUtil.convertStringToDate(dateOfBirth));
            }
            this.dateOfBirth = dateOfBirth;
        } catch (DateTimeException err) {
            err.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHealthConditionInfo() {
        return healthConditionInfo;
    }

    public void setHealthConditionInfo(String healthConditionInfo) {
        this.healthConditionInfo = healthConditionInfo;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(String allergyInfo) {
        this.allergyInfo = allergyInfo;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    private void setMembershipEndDate(String membershipEndDate) { // cannot be set from outside
        this.membershipEndDate = membershipEndDate;
    }

    /**
     * generate and set membership end date based on membership start date
     * @param membershipStartDate
     */
    public void setMembershipEndDateByStartDate(String membershipStartDate) {
        LocalDate endDate = DateUtil.convertStringToDate(membershipStartDate);
        endDate = endDate.plusYears(1);
        membershipEndDate = DateUtil.convertDateToString(endDate);
    }

    /** other helper methods **/

    /**
     * set id that is already assigned to user, if id is empty or not proper numbers
     * create one and assign it to member
     * @param id
     * @return
     */
    private int generateId(String id) {
        int uniqueID = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        if (!id.isEmpty()) { // if sent id is not empty then use given id
            try {
                uniqueID = Integer.parseInt(id); // convert string to int for system
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return uniqueID; //
    }

    /**
     * generate unique id
     * @return id
     */
    private int generateId() {
        int uniqueID = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        return uniqueID;
    }

    /**
     *
     * @return full name by joining last and first names.
     */
    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "uniqueID=" + uniqueID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", healthConditionInfo='" + healthConditionInfo + '\'' +
                ", allergyInfo='" + allergyInfo + '\'' +
                ", membershipStartDate='" + membershipStartDate + '\'' +
                ", membershipEndDate='" + membershipEndDate + '\'' +
                '}';
    }

    // This is simple modification of toString method
    // Used to save string into csv file
    public String toCSVString() {
        return Integer.toString(uniqueID) + ',' +
                lastName + ',' +
                firstName + ',' +
                dateOfBirth + ',' +
                gender + ',' +
                address + ',' +
                contactNumber + ',' +
                healthConditionInfo + ',' +
                allergyInfo + ',' +
                membershipStartDate + ',' +
                membershipEndDate;
    }
}
