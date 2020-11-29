package com.suresh.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.suresh.sharedpreferences.DbUtils.LocalDB;
import com.suresh.sharedpreferences.DbUtils.User;


public class SignUpActivity extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPassword;
    private EditText edConformPassword;
    private Button btncreateuser;

    private final String Credential_SHARE_PRE  ="Our shareed pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername = findViewById(R.id.ed_uername);
        edPassword = findViewById(R.id.ed_password);
        edConformPassword = findViewById(R.id.ed_confirm_pwd);
        btncreateuser = findViewById(R.id.btn_create_user);

        btncreateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strPassword = edPassword.getText().toString();
                String strconformpassword = edConformPassword.getText().toString();
                final String strUsername = edUsername.getText().toString();

                if (strPassword !=null && strconformpassword !=null && strPassword.equalsIgnoreCase(strconformpassword)) {
  /*                  SharedPreferences credentials = getSharedPreferences(Credential_SHARE_PRE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = credentials.edit();
                    editor.putString("Password",strPassword);
                    editor.putString("Username",strUsername);

                    editor.commit();  */
                     new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user  = new User();
                            user.setUsername(strUsername);
                            user.setPassword(strPassword);
                            LocalDB dbInstance = RoomImplementation.getmInstance().getDbInstantce();
                            dbInstance.userDao().createUser(user);
                        }
                    }).start();
                    SignUpActivity.this.finish();
                }
            }
        });
    }
}