package gui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener personTableListener;

    public TablePanel(){

        tableModel= new PersonTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();

        JMenuItem remove = new JMenuItem("Delete Row");
        popup.add(remove);


        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row  = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row,row);

                if (e.getButton() == MouseEvent.BUTTON3){
                    popup.show(table,e.getX(),e.getY());
                };
            }
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row  = table.getSelectedRow();
                if (personTableListener != null){
                    personTableListener.rowDelete(row);
                    tableModel.fireTableRowsDeleted(row,row);
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> persons){

        tableModel.setData(persons);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }


    public void setPersonListener(PersonTableListener listener){
        this.personTableListener = listener;
    }

}
