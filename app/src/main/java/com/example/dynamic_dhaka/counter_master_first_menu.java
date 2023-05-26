package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class counter_master_first_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_master_first_menu);
    }
    public void add_balance(View v)
    {

    Intent intent = new Intent(counter_master_first_menu.this, balance_add.class);
    startActivity(intent);}
    public void logout(View v)
    {
        Intent intent = new Intent(counter_master_first_menu.this, Staff_Type.class);
        startActivity(intent);
    }
}