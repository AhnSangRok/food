package com.example.week01_homework.person;

public class Prac {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("안상록");
        person.setAge(25);
        person.setAddress("경상북도");
        person.setJob("자택경비원");

        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getAddress());
        System.out.println(person.getJob());
    }
}
