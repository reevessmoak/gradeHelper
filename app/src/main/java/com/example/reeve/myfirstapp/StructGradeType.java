package com.example.reeve.myfirstapp;

import java.util.ArrayList;

public class StructGradeType {

    private String name;
    private String typename;
    private ArrayList<StructAssign> assignlist = new ArrayList<>();

    StructGradeType(){
        name = "unnamed_homework";
        typename = "unnamed_type";
    }

    public String getName(){
        return this.name;
    }

    public String getTypeName(){
        return this.typename;
    }

    public void setName(String newname){
        this.name = newname;
    }

    public void setTypename(String newtypename){
        this.typename = newtypename;
    }

    public ArrayList<StructAssign> getAssignlist() {
        return assignlist;
    }

    public void addAssign(StructAssign assign){
        this.assignlist.add(assign);
    }

    public void removeAssign(int i) {
        assignlist.remove(i);
    }
}
