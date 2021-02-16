package gui;

import controller.Controller;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {
    private JButton btn;
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private TablePanel tablePanel;
    private PrefDialog prefDialog;

    private Preferences prefs;

    private Controller controller;

    public MainFrame(){
        super("New Application");

        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        prefDialog = new PrefDialog(this);
        prefs = Preferences.userRoot().node("db");


        textPanel = new TextPanel();
        btn = new JButton("Submit");
        toolbar = new Toolbar();

        controller = new Controller();
        tablePanel.setData(controller.getPersons());

        tablePanel.setPersonListener(new PersonTableListener(){
            public void rowDelete(int row){
                controller.removePerson(row);

            }
        });

        prefDialog.setPrefListener(new PrefListener(){
            public void preferenceSet(String user, String pass, int port) {
                //System.out.println(user + ", " +  pass + ",  Port" + port);
                prefs.put("user",user);
                prefs.put("pass",pass);
                prefs.putInt("port",port);
            }
        });

        String user = prefs.get("user","");
        String pass = prefs.get("pass","");
        int port =  prefs.getInt("port", 3306);

        prefDialog.setDefault(user,pass,port);


        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

//        toolbar.setStringListener(new StringListener() {
//            public void textEmitter(String text) {
//                textPanel.appendText(text);
//            }
//        });
        formPanel.setFormListener(new FormListener(){
            public void formSubmitted(FormEvent e){
                controller.addPerson(e);
                tablePanel.refresh();
            }
        });

        add(formPanel,BorderLayout.WEST);
       // add(textPanel, BorderLayout.CENTER);
        add(btn,BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500,500));
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu=  new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenuItem importMenu = new JMenuItem("Import");
        JMenuItem exportMenu = new JMenuItem("Export");

        importMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                   // System.out.println(fileChooser.getSelectedFile());
                    try {
                        controller.loadFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load the data from this file","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exportMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    // System.out.println(fileChooser.getSelectedFile());
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save the data to this file","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });



        JCheckBoxMenuItem formMenu = new JCheckBoxMenuItem("Form");
        formMenu.setSelected(true);

        JCheckBoxMenuItem displayMenu=  new JCheckBoxMenuItem("Display");
        displayMenu.setSelected(true);

        formMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        displayMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                tablePanel.setVisible(menuItem.isSelected());
            }
        });

        formMenu.setMnemonic(KeyEvent.VK_F);
        displayMenu.setMnemonic(KeyEvent.VK_D);

        viewMenu.add(formMenu);
        viewMenu.add(displayMenu);

        JMenu helpMenu=  new JMenu("Help");
        JMenuItem prefItem = new JMenuItem("Preference");
        helpMenu.add(prefItem);

        prefItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prefDialog.setVisible(true);
            }
        });




        JMenuItem projectMenu = new JMenuItem("Project");
        JMenuItem moduleMenu=  new JMenuItem("Module");
        JMenu newMenu = new JMenu("New");
        newMenu.add(projectMenu);
        newMenu.add(moduleMenu);


        JMenu openMenu=  new JMenu("Open");
        JMenuItem imageMenu = new JMenuItem("Image");
        JMenuItem folderMenu = new JMenuItem("Folder    ");



        openMenu.add(imageMenu);
        openMenu.add(folderMenu);

        JMenuItem settingsMenu = new JMenuItem("Settings");
        JMenuItem exitMenu=  new JMenuItem("Exit");

        exitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(MainFrame.this,"Do you want exit ?", "Exit", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });

        exitMenu.setMnemonic(KeyEvent.VK_X);

        fileMenu.add(newMenu);
        fileMenu.add(openMenu);
        fileMenu.add(settingsMenu);
        fileMenu.add(importMenu);
        fileMenu.add(exportMenu);
        fileMenu.add(exitMenu);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }
}
