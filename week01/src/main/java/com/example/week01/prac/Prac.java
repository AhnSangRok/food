package com.example.week01.prac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prac {

    public static void main(String[] args) {
        Tutor tutor = new Tutor();
        tutor.setName("남병관");
        tutor.setBio(15);
        System.out.println(tutor.getName());
        System.out.println(tutor.getBio());
    }
}
