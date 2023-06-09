package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Auth_first_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_first_menu);
    }
    public void Auth_Login(View view)
    {
        //EditText name=(EditText)findViewById(R.id.staff_name);
        EditText password=(EditText)findViewById(R.id.Auth_pass);
        //String u_name=name.getText().toString();
        String p=password.getText().toString();
        //name.setText("");
        password.setText("");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Auth_Pass");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                {
                    String s=snapshot.getValue().toString();
                    if(s.equals(p))
                    {      Intent intent = new Intent(Auth_first_menu.this, Auth_menu.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Login Successful !",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                    }
                }
                  
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}