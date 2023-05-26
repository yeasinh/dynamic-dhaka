package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pass_first_menu extends AppCompatActivity {
public void PassSignUp(View view)
{
    Intent intent = new Intent(Pass_first_menu.this, Pass_signup.class);//going to pass signup menu
    startActivity(intent);
}
public void PassLogin(View view)
{
    Intent intent = new Intent(Pass_first_menu.this, Pass_login_page.class);//going to pass login menu
    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_first_menu);
    }
}