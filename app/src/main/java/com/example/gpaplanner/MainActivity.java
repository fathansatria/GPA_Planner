package com.example.gpaplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "LoginOrSignUp";
    DatabaseHelper mDatabaseHelper = null;
    EditText edtloginUsername, edtPassLogin, edtNim;
    private ImageButton btnSignup, btnLogin;
    private Button btnToSign;
    private String loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper.deleteDatabase(this);


        edtloginUsername = findViewById(R.id.et_usernamelogin);
        edtPassLogin = findViewById(R.id.et_passwordlogin);
        btnLogin = findViewById(R.id.bt_login);
        btnToSign = findViewById(R.id.bt_toSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Student> allStudent = mDatabaseHelper.getAllStudents();
                String currentUser = edtloginUsername.getText().toString();
                String currentPass = edtPassLogin.getText().toString();
                boolean login = false;

                for (Student std : allStudent) {

                    if(std.getUsername().equals(currentUser)){
                        if(std.getPassword().equals(currentPass)){

                            Toast.makeText(MainActivity.this,"Login Succesfull", Toast.LENGTH_LONG).show();
                            login = true;
                            loginUser = std.getUsername();

                        }
                        if(login){ break; }
                    }

                }

                if(login){
                    goToDashboard();
                }


            }
        });


        btnToSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignup();
            }
        });

        mDatabaseHelper.closeDB();

    }
    public void goToSignup(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void goToDashboard(){
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("username", loginUser);
        startActivity(intent);
    }
}
