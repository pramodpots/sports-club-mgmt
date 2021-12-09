import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Main class which initiates the system.
 * @param: memberTreeSet: holds all the members in system
 * @param: guiMainFrame holds single main JFrame to execute all operations.
 */
public class ClubMembership {
    // constants
    private static final double INDIVIDUAL_MEMBER_FEE = 36.0;
    private static final double FAMILY_MEMBER_FEE = 60.0;
    public static final double VISITOR_FEE = 10.0;

    public static TreeSet<Member> memberTreeSet = new TreeSet<Member>(new MemberComparator());
    static GUIMainFrame guiMainFrame;

    // loads all the previous members form clients old "customerlist.csv" file into the memberTreeSet
    public static void readFromCSV() {
        ArrayList<String> memberList = CSVUtil.readCSVFile();
        for (String memberCSVString : memberList) {
            Member memberObj = new IndividualMember(memberCSVString);
            memberTreeSet.add(memberObj);
        }
    }

    // when system is restarted it will load all the members that were in the system before closing
    // this functions loads all the members from "SheffieldSportsClub.csv".
    public static void loadAllMembersOnStart() {
        ArrayList<String> memberList = CSVUtil.loadAllMembersOnStart();
        for (String memberCSVString : memberList) {
            Member memberObj;
            if (memberCSVString.contains("Family")) { // If family member
                memberObj = new FamilyMember(memberCSVString);
            } else {
                memberObj = new IndividualMember(memberCSVString);
            }
            memberTreeSet.add(memberObj);
        }
    }

    // All members currently present in system are exported to "SheffieldSportsClub.csv" file.
    public static void saveToCSV() {
        ArrayList<String> updatedMemberList = new ArrayList<>();
        for (Member m : memberTreeSet) {
            updatedMemberList.add(m.toCSVString());
        }
        CSVUtil.writeCSVFile(updatedMemberList);
    }

    // Generated Arraylist of all the entries from memberTreeSet
    public static ArrayList<Member> getMembersList() {
        ArrayList<Member> memberList = new ArrayList<>();
        for (Member member : memberTreeSet) {
            memberList.add(member);
        }
        return memberList;
    }

    /**
     * calculates total membership income based on start date and end date of membership
     * end date should be always more than january 1st of this year
     * @return calculated member income
     */
    public static double getCalculatedTotalMemberShipIncome() {
        int individualMemberIncome = 0;
        int familyMemberIncome = 0;

        for(Member member: memberTreeSet) {
            int validMonths = 0;
            boolean isEndDateAfterJan1st = DateUtil.isDateAfterJanuary1st(member.getMembershipEndDate());

            if(isEndDateAfterJan1st) { // membership was valid after jan 1st only then calculate
                boolean isStartDateAfterJan1st = DateUtil.isDateAfterJanuary1st(member.getMembershipStartDate());

                if (isStartDateAfterJan1st) { // both dates are after 1st jan of this year
                    // so income months will be only between membership start date and current date
                    validMonths = DateUtil.getNumOfMonthsFromDate(member.getMembershipStartDate());
                } else {
                    // else start date was before jan 1, so valid months are all months since 1st jan till end date
                    validMonths = DateUtil.getNumOfMonthsSinceJanuary1(member.getMembershipEndDate());
                }

                if (member instanceof IndividualMember) {
                    individualMemberIncome += (validMonths * INDIVIDUAL_MEMBER_FEE);
                } else {
                    familyMemberIncome += (validMonths * FAMILY_MEMBER_FEE);
                }
            }
        }
        double totalIncome = individualMemberIncome + familyMemberIncome;

        return totalIncome;
    }

    // main function to start the system.
    public static void main(String[] args) {
        loadAllMembersOnStart();
        guiMainFrame = new GUIMainFrame();
    }
}
