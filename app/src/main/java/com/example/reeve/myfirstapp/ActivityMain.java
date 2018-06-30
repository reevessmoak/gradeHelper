package com.example.reeve.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private ListView courselist;
    private Button coursebutton;
    public ArrayList<StructCourse> classes;
    public CustomListAdapter adapter;

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load Session
        loadData();

        //init UI elements
        courselist = (ListView) findViewById(R.id.main_listview_courselist);
        coursebutton = (Button) findViewById(R.id.main_button_coursecreate);
        adapter = new CustomListAdapter();
        courselist.setAdapter(adapter);

        //set values
        setTitle("Course List");

        //Init Listeners
        onCourseCreate();
        onListClick();
    }

    class CustomListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return classes.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {//setup custom list xml
            view = getLayoutInflater().inflate(R.layout.courselist_layout, null);
            TextView tv = (TextView) view.findViewById(R.id.coursenames);
            tv.setText(classes.get(i).getName());
            return view;
        }
    }




    public void onListClick(){//When a course is clicked
        courselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Save to preferences
                saveData();

                //Prepare new activity
                Intent intent = new Intent(ActivityMain.this, ActivityCourseEditor.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
    }

    public void onCourseCreate() {//create a course button
        coursebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create blank StructCourse
                StructCourse newCourse = new StructCourse();
                classes.add(newCourse);

                //Save new class structure
                saveData();

                //Prepare new activity
                Intent intent = new Intent(ActivityMain.this, ActivityCourseEditor.class);
                intent.putExtra("position", classes.size()-1);
                startActivity(intent);
            }
        });
    }

    public void saveData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(classes);
        editor.putString("class list", json);
        editor.apply();
    }

    private void loadData(){

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

