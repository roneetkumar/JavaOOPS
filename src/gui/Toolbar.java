package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private JButton openBtn;
    private JButton closeBtn;
    private StringListener textListener;


    public Toolbar(){

        setBorder(BorderFactory.createEtchedBorder());
        openBtn = new JButton("Open");
        closeBtn = new JButton("Close");

        openBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(openBtn);
        add(closeBtn);

    }

    public void setStringListener(StringListener textListener){
        this.textListener = textListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        switch (clicked.getText()) {
            case "Open":
                if (this.textListener != null)
                    this.textListener.textEmitter("Panel Opened\n");
                break;
            case "Close":
                if (this.textListener != null)
                    this.textListener.textEmitter("Panel Closed\n");
                break;
            default:
                break;
        }
    }
}
