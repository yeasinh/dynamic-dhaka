package com.example.dynamic_dhaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class assistent_first_menu extends AppCompatActivity {
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistent_first_menu);
        s=getIntent().getStringExtra("user_id");
        System.out.println("the id is "+s);
    }
    public void check_schedule(View v)
    { System.out.println("entered show driver schedule");
        Intent intent = new Intent(assistent_first_menu.this, check_schedule.class);
        intent.putExtra("user_id",s);

        startActivity(intent);
    }
    
}