package model;

import java.io.*;
import java.util.*;

public class Database{

    private List<Person> persons;

    public Database(){
        persons = new LinkedList<Person>();
    }

    public void addPerson(Person person){
        this.persons.add(person);
    }

    public List<Person> getPerson(){
        return Collections.unmodifiableList(this.persons);
    }

    public void saveFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person[] persons = this.persons.toArray(new Person[this.persons.size()]);

        oos.writeObject(persons);
        oos.close();
    }


    public void loadFile(File file) throws IOException{
        FileInputStream fos = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fos);

        try {
            Person[] people = (Person[]) ois.readObject();
            persons.clear();
            persons.addAll(Arrays.asList(people));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }

    public void removePerson(int row) {
        persons.remove(row);
    }
}
