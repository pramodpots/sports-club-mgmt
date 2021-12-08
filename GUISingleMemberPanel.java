import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is representation of single member in gui
 * Shows id, name, date of birth and two buttons 'show/edit', 'delete' in single horizontal line
 * 1. show/edit - opens window to see and if required update member details
 * 2. delete - deletes that member form the system
 */
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

        // edit button action listener
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GUIEditShowMemberForm(member);
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

    // creates empty component used for spacing items properly
    private Component getEmptyComponent() {
        Component emptyComponent = Box.createRigidArea(new Dimension(5, 0));
        return emptyComponent;
    }
}
