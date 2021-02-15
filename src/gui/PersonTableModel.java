package gui;

import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> db;
    private final String[] colNames = {
            "ID",
            "Name",
            "Job",
            "Gender",
            "Age",
            "Subject",
            "Citizen",
            "SIN"
    };

    public PersonTableModel(){}

    public void setData(List<Person> persons){
        this.db = persons;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = db.get(rowIndex);

        switch (columnIndex){
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getJob();
            case 3:
                return person.getGender();
            case 4:
                return person.getAgeGroup();
            case 5:
                return person.getSubject();
            case 6:
                return person.isCitizen();
            case 7:
                return person.getSinNumber();
        }

        return null;




    }
}
