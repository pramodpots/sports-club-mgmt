import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Main class which initiates the system.
 * @param: memberTreeSet: holds all the members in system
 * @param: guiMainFrame holds single main JFrame to execute all operations.
 */
public class ClubMembership {
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

    // main function to start the system.
    public static void main(String[] args) {
        loadAllMembersOnStart();
        guiMainFrame = new GUIMainFrame();
    }
}
