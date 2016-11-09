package com.example.surajbisht.loginaback4app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_pass;
    Button btn_sup, btn_sin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(new Parse.Configuration.Builder(MainActivity.this).applicationId("eqMNrQvJ1sowJHT5b3np28PY3mWe1yiRSpFZwKlK").clientKey("LjiM2rKBrhEkY2uaa4lsv7LFoSy7bfxyUtArUad2").server("https://parseapi.back4app.com/").build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_sin = (Button) findViewById(R.id.btn_sin);
        btn_sup = (Button) findViewById(R.id.btn_sup);
        et_username = (EditText) findViewById(R.id.et_username);
        et_pass = (EditText) findViewById(R.id.et_pass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logincheck()!=1) {
                    ParseUser.logInInBackground(String.valueOf(et_username.getText()),String.valueOf(et_pass.getText()), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null)
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        btn_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logincheck()!=1)
                {
                    ParseUser newuser = new ParseUser();
                    newuser.setUsername(et_username.getText().toString());
                    newuser.setPassword(et_pass.getText().toString());
                    newuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null)
                                Toast.makeText(MainActivity.this, "Sign up Successfull \nWelcome" + et_username.getText().toString(), Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, "Registration Failed\n " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }                            }
        });
    }

    public int logincheck() {
        int i=0;
        if (et_username.getText().toString().isEmpty()) {
            i=1;
            Toast.makeText(MainActivity.this, "You have not entered username ", Toast.LENGTH_SHORT).show();
        }
        else if (et_username.getText().toString() != null) {
            if (et_pass.getText().toString().isEmpty())
            {
                i=1;
                Toast.makeText(MainActivity.this, "You have not entered Password ", Toast.LENGTH_SHORT).show();
            }
        }
        return i;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}