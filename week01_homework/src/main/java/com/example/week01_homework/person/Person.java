package com.example.week01_homework.person;

public class Person {
    private String name;
    private int age;
    private String address;
    private String job;

    public Person(){

    }
    public Person(String name, int age, String address, String job){
        this.name = name;
        this.age = age;
        this.address = address;
        this.job = job;
    }

    //getter
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getJob() {
        return job;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setJob(String job) {
        this.job = job;
    }
}
