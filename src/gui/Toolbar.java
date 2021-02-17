package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private JButton saveBtn;
    private JButton refreshBtn;
    private ToolbarListener toolbarListener;


    public Toolbar(){

        setBorder(BorderFactory.createEtchedBorder());
        saveBtn = new JButton("Save");
        refreshBtn = new JButton("Refresh");

        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveBtn);
        add(refreshBtn);

    }

    public void setStringListener(ToolbarListener textListener){
        this.toolbarListener = textListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        switch (clicked.getText()) {
            case "Save":
                if (this.toolbarListener != null)
                    this.toolbarListener.saveEvent();
                break;
            case "Refresh":
                if (this.toolbarListener != null)
                    this.toolbarListener.refreshEvent();
                break;
            default:
                break;
        }
    }
}
