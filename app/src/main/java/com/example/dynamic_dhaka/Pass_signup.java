package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pass_signup extends AppCompatActivity {
    EditText nid_name,Nid_no,Date,user_name,password,confirm;
    TextView sh;
    Boolean starter=true;//help to start the process if everything ok
     DatabaseReference reff,ref;
    final int[] serail = new int[1];
    final String[] sr = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_signup);
    }
    public void check_nid(View view)//Checking Nid infos
    {
        final String[] name = new String[1];
        final String[] Dob = new String[1];

        Nid_no=(EditText)findViewById(R.id.Nid_no);
        nid_name=(EditText)findViewById(R.id.Nid_name);
        Date=(EditText)findViewById(R.id.Dob);
    String no=Nid_no.getText().toString();
    String na=nid_name.getText().toString();
    String dob=Date.getText().toString();

  ref=FirebaseDatabase.getInstance().getReference().child("Database").child("NID");//connecting to NId database
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            if(snapshot.hasChild(no))//checking id no exists or not
            {

                if(true)
                {
                    ref= FirebaseDatabase.getInstance().getReference().child("Database").child("NID").child(no);//changing the reference to id no
                    {
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                name[0] =snapshot.child("Name").getValue().toString();//retriving name
                                Dob[0] =snapshot.child("Date of Birth").getValue().toString();//retriving dob
                                //Toast.makeText(getApplicationContext(),name[0],Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),na,Toast.LENGTH_SHORT).show();

                                //Toast.makeText(getApplicationContext(),Dob[0],Toast.LENGTH_SHORT).show();
                                if(!na.equals(name[0]))//checking the names
                                {
                                    starter=false;
                                    Toast.makeText(getApplicationContext(),"NID NAME Doesnot Match",Toast.LENGTH_SHORT).show();
                                }
                                if(!dob.equals(Dob[0]))//checking the Dobs
                                {
                                    starter=false;
                                    Toast.makeText(getApplicationContext(),"Date of Birth Doesnot Match",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });}
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"NID Number Is Not Present In National Database",Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });






    }
    public void check_pass(View view)//check the constraints of password
    {
        password=(EditText)findViewById(R.id.FirstPass);
        String s=password.getText().toString();
        int up=0,lo=0,nu=0;
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch>='A'&&ch<='Z')//counting the uppper case letters
            {
                up++;
            }
            else if(ch>='a'&&ch<='z')//counting the lowercase letters
            {
                lo++;
            }
            else if(ch>='0'&&ch<='9')//counting the numbers
            {
                nu++;
            }
        }
        if(up>=2&&lo>=2&&nu>=2)
        {

        }
        else
        {
            starter=false;
            Toast.makeText(getApplicationContext(),"Please Follow The Password Pattern",Toast.LENGTH_SHORT).show();
        }
    }
    public void confirm_pass(View view)
    {
        password=(EditText)findViewById(R.id.FirstPass);
        confirm=(EditText)findViewById(R.id.SecondPass);
        String s1=password.getText().toString();
        String s2=confirm.getText().toString();
        if(!s1.equals(s2))
        {
            starter=false;
            Toast.makeText(getApplicationContext(),"Password Does not Match.Check the Password",Toast.LENGTH_SHORT).show();
        }



    }
    public void show_id(View view)//it will generate id and show it to user
    {

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("PassSerial");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sr[0]=snapshot.child("UID").getValue().toString();
                System.out.println(sr[0]+" found from data");
                serail[0]=Integer.parseInt(sr[0]);
                int t=serail[0]+1;
                String te=Integer.toString(t);
                sh=(TextView) findViewById(R.id.sh_id);
                te="Your Id is : "+te;
                Toast.makeText(getApplicationContext(),te,Toast.LENGTH_LONG).show();
                sh.setText(te);
                starter=true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void set(View view) {
        Nid_no = (EditText) findViewById(R.id.Nid_no);
        String a = Nid_no.getText().toString();
        DatabaseReference nid=FirebaseDatabase.getInstance().getReference().child("nid to id");
        nid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(a))
                {
                    Toast.makeText(getApplicationContext(),"Account Already Exits!",Toast.LENGTH_SHORT).show();
                    starter=false;
                }
                else
                {
                    if (starter) {




                        nid_name = (EditText) findViewById(R.id.Nid_name);

                        Date = (EditText) findViewById(R.id.Dob);
                        user_name = (EditText) findViewById(R.id.UserName);
                        password = (EditText) findViewById(R.id.FirstPass);
                        confirm = (EditText) findViewById(R.id.SecondPass);
                        String  b, c, d, e, f;

                        b = nid_name.getText().toString();
                        c = user_name.getText().toString();
                        d = password.getText().toString();





                        //Toast.makeText(getApplicationContext(),Integer.toString(serail[0])+"before increse",Toast.LENGTH_SHORT).show();
                        serail[0] = serail[0] + 1;
                       // Toast.makeText(getApplicationContext(),Integer.toString(serail[0])+" after increase",Toast.LENGTH_SHORT).show();
                        int temp = serail[0];
                        sr[0] = Integer.toString(serail[0]);
                        PassDataInsert obj = new PassDataInsert(sr[0], a, c, b, d);
                        FirebaseDatabase uid = FirebaseDatabase.getInstance();
                        DatabaseReference node = uid.getReference("PassSerial");
                        Toast.makeText(getApplicationContext(),sr[0]+"  inserting to database ",Toast.LENGTH_SHORT).show();
                        node.child("UID").setValue(sr[0]);

                        /////////////////creating account
                        Bank_Account inac=new Bank_Account(serail[0]);
                        FirebaseDatabase acc=FirebaseDatabase.getInstance();
                        DatabaseReference accin=acc.getReference("Bank_Account");
                        accin.child(sr[0]).setValue(inac);
                        ////////////////Account creation finished

                       FirebaseDatabase nid_id=FirebaseDatabase.getInstance();
                       DatabaseReference nid_nod=nid_id.getReference("nid to id");
                       nid_nod.child(a).setValue(sr[0]);
                        FirebaseDatabase pass = FirebaseDatabase.getInstance();
                        DatabaseReference insert = pass.getReference("Passengers");//inserting to data base
                        insert.child(sr[0]).setValue(obj);
                        nid_name.setText("");
                        Nid_no.setText("");
                        user_name.setText("");
                        password.setText("");
                        confirm.setText("");
                        Date.setText("");
                        Toast.makeText(getApplicationContext(), "Signed Up Successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Pass_signup.this, Pass_first_menu.class);
                        startActivity(intent);


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Some Info Are Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}