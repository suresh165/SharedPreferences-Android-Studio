package com.suresh.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suresh.sharedpreferences.DbUtils.LocalDB;
import com.suresh.sharedpreferences.DbUtils.User;

public class MainActivity extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPasssword;
    private Button btnLogin;
    private Button btnSignUp;
    private final String Credential_SHARE_PRE = "Our shareed pref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername = findViewById(R.id.ed_uername);
        edPasssword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*
                    SharedPreferences credentials = getSharedPreferences(Credential_SHARE_PRE, Context.MODE_PRIVATE);
                    String strUsername = credentials.getString("Username",null);
                    String strPassword = credentials.getString("Password" ,null); */
                final String username_from_ed = edUsername.getText().toString();
                final String password_from_ed = edPasssword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocalDB dbInstance = RoomImplementation.getmInstance().getDbInstantce();
                        User user = dbInstance.userDao().getUserByUsername(username_from_ed);

                        if (user != null && user.getUsername() !=null && username_from_ed != null && user.getUsername().equalsIgnoreCase(username_from_ed)) {
                            if (user.getPassword() != null && password_from_ed != null && user.getPassword().equalsIgnoreCase(password_from_ed)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}