package model;

import model.AgeGroup;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -1115239867237033577L;
    private static int count = 1;

    private int id;
    private String name;
    private String job;
    private AgeGroup ageGroup;
    private Subject subject;
    private String sinNumber;
    private boolean isCitizen;
    private Gender gender;

    public Person(String name, String job, AgeGroup ageGroup, Subject subject, String sinNumber, boolean isCitizen, Gender gender) {
        this.name = name;
        this.job = job;
        this.ageGroup = ageGroup;
        this.subject = subject;
        this.sinNumber = sinNumber;
        this.isCitizen = isCitizen;
        this.gender = gender;

        this.id = count;
        count++;

    }

    public Person(int id, String name, String job, AgeGroup ageGroup, Subject subject, String sinNumber, boolean isCitizen, Gender gender) {
        this(name,job,ageGroup,subject,sinNumber,isCitizen,gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return String.format("id: %d, name: %s",this.id,this.name);
    }
}
