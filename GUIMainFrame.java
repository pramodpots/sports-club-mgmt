import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Main GUI application starts here. This class is the base of GUI
 * On closing of this frame system will close.
 * Before closing this class will automatically save all the members into csv file
 */
public class GUIMainFrame extends JFrame {

    Container contentPane;
    JPanel mainContentPanel;
    JPanel memberListPanel;
    JScrollPane scrollPane;

    public GUIMainFrame() {
        // set up look and feel
        setTitle("Sheffield Sports Club");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        setSize(dim.width, dim.height);
        setLocation(new Point(dim.width / 4, dim.height / 4));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = this.getContentPane();
        mainContentPanel = new JPanel();
        memberListPanel = new JPanel();
        memberListPanel.setLayout(new BoxLayout(memberListPanel, BoxLayout.Y_AXIS));
        for (Member member : ClubMembership.memberTreeSet) {
            GUISingleMemberPanel guiSingleMember = new GUISingleMemberPanel(member);
            memberListPanel.add(guiSingleMember);
        }
        scrollPane = new JScrollPane(memberListPanel);
        mainContentPanel.add(scrollPane);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(new GUISideMenuPanel(), BorderLayout.WEST);
        setVisible(true);

        // This is used to export all members in system to be saved into
        // SheffieldSportsClub.csv file
        // which will be main database for this system.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                ClubMembership.saveToCSV(); // Export exiting members automatically
            }
        });
    }

    /**
     * Updates members list shown in the center pane
     * with all the members present in database tree set
     */
    public void updatePane() {
        ArrayList<Member> memberList = ClubMembership.getMembersList();
        updatePane(memberList);
    }

    /**
     * Updates members list shown in the center pane with passed list of members
     * Used to show only filtered list of members
     * related to search functionality
     */
    public void updatePane(ArrayList<Member> memberList) {
        contentPane.removeAll();
        mainContentPanel = new JPanel();
        memberListPanel = new JPanel();
        memberListPanel.setLayout(new BoxLayout(memberListPanel, BoxLayout.Y_AXIS));
        for (Member member : memberList) {
            GUISingleMemberPanel guiSingleMember = new GUISingleMemberPanel(member);
            memberListPanel.add(guiSingleMember);
        }
        scrollPane = new JScrollPane(memberListPanel);
        mainContentPanel.add(scrollPane);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(new GUISideMenuPanel(), BorderLayout.WEST);
        setVisible(true);
    }
}
