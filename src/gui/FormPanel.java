package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel jobLabel;
    private JTextField nameField;
    private JTextField jobField;
    private JButton submitBtn;

    private FormListener formListener;
    private JList ageList;
    private JComboBox subjectCombo;

    private JCheckBox citizenCheckbox;

    private JLabel sinLabel;
    private JTextField sinField;

    private JRadioButton maleGender;
    private JRadioButton femaleGender;
    private ButtonGroup genderGroup;

    FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

//        created objects
        nameLabel = new JLabel("Name: ");
        jobLabel = new JLabel("Job: ");
        nameField = new JTextField(10);
        jobField = new JTextField(10);
        ageList = new JList();
        subjectCombo = new JComboBox();
        sinLabel = new JLabel("SIN Number: ");
        sinField = new JTextField(10);
        citizenCheckbox = new JCheckBox();

        maleGender = new JRadioButton("Male");
        femaleGender  = new JRadioButton("Female");
        genderGroup = new ButtonGroup();

        submitBtn = new JButton("Submit");


        // setup gender group
        genderGroup.add(maleGender);
        genderGroup.add(femaleGender);

        maleGender.setActionCommand("male");
        femaleGender.setActionCommand("female");
        maleGender.setSelected(true);

        // setup age list
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeGroup(1,"Under 18"));
        ageModel.addElement(new AgeGroup(2,"18 to 60"));
        ageModel.addElement(new AgeGroup(3,"Above 60"));

        ageList.setModel(ageModel);
        ageList.setPreferredSize(new Dimension(112, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(0);

        // setup subject combo
        DefaultComboBoxModel subjectModel = new DefaultComboBoxModel();
        subjectModel.addElement("C++");
        subjectModel.addElement("GO");
        subjectModel.addElement("Python");
        subjectCombo.setModel(subjectModel);

        // setup tax
        sinLabel.setEnabled(false);
        sinField.setEnabled(false);

        citizenCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isCitizen = citizenCheckbox.isSelected();
                sinLabel.setEnabled(isCitizen);
                sinField.setEnabled(isCitizen);
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String job = jobField.getText();
                int ageGroup = ageList.getSelectedIndex();
                String subject = (String) subjectCombo.getSelectedItem();
                String sinNumber = sinField.getText();
                boolean isCitizen = citizenCheckbox.isSelected();

                String genderCommand = genderGroup.getSelection().getActionCommand();

                FormEvent event = new FormEvent(this, name, job, ageGroup,subject,sinNumber,isCitizen,genderCommand);

                if(formListener != null){
                    formListener.formSubmitted(event);
                }
            }
        });

        submitBtn.setMnemonic(KeyEvent.VK_S);

        Border inner = BorderFactory.createTitledBorder("Add Person");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer,inner));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

//        FIRST ROW
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets =  new Insets(0,0,0,5);
        add(nameLabel,gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField,gc);

//      SECOND ROW
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(jobLabel,gc);

        gc.gridx = 1;
        gc.insets =  new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(jobField,gc);

//      Third Row
        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "),gc);

        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList,gc);

//      Forth Row
        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Subject: "),gc);


        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(subjectCombo,gc);

//      Next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Gender: "),gc);


        gc.weightx = 1;
        gc.weighty = 0.05;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleGender,gc);

        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleGender,gc);


//      Next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Citizen: "),gc);


        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheckbox,gc);


//      Next Row
        gc.gridx = 0;
        gc.gridy++;
        gc.insets =  new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(sinLabel,gc);


        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(sinField,gc);


//      Fifth Row
        gc.weightx = 1;
        gc.weighty = 2.0;
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submitBtn,gc);

    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

}

class AgeGroup{
    private int id;
    private String text;

    public AgeGroup(int id, String text){
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
