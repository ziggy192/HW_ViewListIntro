package com.example.nghia.listviewintro.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 11/8/2016.
 */

public class Student implements Serializable{

    public static final int OP_ADD = 0;
    public static final int OP_UPDATE = 1;

    public static final Student[] STUDENTS = {
            new Student("Luu","female",15),
            new Student("Nghia","male",13)
            ,new Student("Duc","male",11)
            ,new Student("Trung","male",11)
    };


    public static ArrayList<Student> names  = new ArrayList<>(Arrays.asList(STUDENTS));


    private  String name;
    private String gender;
    private int age;

    public Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Student() {
        name = new String();
        gender = new String();
        age = -1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }


    public static void printStudentList(){
        System.out.println("Student list: ");
        for (Student student: names
             ) {
            System.out.print(student + " ");
        }
    }
}

