package com.gmail.loginui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    DatabaseHelper xy;
    EditText mtxtUser;
    EditText mtxtPass;
    EditText mtxtConfirmPass;
    View mLogin;
    Button mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        xy=new DatabaseHelper(this);

        mtxtUser=findViewById(R.id.txtUser);
        mtxtPass=findViewById(R.id.txtPass);
        mtxtConfirmPass=findViewById(R.id.txtConfPass);
        mLogin=findViewById(R.id.viewLogin);
       mCreateAccount=findViewById(R.id.btnCreateAccount);

       mLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent BackToLogin= new Intent(Signup.this,MainActivity.class);
               startActivity(BackToLogin);
           }
       });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mtxtUser.getText().toString().trim();
                String pwd = mtxtPass.getText().toString().trim();
                String conf_pwd = mtxtConfirmPass.getText().toString().trim();

                if (pwd.equals(conf_pwd)){
                    long val = xy.addUser(user,pwd);
                    if (val>0) {
                        Toast.makeText(Signup.this, "Successfully Registered welcome to BOTP", Toast.LENGTH_SHORT).show();
                        Intent MoveToLogin = new Intent(Signup.this, MainActivity.class);
                        startActivity(MoveToLogin);
                    }
                    else
                    {
                        Toast.makeText(Signup.this,"Registration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Signup.this,"Password do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
