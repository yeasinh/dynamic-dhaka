package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class balance_add extends AppCompatActivity {

    String ids;
    Double bal,balance;
    EditText amnrt;
    EditText account_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_add);

    }
    public void datas(View v)
    {
        amnrt=(EditText)findViewById(R.id.amnt);
        account_id=(EditText)findViewById(R.id.acid);

        ids=account_id.getText().toString();
        System.out.println("id of account is "+ids);


        DatabaseReference ac;
        ac=FirebaseDatabase.getInstance().getReference().child("Bank_Account").child("1").child("balance");
        ac.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                balance=Double.parseDouble(snapshot.getValue().toString());
                System.out.println("account balance"+ balance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void addition(View v)
    {

      bal=Double.parseDouble(amnrt.getText().toString());
        balance += bal;
        Bank_Account inac=new Bank_Account();
        inac.setAccount_id(Integer.parseInt(ids));
        inac.setBalance(balance);
        FirebaseDatabase acc=FirebaseDatabase.getInstance();
        DatabaseReference accin=acc.getReference("Bank_Account");
        accin.child(ids).setValue(inac);
        Intent intent = new Intent(balance_add.this, counter_master_first_menu.class);

        startActivity(intent);
    }


    ////

        ///
}