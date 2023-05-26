package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
public void  usertype_page(View view)
    {
        Intent  intent = new Intent(MainActivity.this, User_type_menu.class);//going to user select type
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}