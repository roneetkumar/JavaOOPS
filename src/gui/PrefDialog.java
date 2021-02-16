package gui;

import javax.swing.*;
import java.awt.*;

public class PrefDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;

    private SpinnerNumberModel spinnerModel;


    public PrefDialog(JFrame parent){
        super(parent,"Preference",false );

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306,0,9999,1);

        portSpinner = new JSpinner(spinnerModel);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
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




        setSize(400,300);
        setLocationRelativeTo(parent);
    }
}
