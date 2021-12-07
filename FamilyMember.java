public class FamilyMember extends Member {
    // constants
    private static final double FAMILY_MEMBER_FEE = 60.0;
    private static final String MEMBERSHIP_TYPE_FAMILY = "Family";
    // instance fields
    private double membershipFee;
    private String membershipType;
    private String spouseName;
    private int numberOfChildren;

    public FamilyMember(String memberCSVString) {
        super(memberCSVString);
        // adding extra 20 commas to make code simple and consistent without multiple if
        // else cases
        memberCSVString += ",,,,,,,,,,,,,,,,,,,,";
        String[] attributes = memberCSVString.split("\\,", 40);
        membershipFee = FAMILY_MEMBER_FEE;
        membershipType = attributes[12];
        spouseName = attributes[13];
        setNumberOfChildren(attributes[14]);
    }

    public FamilyMember() {
        super();
        membershipFee = FAMILY_MEMBER_FEE;
        membershipType = MEMBERSHIP_TYPE_FAMILY;
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    private void setMembershipFee(double membershipFee) {
        this.membershipFee = FAMILY_MEMBER_FEE;
    }

    public String getMembershipType() {
        return membershipType;
    }

    private void setMembershipType() {
        this.membershipType = MEMBERSHIP_TYPE_FAMILY;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildrenStr) {
        int num = 0;
        try {
            num = Integer.parseInt(numberOfChildrenStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.numberOfChildren = num;
    }

    @Override
    public String toString() {
        return super.toString() + "FamilyMember{" +
                "membershipFee=" + membershipFee +
                ", membershipType='" + membershipType + '\'' +
                ", spouseName='" + spouseName + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                '}';
    }

    @Override
    public String toCSVString() {
        return super.toCSVString() + ',' +
                membershipFee + ',' +
                membershipType + ',' +
                spouseName + ',' +
                numberOfChildren;
    }
}
