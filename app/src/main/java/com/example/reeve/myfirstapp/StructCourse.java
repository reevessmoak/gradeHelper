package com.example.reeve.myfirstapp;

import java.util.ArrayList;

public class StructCourse { //constructor for Course types, consists of a list of assignment types (typeProfiles)

    private String name;
    public ArrayList<StructGradeType> gradeTypes = new ArrayList<>();
    private double finalGrade;
    private double maxgrade = 100;
    private double mingrade = 0;

    StructCourse() {//Default constructor
        name = "Unnamed";
        finalGrade = 0;
        StructGradeType hwList = new StructGradeType();
        gradeTypes.add(hwList);
    }

    public void setFinalGrade(double grade){
        this.finalGrade = grade;
    }

    private void addType(StructGradeType type) { //Add an assignment type, called a StructGradeType, to another list

        this.gradeTypes.add(type);
    }

    public String getName(){ return this.name;}

    public void setName(String newname){this.name = newname;}

    public ArrayList<StructGradeType> getGradeTypes() {
        return gradeTypes;
    }

    public void setGradeTypes(ArrayList<StructGradeType> newGradeTypes){
        this.gradeTypes = newGradeTypes;
    }


}
