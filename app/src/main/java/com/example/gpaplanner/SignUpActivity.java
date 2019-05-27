package com.example.gpaplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtName, edtProdi;
    private ImageButton btnSignup;
    private Button btnToLogin;
    private Student std;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDatabaseHelper = new DatabaseHelper(this);

        edtUsername = findViewById(R.id.et_username);
        edtPassword = findViewById(R.id.et_password);
        edtName = findViewById(R.id.et_name);
        edtProdi = findViewById(R.id.et_prodi);
        btnSignup = findViewById(R.id.bt_signup);
        btnToLogin = findViewById(R.id.bt_toLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String newUser = edtUsername.getText().toString();
                String newPass = edtPassword.getText().toString();
                String newName = edtName.getText().toString();
                String newProdi = edtProdi.getText().toString();

                std = new Student(newName,newUser,newPass,newProdi);
                mDatabaseHelper.registerStudent(std);
                Toast.makeText(SignUpActivity.this, "Account : " + std.getName() + " Has been Registered ", Toast.LENGTH_LONG).show();
                goToLogin();

            }

        });


        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

        mDatabaseHelper.closeDB();
    }
    public void goToLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
