package gui;

import java.util.EventObject;

public class FormEvent  {

    private String name;
    private String job;
    private int age;
    private String subject;
    private String sinNumber;
    private boolean isCitizen;
    private String gender;

    public FormEvent(Object source) {
        //super(source);
    }

    public FormEvent(
            Object source,
            String name,
            String job,
            int age,
            String subject,
            String sinNumber,
            boolean isCitizen,
            String gender
    ) {
       // super(source);
        this.name = name;
        this.job = job;
        this.age = age;
        this.subject = subject;
        this.sinNumber = sinNumber;
        this.isCitizen = isCitizen;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }

    public boolean isCitizen() {
        return isCitizen;
    }

    public void setCitizen(boolean citizen) {
        isCitizen = citizen;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
