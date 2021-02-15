package controller;

import model.*;
import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private Database db = new Database();


    public List<Person> getPersons(){
        return db.getPerson();
    }

    public void addPerson(FormEvent e){
        String name = e.getName();
        String job = e.getJob();
        int ageGroupId = e.getAge();
        String subject = e.getSubject();
        String sinNumber = e.getSinNumber();
        boolean isCitizen = e.isCitizen();
        String gender = e.getGender();

        AgeGroup ageGroup;
        switch (ageGroupId){
            case 0:
                ageGroup = AgeGroup.child;
                break;
            case 1:
                ageGroup = AgeGroup.adult;
                break;
            case 2:
                ageGroup = AgeGroup.senior;
                break;
            default:
                ageGroup = AgeGroup.none;
                System.err.println(ageGroup);
                break;
        }

        Subject subjectStr;
        switch (subject){
            case "C++":
                subjectStr = Subject.cpp;
                break;
            case "GO":
                subjectStr = Subject.go;
                break;
            case "Python":
                subjectStr = Subject.python;
                break;
            default:
                subjectStr = Subject.other;
                System.err.println(subjectStr);
                break;
        }

        Gender genderStr;

        if (gender == "male") {
            genderStr = Gender.male;
        }else{
            genderStr = Gender.female;
        }

        Person person = new Person(name,job,ageGroup,subjectStr,sinNumber,isCitizen,genderStr);

        db.addPerson(person);

    }

    public void saveToFile(File file) throws IOException {
        db.saveFile(file);
    }


    public void loadFile(File file) throws IOException {
        db.loadFile(file);
    }

    public void removePerson(int row) {
        db.removePerson(row);
    }

}
