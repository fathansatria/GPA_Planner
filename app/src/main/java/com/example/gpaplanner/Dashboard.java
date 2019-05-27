package com.example.gpaplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    private CardView profileBtn;
    private CardView coursesBtn;
    private CardView gpaBtn;
    private CardView notesBtn;
    private CardView settingBtn;
    private CardView logoutBtn;
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        account = intent.getStringExtra("username");

        profileBtn = findViewById(R.id.btn_profile);
        coursesBtn = findViewById(R.id.btn_mycourses);
        gpaBtn = findViewById(R.id.btn_gpa);
        logoutBtn = findViewById(R.id.btn_gpa);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });
        coursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCoursesList();
            }
        });
        gpaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGpa();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });



    }

    private void goToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", account);
        startActivity(intent);
    }

    private void goToCoursesList() {
        Intent intent = new Intent(this, CourseList.class);
        intent.putExtra("username", account);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", account);
        startActivity(intent);
    }

    private void goToGpa() {
        Intent intent = new Intent(this, GPAActivity.class);
        intent.putExtra("username", account);
        startActivity(intent);
    }
}
