package com.example.gpaplanner;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    private ListView courseList;
    private List<Course> courses;
    private ListAdapter listAdapter;
    private FloatingActionButton addCourse;
    Dialog addDialog;
    private String account;
    String newName,newLect;
    int newCredit;
    private DatabaseHelper db;
    private EditText newCourse, newLecturer, newSks;
    private ImageButton btncancel,btnadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        db = new DatabaseHelper(this);
        addCourse = findViewById(R.id.fab_add);
        addDialog = new Dialog(this);
        addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        addDialog.setContentView(R.layout.add_course_pop_up);

        btncancel = (ImageButton)addDialog.findViewById(R.id.btn_cancel);
        btnadd = (ImageButton)addDialog.findViewById(R.id.btn_add_course);
        newCourse = (EditText)addDialog.findViewById(R.id.et_courseName);
        newLecturer = (EditText)addDialog.findViewById(R.id.et_courseTeacher);
        newSks = (EditText)addDialog.findViewById(R.id.et_courseSks);
        Intent intent = getIntent();
        account = intent.getStringExtra("username");

        courses = db.getAllCoursesByUsername(account);
        courseList = findViewById(R.id.list_courses);
        listAdapter = new ListAdapter(this, courses);
        courseList.setAdapter(listAdapter);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });

        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String courseName = courses.get(i).getCourseName();
                String courseUser = courses.get(i).getUser();
                Toast.makeText(getApplicationContext(), courseName, Toast.LENGTH_LONG).show();
                goToDetail(courseName, courseUser);

            }
        });

        db.closeDB();

    }

    public void showPopUp(){


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog.dismiss();
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newName = newCourse.getText().toString();
                newLect = newLecturer.getText().toString();
                newCredit = Integer.valueOf(newSks.getText().toString());

                Course c1 = new Course();
                c1.setCourseName(newName);
                c1.setTeacher(newLect);
                c1.setSks(newCredit);

                db.createCourse(c1,account);
                addDialog.dismiss();

                recreate();
            }
        });

        addDialog.show();


    }

    void goToDetail(String coursename, String user){

        Intent intent = new Intent(this,CourseDetail.class);
        intent.putExtra("coursename", coursename);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
