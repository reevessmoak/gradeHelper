package com.example.reeve.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.reeve.myfirstapp.ActivityMain.SHARED_PREFS;

public class ActivityCourseEditor extends AppCompatActivity {

    private TextView courseTitle;
    private EditText courseName;
    private ExpandableListView hwlist;
    private Button returnBut;
    private Button removeBut;
    private Button typeBut;
    private Button assignBut;
    private ArrayList<StructCourse> classes = new ArrayList<>();
    private StructCourse course;
    private CourseEditorCustomEListAdapter myAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);

        //load session
        loadData();

        //init UI elements
        assignBut = (Button) findViewById(R.id.courseeditor_button_addassign);
        courseName = (EditText) findViewById(R.id.courseeditor_edittext_coursename);
        courseTitle = (TextView) findViewById(R.id.courseeditor_textview_coursename);
        hwlist = (ExpandableListView) findViewById(R.id.courseeditor_expandablelistview_main);
        removeBut = (Button) findViewById(R.id.courseeditor_button_removebut);
        returnBut = (Button) findViewById(R.id.courseeditor_button_returnbut);
        typeBut = (Button) findViewById(R.id.courseeditor_button_addtype);

        //set values
        course = classes.get(position);
        courseTitle.setText(classes.get(position).getName());
        course = classes.get(position);
        courseName.setText(course.getName());
        setTitle(course.getName());
        myAdapter = new CourseEditorCustomEListAdapter(this, course.getGradeTypes());
        hwlist.setAdapter(myAdapter);

        //init listeners
        onAddAssign();
        onAddGradeType();
        onGradeClick();
        onAssignClick();
        onRemoveClick();
        onReturnClick();

    }

    private void onRemoveClick(){

        removeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classes.remove(position);
                saveData();
                Intent intent = new Intent(ActivityCourseEditor.this, ActivityMain.class);
                startActivity(intent);
            }
        });
    } //Listener for the remove button
    private void onReturnClick(){ //when return is clicked
        returnBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save session
                saveData();

                //reset to home activity
                Intent intent = new Intent(ActivityCourseEditor.this, ActivityMain.class); //return to main
                startActivity(intent);
            }
        });

    } //Listener for the return button
    private void onAssignClick(){
        hwlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                CourseEditorCustomAlertDialog dialog = new CourseEditorCustomAlertDialog();
                dialog.show(getSupportFragmentManager(), "my_dialog");
                return false;
            }
        });

    }    //Listener for when gradeType group is clicked
    private void onGradeClick() {//When a course is clicked
        hwlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                CourseEditorCustomAlertDialog dialog = new CourseEditorCustomAlertDialog();
                dialog.show(getSupportFragmentManager(), "my_dialog");
                return false;
            }
        });

    } //Listener for when gradeType child is clicked

    private void onAddAssign(){
        assignBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StructAssign newAssign = new StructAssign();
                course.gradeTypes.get(0).addAssign(newAssign);

                saveData();
                myAdapter.notifyDataSetChanged();
            }
        });

    }
    private void onAddGradeType() {//create a course button
        typeBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create blank StructCourse
                StructGradeType newHw = new StructGradeType();
                course.gradeTypes.add(newHw);

                //Save new class structure
                saveData();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void saveData(){
        //save to struct
        classes.get(position).setName(courseName.getText().toString());

        //save struct to disk
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(classes);
        editor.putString("class list", json);
        editor.apply();
    }
    private void loadData(){
        //load course position
        Intent mIntent = getIntent();
        position = mIntent.getIntExtra("position", 0);

        //load struct from disk
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("class list", null);
        Type type = new TypeToken<ArrayList<StructCourse>>() {}.getType();
        classes = gson.fromJson(json, type);

        if(classes == null) {

            classes = new ArrayList<StructCourse>();

        }
    }
}
