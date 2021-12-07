
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Member {
    // instance fields
    private int uniqueID;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String gender;
    private String address; // can be moved into own separate class.
    private String contactNumber;
    private String healthConditionInfo; // string stating health issues if any
    private String allergyInfo; // string with allergy information
    private String membershipStartDate;
    private String membershipEndDate; // calculated automatically

    public Member(String id, String firstName, String lastName, String dateOfBirth, String address, String gender,
            String contactNumber, String healthConditionInfo, String allergyInfo, String membershipStartDate,
            String membershipEndDate) {
        this.uniqueID = generateId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.healthConditionInfo = healthConditionInfo;
        this.allergyInfo = allergyInfo;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
    }

    public Member(String commaSeperatedStringAttributes) {
        // adding extra 20 commas to make code simple and consistent without multiple if
        // else cases
        commaSeperatedStringAttributes += ",,,,,,,,,,,,,,,,,,,,";
        String[] attributes = commaSeperatedStringAttributes.split("\\,", 40);
        this.uniqueID = generateId(attributes[0]);
        this.lastName = attributes[1];
        this.firstName = attributes[2];
        // this.dateOfBirth = attributes[3];
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        try {
            if (!dateOfBirth.isEmpty()) {
                dateOfBirth = Util.convertDateToString(Util.convertStringToDate(dateOfBirth));
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

    public int getAge() {
        return Util.getCalculateAge(this.dateOfBirth);
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

    public void setMembershipEndDate(String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    /** other helper methods **/

    private int generateId(String id) {
        int uniqueID;
        if (id.isEmpty()) {
            uniqueID = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        } else {
            uniqueID = Integer.parseInt(id);
        }
        return uniqueID;
    }

    private int generateId() {
        int uniqueID = UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        return uniqueID;
    }

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

    public int compareTo(Member member) {
        int comparedLastNames = this.lastName.compareTo(member.lastName);

        // if lastnames are the same
        if (comparedLastNames == 0) {
            int comparedFirstNames = this.firstName.compareTo(member.firstName);

            // if both surnames and first names are the same, compare ages
            if (comparedFirstNames == 0) {
                return this.dateOfBirth.compareTo(member.dateOfBirth);
            } else {
                return comparedFirstNames;
            }
        }
        return comparedLastNames;
    }

    public void setMembershipEndDateByStartDate(String membershipStartDate) {
        LocalDate endDate = Util.convertStringToDate(membershipStartDate);
        endDate = endDate.plusYears(1);
        membershipEndDate = Util.convertDateToString(endDate);
    }
}
