package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    private JTextField userField;
    private  JPasswordField passwordField;

    private PrefListener prefListener;

    public PrefDialog(JFrame parent){
        super(parent,"Settings",false );

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('#');


        spinnerModel = new SpinnerNumberModel(3306,0,9999,1);

        portSpinner = new JSpinner(spinnerModel);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;

        // Next Row

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        add(new JLabel("User"),gc);

        gc.gridx++;
        add(userField,gc);

        // Next Row

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        add(new JLabel("Password"),gc);

        gc.gridx++;
        add(passwordField,gc);

        // Next Row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;

        add(new JLabel("Port: "),gc);

        gc.gridx++;
        add(portSpinner,gc);

        // Next Row
        gc.gridy++;
        gc.gridx = 0;
        add(okButton,gc);

        gc.gridx++;
        add(cancelButton,gc);


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer)portSpinner.getValue();

                String user = userField.getText();

                char[] password =  passwordField.getPassword();

                if (prefListener != null){
                    prefListener.preferenceSet(user,new String(password),value);
                }
                setVisible(false);
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setSize(400,300);
        setLocationRelativeTo(parent);
    }


    public void setPrefListener(PrefListener prefListener){
        this.prefListener = prefListener;
    }

    public void setDefault(String user,String pass, int port){
        userField.setText(user);
        passwordField.setText(pass);
        portSpinner.setValue(port);
    }


}
