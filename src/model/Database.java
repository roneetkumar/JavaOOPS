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

    public void save() throws SQLException {

        try {
        String checkSql = "SELECT count(*) AS count FROM persons WHERE id=?";

        String insertSql = "INSERT INTO persons (name, age, subject, sin, is_citizen, gender, job) VALUES(?,?,?,?,?,?,?)";

        String updateSql = "UPDATE persons SET name=?, age=?, subject=?, sin=?, is_citizen=?, gender=?, job=? WHERE id=?";

        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        PreparedStatement insertStatement = conn.prepareStatement(insertSql);
        PreparedStatement updateStatement = conn.prepareStatement(updateSql);

            for (Person person:persons){
                int id = person.getId();
                String  name =  person.getName();
                Subject subject = person.getSubject();
                Gender gender = person.getGender();
                AgeGroup age = person.getAgeGroup();
                String sin = person.getSinNumber();
                String job= person.getJob();
                Boolean isCitizen =  person.isCitizen();

                checkStmt.setInt(1,id);
                ResultSet res = checkStmt.executeQuery();
                res.next();
                int count = res.getInt(1);

                if(count == 0){
                    System.out.println("Inserting person with ID : " + id);
//                    insertStatement.setInt(1,id);
                    insertStatement.setString(1,name);
                    insertStatement.setString(2,age.name());
                    insertStatement.setString(3,subject.name());
                    insertStatement.setString(4,sin);
                    insertStatement.setBoolean(5,isCitizen);
                    insertStatement.setString(6,gender.name());
                    insertStatement.setString(7,job);
                    insertStatement.executeUpdate();
                }else{
                    System.out.println("Updating person with ID : " + id);
                    int col = 1;
                    updateStatement.setString(col++,name);
                    updateStatement.setString(col++,age.name());
                    updateStatement.setString(col++,subject.name());
                    updateStatement.setString(col++,sin);
                    updateStatement.setBoolean(col++,isCitizen);
                    updateStatement.setString(col++,gender.name());
                    updateStatement.setString(col++,job);
                    updateStatement.setInt(col++,id);
                    updateStatement.executeUpdate();
                }

                System.out.println("Count for person with ID "+ job+ " is " +  count);

            }
            insertStatement.close();
            checkStmt.close();
            updateStatement.close();
        } catch (SQLException throwable) {
           throw throwable;
        }
    }

    public void load() throws SQLException {
        persons.clear();
        try {
            String sql = "SELECT * FROM persons";
            Statement selectStatement = conn.createStatement();

            ResultSet res = selectStatement.executeQuery(sql);

            while(res.next()){
                int id =  res.getInt("id");
                String name =  res.getString("name");
                String gender =  res.getString("gender");
                String age =  res.getString("age");
                String job =  res.getString("job");
                String sin =  res.getString("sin");
                boolean is_citizen =  res.getBoolean("is_citizen");
                String subject =  res.getString("subject");

                Person p = new Person(id,name,job,AgeGroup.valueOf(age),Subject.valueOf(subject),sin,is_citizen,Gender.valueOf(gender));
                persons.add(p);
                System.out.println(p);
            }
        } catch (SQLException throwable) {
            throw throwable;
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
