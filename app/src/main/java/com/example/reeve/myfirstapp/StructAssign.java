package com.example.reeve.myfirstapp;

public class StructAssign {

    private String name;
    private double grade;

   StructAssign(){
        name = "unnamed_assignment";
        grade = 0;
    }

    public String getName(){
        return this.name;
    }

    public Double getGrade(){
        return this.grade;
    }

    public void setName(String newname){
        this.name = newname;
    }

    public void setGrade(Double newgrade){
        this.grade = newgrade;
    }

}
