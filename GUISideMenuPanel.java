import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUISideMenuPanel extends JPanel implements ActionListener {
    private static int visitorCount = 0;
    private JButton addMemberButton;
    private JButton importMembersButton;
    private JButton exportMembersButton;
    private JButton logVisitorButton;
    private JButton showAllMembers;
    private JButton searchButton;
    private JLabel numberOfVisitors;
    private JLabel visitorsTillDate;
    private JTextField searchValue;

    public GUISideMenuPanel() {
        setLayout(new BoxLayout(this, 1));
        setBackground(Color.gray);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        add(lblMenu);
        add(new JLabel("==============="));

        // create buttons
        showAllMembers = new JButton("Show All Members");
        addMemberButton = new JButton("Add Member");
        importMembersButton = new JButton("Import Members");
        exportMembersButton = new JButton("Export Members");
        logVisitorButton = new JButton("Log Visitor");

        // add buttons to panel
        add(showAllMembers);
        add(addMemberButton);
        add(importMembersButton);
        add(exportMembersButton);
        add(logVisitorButton);

        // associate actions to buttons
        showAllMembers.addActionListener(this);
        addMemberButton.addActionListener(this);
        importMembersButton.addActionListener(this);
        exportMembersButton.addActionListener(this);
        logVisitorButton.addActionListener((this));

        // Create search area
        JLabel gapLine = new JLabel("===============");
        JLabel searchBy1 = new JLabel("Search by: ID");
        JLabel searchBy2 = new JLabel("First Name, Last Name,");
        JLabel searchBy3 = new JLabel("DOB (DD/MM/YYYY)");
        searchValue = new JTextField();
        searchValue.setMaximumSize(new Dimension(400, 40));
        searchButton = new JButton("Search");
        add(gapLine);
        add(searchBy1);
        add(searchBy2);
        add(searchBy3);
        add(searchValue);
        add(searchButton);

        searchButton.addActionListener(this);

        createVisitorDetailsArea();

    }

    private void createVisitorDetailsArea() {
        // Create Visitor count till today's date
        visitorsTillDate = new JLabel("Date:" + Util.convertDateToString(LocalDate.now()));
        numberOfVisitors = new JLabel("Visitors:" + String.valueOf(visitorCount));
        JLabel gapLine = new JLabel("===============");
        this.add(gapLine);
        this.add(visitorsTillDate);
        this.add(numberOfVisitors);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == showAllMembers) {
            ClubMembership.guiMainFrame.updatePane();
        } else if (source == addMemberButton) {
            try {
                new GUIAddMemberForm();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (source == importMembersButton) {
            ClubMembership.readFromCSV(); // reads customerlist.csv file and loads members into system
            ClubMembership.guiMainFrame.updatePane();
            JOptionPane.showMessageDialog(null,
                    "Import was successful from the file customerlist.csv in current folder");

        } else if (source == exportMembersButton) {
            ClubMembership.saveToCSV(); // saves current members to csv file
            JOptionPane.showMessageDialog(null,
                    "Export was successfully. Please check file 'SheffieldSportsClub.csv' in current folder.");
        } else if (source == logVisitorButton) {
            visitorCount++;
            JOptionPane.showMessageDialog(null, "Visitor logged successfully\n Total visitors: " + visitorCount);
            visitorsTillDate.setText("Date:" + Util.convertDateToString(LocalDate.now())); // this will change date if
                                                                                           // date is changed while
                                                                                           // logging the visitor
            numberOfVisitors.setText("Visitors:" + String.valueOf(visitorCount));
        } else if (source == searchButton) {
            String searchTerm = searchValue.getText().trim().toLowerCase();

            if (searchTerm.isEmpty())
                return; // do nothing in case empty string

            ArrayList<Member> memberList = ClubMembership.getMembersList();
            ArrayList<Member> filteredMemberList = new ArrayList<>();

            for (Member member : memberList) {
                String id = member.getFormattedUniqueId().toLowerCase();
                String firstName = member.getFirstName().toLowerCase();
                String lastName = member.getLastName().toLowerCase();
                String dob = member.getDateOfBirth().toLowerCase();

                String combinedString = id + firstName + lastName + dob;
                if (combinedString.contains(searchTerm)) { // if search term has anything is common with above
                                                           // attributes
                    filteredMemberList.add(member);
                }
            }
            if (!filteredMemberList.isEmpty()) {
                ClubMembership.guiMainFrame.updatePane(filteredMemberList);
            }
        }
    }
}
