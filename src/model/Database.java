package model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database{

    private List<Person> persons;
    private Connection conn;


    public Database(){
        persons = new LinkedList<Person>();
    }


    public void connect() throws Exception {

        persons.add(new Person("Roneet","developer", AgeGroup.adult,Subject.go,"12345",true,Gender.male));
        persons.add(new Person("Person","developer", AgeGroup.adult,Subject.go,"12345",true,Gender.male));
        if (conn != null) return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Driver not found");
        }

        String connectionUrl = "jdbc:mysql://localhost:3306/db?useSSL=false";
        conn = DriverManager.getConnection(connectionUrl,"root","abc123.");

        System.out.println("Connected : ");


    }

    public  void disconnect(){
        try {
            conn.close();
        } catch (SQLException throwable) {
            System.out.println("Can't close the connection");
        }
    }

    public void save(){
        String checkSql = "SELECT count(*) AS count FROM persons WHERE job=?";
        PreparedStatement checkStmt = null;
        try {
            checkStmt = conn.prepareStatement(checkSql);
            for (Person person:persons){
                String job= person.getJob();

                checkStmt.setString(1,job);
                ResultSet res = checkStmt.executeQuery();
                res.next();
                int count = res.getInt(1);
                System.out.println("Count for person with ID "+ job+ " is " +  count);
            }
            checkStmt.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
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
