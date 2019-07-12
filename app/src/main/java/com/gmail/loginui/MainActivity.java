package com.gmail.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gmail.loginui.R.layout;

public class MainActivity extends AppCompatActivity {


    RelativeLayout rellay1, rellay2;
    Handler k = new Handler();
    Runnable m = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);
        k.postDelayed(m, 4000);
        SignUp();

        Button sign_in=findViewById(R.id.btnSignIn);
        final EditText a= findViewById(R.id.EdittxtUser);
        final EditText b= findViewById(R.id.Edittxtpassword);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = a.getText().toString().trim();
                String pwd = b.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if (res == true){
                    Toast.makeText(MainActivity.this,"Redirecting redirecting you welcome:  "+user,Toast.LENGTH_SHORT).show();
                    Intent HomePage = new Intent(MainActivity.this,home_activity.class);
                    startActivity(HomePage);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error! Wrong Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SignUp() {
        Button Signup = findViewById(R.id.btn_sign_up);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });
    }
}
