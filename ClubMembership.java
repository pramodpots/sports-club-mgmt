import java.util.ArrayList;
import java.util.TreeSet;

public class ClubMembership {
    public static TreeSet<Member> memberTreeSet = new TreeSet<Member>(new MemberComparator());
    static GUIMainFrame guiMainFrame;

    public static void readFromCSV() {
        ArrayList<String> memberList = CSVOperations.readCSVFile();
        for (String memberCSVString : memberList) {
            Member memberObj = new IndividualMember(memberCSVString);
            memberTreeSet.add(memberObj);
        }
    }

    public static void loadAllMembersOnStart() {
        ArrayList<String> memberList = CSVOperations.loadAllMembersOnStart();
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

    public static void saveToCSV() {
        ArrayList<String> updatedMemberList = new ArrayList<>();
        for (Member m : memberTreeSet) {
            updatedMemberList.add(m.toCSVString());
        }
        CSVOperations.writeCSVFile(updatedMemberList);
    }

    public static ArrayList<Member> getMembersList() {
        ArrayList<Member> memberList = new ArrayList<>();
        for (Member member : memberTreeSet) {
            memberList.add(member);
        }
        return memberList;
    }

    public static void main(String[] args) {
        loadAllMembersOnStart();
        guiMainFrame = new GUIMainFrame();
    }
}
