import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GUIShowOrEditMember extends JFrame implements ActionListener {
    // Instance field to save member
    Member member;

    // form fields labels
    JLabel lblLastName, lblFirstName, lblDob, lblGender, lblAddress, lblContactNum, lblHealthInfo, lblAllergyInfo,
            lblMembershipStart, lblMembershipEnd, lblMembershipFee, lblMembershipType, lblSpouseName, lblNumOfChildren;

    // form text fields
    JTextField tfLastName, tfFirstName, tfDob, tfGender, tfAddress, tfContactNum, tfHealthInfo, tfAllergyInfo,
            tfMembershipStart, tfMembershipEnd, tfMembershipFee, tfMembershipType, tfSpouseName, tfNumberOfChildren;

    // form radio button fields
    JRadioButton rdoMale, rdoFemale, rdoIndividual, rdoFamily;

    JButton btnUpdateMember;

    JLabel lblInstruction, lblErrorMsg;

    Container contentPane;

    public GUIShowOrEditMember(Member member) {
        this.member = member;
        setBounds(580, 220, 850, 550);
        setTitle("Edit Member");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        setLocation(new Point(dim.width / 4, dim.height / 4));

        contentPane = this.getContentPane();

        // create member basic details pane and its fields
        JPanel basicDetailsPanel = new JPanel(new GridLayout(0, 2));
        basicDetailsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        lblLastName = new JLabel("Last Name*:");
        tfLastName = new JTextField(member.getLastName());
        basicDetailsPanel.add(lblLastName);
        basicDetailsPanel.add(tfLastName);

        lblFirstName = new JLabel("First Name*:");
        tfFirstName = new JTextField(member.getFirstName());
        basicDetailsPanel.add(lblFirstName);
        basicDetailsPanel.add(tfFirstName);

        lblDob = new JLabel("Date of Birth*:");
        tfDob = new JTextField(member.getDateOfBirth());
        basicDetailsPanel.add(lblDob);
        basicDetailsPanel.add(tfDob);

        // create gender selection panel
        lblGender = new JLabel("Gender*:");
        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        ButtonGroup genderGroup = new ButtonGroup();

        boolean isMale = (member.getGender().equals("Male") || member.getGender().isEmpty()) ? true : false;
        rdoMale = new JRadioButton("Male", isMale);
        genderPanel.add(rdoMale);
        genderGroup.add(rdoMale);

        rdoFemale = new JRadioButton("Female", !isMale);
        genderPanel.add(rdoFemale);
        genderGroup.add(rdoFemale);

        basicDetailsPanel.add(lblGender);
        basicDetailsPanel.add(genderPanel);

        lblAddress = new JLabel("Address:");
        tfAddress = new JTextField(member.getAddress());
        basicDetailsPanel.add(lblAddress);
        basicDetailsPanel.add(tfAddress);

        lblContactNum = new JLabel("Contact Number:");
        tfContactNum = new JTextField(member.getContactNumber());
        basicDetailsPanel.add(lblContactNum);
        basicDetailsPanel.add(tfContactNum);

        lblHealthInfo = new JLabel("Health Information:");
        tfHealthInfo = new JTextField(member.getHealthConditionInfo());
        basicDetailsPanel.add(lblHealthInfo);
        basicDetailsPanel.add(tfHealthInfo);

        lblAllergyInfo = new JLabel("Allergy Information:");
        tfAllergyInfo = new JTextField(member.getAllergyInfo());
        basicDetailsPanel.add(lblAllergyInfo);
        basicDetailsPanel.add(tfAllergyInfo);

        lblMembershipStart = new JLabel("Membership Start Date*:");
        tfMembershipStart = new JTextField(member.getMembershipStartDate());
        basicDetailsPanel.add(lblMembershipStart);
        basicDetailsPanel.add(tfMembershipStart);

        lblMembershipEnd = new JLabel("Membership End Date:");
        tfMembershipEnd = new JTextField(member.getMembershipEndDate());
        tfMembershipEnd.setEditable(false); // Make it readonly
        basicDetailsPanel.add(lblMembershipEnd);
        basicDetailsPanel.add(tfMembershipEnd);

        // specific member fields (Individual / Family)
        boolean isMemberTypeIndividual = (member instanceof IndividualMember) ? true : false; // to know the type of
                                                                                              // member

        lblMembershipFee = new JLabel("Membership Fee(36.0/60.0):");
        tfMembershipFee = new JTextField(isMemberTypeIndividual ? "36.0" : "60.0");
        tfMembershipFee.setEditable(false);
        basicDetailsPanel.add(lblMembershipFee);
        basicDetailsPanel.add(tfMembershipFee);

        // create membership type selection panel
        lblMembershipType = new JLabel("Membership Type*:");
        JPanel membershipTypePanel = new JPanel(new GridLayout(1, 2));
        ButtonGroup membershipTypeGroup = new ButtonGroup();
        rdoIndividual = new JRadioButton("Individual", isMemberTypeIndividual);
        membershipTypePanel.add(rdoIndividual);
        membershipTypeGroup.add(rdoIndividual);

        rdoFamily = new JRadioButton("Family", !isMemberTypeIndividual);
        membershipTypePanel.add(rdoFamily);
        membershipTypeGroup.add(rdoFamily);

        basicDetailsPanel.add(lblMembershipType);
        basicDetailsPanel.add(membershipTypePanel);

        lblSpouseName = new JLabel("Spouse Name:");
        tfSpouseName = new JTextField(
                isMemberTypeIndividual ? "" : ((FamilyMember) member).getSpouseName());
        basicDetailsPanel.add(lblSpouseName);
        basicDetailsPanel.add(tfSpouseName);

        lblNumOfChildren = new JLabel("Number of children");
        tfNumberOfChildren = new JTextField(
                isMemberTypeIndividual ? "" : String.valueOf(((FamilyMember) member).getNumberOfChildren()));

        basicDetailsPanel.add(lblNumOfChildren);
        basicDetailsPanel.add(tfNumberOfChildren);

        btnUpdateMember = new JButton("Update details");
        btnUpdateMember.addActionListener(this);

        lblInstruction = new JLabel("Fields indicated with (*) are required fields.");
        lblErrorMsg = new JLabel("");

        JPanel southPanel = new JPanel(new GridLayout(0, 1));
        southPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        southPanel.add(lblInstruction);
        southPanel.add(lblErrorMsg);
        southPanel.add(btnUpdateMember);

        contentPane.add(basicDetailsPanel, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // get all the values from the form
        String lastName = tfLastName.getText().trim();
        String firstName = tfFirstName.getText().trim();
        String dateOfBirth = tfDob.getText().trim();
        String gender = rdoMale.isSelected() ? "Male" : "Female";
        String address = tfAddress.getText().trim();
        String contactNumber = tfContactNum.getText().trim();
        String healthConditionInfo = tfHealthInfo.getText().trim();
        String allergyInfo = tfAllergyInfo.getText().trim();
        String membershipStartDate = tfMembershipStart.getText().trim();
        String membershipEndDate = tfMembershipEnd.getText().trim();

        String spouseName = tfSpouseName.getText().trim();
        String numberOfChildren = tfNumberOfChildren.getText().trim();

        // check for required fields
        if (lastName.isEmpty() || firstName.isEmpty() || dateOfBirth.isEmpty() || membershipStartDate.isEmpty()) {
            lblErrorMsg.setText("Please enter all the required fields");
            return;
        }

        // check for valid dates
        try {
            LocalDate dob = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate startDate = LocalDate.parse(membershipStartDate, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeException err) {
            // dates are invalid ask user to edit dates
            err.printStackTrace();
            lblErrorMsg.setText("Please enter date is DD/MM/YYYY format e.g- 31/12/1999");
            return;
        }

        // delete existing entry from the main collection
        ClubMembership.memberTreeSet.remove(member);
        if (rdoIndividual.isSelected()) {
            member = new IndividualMember();
        } else {
            member = new FamilyMember();
        }


        member.setLastName(lastName);
        member.setFirstName(firstName);
        member.setDateOfBirth(dateOfBirth);
        member.setGender(gender);
        member.setAddress(address);
        member.setContactNumber(contactNumber);
        member.setHealthConditionInfo(healthConditionInfo);
        member.setAllergyInfo(allergyInfo);
        member.setMembershipStartDate(membershipStartDate);
        member.setMembershipEndDateByStartDate(membershipStartDate); // set end date based on start date

        if (member instanceof FamilyMember) {
            ((FamilyMember) member).setSpouseName(spouseName);
            if (!numberOfChildren.isEmpty()) {
                try {
                    ((FamilyMember) member).setNumberOfChildren(Integer.parseInt(numberOfChildren));
                } catch (NumberFormatException err) {
                    // set number to zero
                    ((FamilyMember) member).setNumberOfChildren(0);
                }
            }

        }
        ClubMembership.memberTreeSet.add(member);
        setVisible(false);
        ClubMembership.guiMainFrame.updatePane(); // update live list of members
        JOptionPane.showMessageDialog(null, "Member details updated successfully.");
    }
}
