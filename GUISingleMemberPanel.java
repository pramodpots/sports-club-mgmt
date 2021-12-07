import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISingleMemberPanel extends JPanel {

    public GUISingleMemberPanel(Member member) {

        // Size and style
        setPreferredSize(new Dimension(500, 40));
        setLayout(new BoxLayout(this, 0));

        // Actual content in panel
        JLabel idLabel = new JLabel("id:");
        JLabel uid = new JLabel(member.getFormattedUniqueId());
        uid.setPreferredSize(new Dimension(200, 25));
        JLabel fullName = new JLabel(member.getFullName());
        fullName.setPreferredSize(new Dimension(200, 25));
        JLabel dateOfBirth = new JLabel(member.getDateOfBirth());
        dateOfBirth.setPreferredSize(new Dimension(200, 25));

        JButton editButton = new JButton("Show/Edit");
        JButton deleteButton = new JButton(("Delete"));

        // Adding content to panel
        add(getEmptyComponent());
        add(getEmptyComponent());
        add(idLabel);
        add(uid);
        add(getEmptyComponent());
        add(fullName);
        add(getEmptyComponent());
        add(dateOfBirth);
        add(Box.createHorizontalGlue()); // for seperating details and buttons
        add(editButton);
        add(deleteButton);

        // delete action listener
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GUIShowOrEditMember(member);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });

        // delete action listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClubMembership.memberTreeSet.remove(member);
                ClubMembership.guiMainFrame.updatePane(); // update live list of members
                JOptionPane.showMessageDialog(new JPanel(), "Member "
                        + member.getUniqueID() + " "
                        + member.getFullName() + " "
                        + "deleted successfully.");
            }
        });
    }

    private Component getEmptyComponent() {
        Component emptyComponent = Box.createRigidArea(new Dimension(5, 0));
        return emptyComponent;
    }
}
