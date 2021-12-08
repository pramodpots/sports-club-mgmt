/**
 * Child class of Member class
 * Supports additional details related to individual member type
 */
public class IndividualMember extends Member{
    // constants
    private static final double INDIVIDUAL_MEMBER_FEE = 36.0;
    private static final String MEMBERSHIP_TYPE_INDIVIDUAL = "Individual";

    // instance fields
    private double membershipFee;
    private String membershipType;

    /**
     * class constructor getting comma seperated string which holds all the fields in single string
     * this should be used when loading the existing customers form csv files
     * @param memberCSVString
     */
    public IndividualMember(String memberCSVString) {
        // adding extra 20 commas to make code simple and consistent
        // without multiple if else cases
        super(memberCSVString);
        memberCSVString += ",,,,,,,,,,,,,,,,,,,,";
        String[] attributes = memberCSVString.split("\\,", 40);
        membershipFee = INDIVIDUAL_MEMBER_FEE;
        membershipType = attributes[12];
    }
    public IndividualMember() {
        super();
        membershipFee = INDIVIDUAL_MEMBER_FEE;
        membershipType = MEMBERSHIP_TYPE_INDIVIDUAL;
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    private void setMembershipFee() {
        this.membershipFee = INDIVIDUAL_MEMBER_FEE;
    }

    public String getMembershipType() {
        return membershipType;
    }

    private void setMembershipType() {
        this.membershipType = MEMBERSHIP_TYPE_INDIVIDUAL;
    }

    @Override
    public String toString() {
        return super.toString() + "IndividualMember{" +
                "membershipFee=" + membershipFee +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }

    // This is simple modification of toString method
    // Used to save string into csv file
    @Override
    public String toCSVString() {
        return super.toCSVString()+',' +
                membershipFee + ',' +
                membershipType + ',' +
                ',' +
                ',';  // extra two commas to while saving to csv file to be consistent with family member
    }
}
