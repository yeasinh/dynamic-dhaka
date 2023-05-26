package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pass_login_page extends AppCompatActivity {

FirebaseUser user;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_login_page);
    }


    public void login(View view)
    { EditText name=(EditText)findViewById(R.id.user_name);
        EditText password=(EditText)findViewById(R.id.User_pass);
        String u_name=name.getText().toString();
        String p=password.getText().toString();
        name.setText("");
        password.setText("");
       DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Passengers");
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.hasChild(u_name))
               {
                   String s=snapshot.child(u_name).child("password").getValue().toString();
                   if(s.equals(p))
                   {
                       Toast.makeText(getApplicationContext(),"Login Successful !",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(Pass_login_page.this, Passenger_main_menu.class);
                       intent.putExtra("user_id",u_name);//// sending the user id
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                   }
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Id Does not Exists !",Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });





    }
}