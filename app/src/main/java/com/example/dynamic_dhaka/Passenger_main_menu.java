package com.example.dynamic_dhaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

public class Passenger_main_menu extends AppCompatActivity {
    ArrayList<String> rusid=new ArrayList<String>();
    String s;
    String l1,l2,l3,l4;
    String source;
    String destination;
    ArrayList<String> time_list=new ArrayList<String>();
    ArrayList<Schedule_info>schedule_list=new ArrayList<Schedule_info>();
    ArrayList<String>route_id_list=new ArrayList<String>();
    ArrayList<Location>route_lis=new ArrayList<Location>();
    private static final int REQUEST_CALL=1;
    final String[] serial = new String[1];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main_menu);
        s = getIntent().getStringExtra("user_id");//getting user id
        System.out.println(s);

        ///////////geting the route ids from base
        DatabaseReference ref_route;
        ref_route = FirebaseDatabase.getInstance().getReference().child("route");
        ref_route.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int i = 0;
                    rusid.add(i, "Choose the Route no");
                    for (DataSnapshot itr : snapshot.getChildren()) {
                        String t = itr.getKey().toString();
                        i++;
                        rusid.add(i, t);
                        System.out.println(rusid.get(i) + " is route id");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//////////////getting schedules for buy tickets

        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("Schedules");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int i = 0;
                    time_list.add(i, "Choose the Time");

                    for (DataSnapshot itr : snapshot.getChildren()) {
                        i++;

                        Schedule_info t1 = new Schedule_info();
                        t1 = itr.getValue(Schedule_info.class);
                        time_list.add(i, t1.getStart_time());///////string type of schedule
                        schedule_list.add(i - 1, t1);

                        System.out.println(t1.getBus_id() + "bus idss");


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ///////////////////////////////
        DatabaseReference refa;

        final int[] ids = new int[1];
        refa= FirebaseDatabase.getInstance().getReference().child("route_id");
        refa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serial[0] =snapshot.getValue().toString();
                ids[0] =Integer.parseInt(serial[0]);

                System.out.println(serial[0]+"is getiing evertything correct");



                //Toast.makeText(getApplicationContext(),serial[0]+"is the string",Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        int k=ids[0]+1;
        System.out.println(k +"is making  correct");

        ////////////getting the boarding point
        final int[] i = {0};
        for (int j = 1; j <=k+1; j++) {

            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference().child("route").child(Integer.toString(j));/////////route number use korte hbe
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        route_id_list.add(i[0], "Choose the Board Point");

                        for (DataSnapshot itr : snapshot.getChildren()) {
                            i[0]++;

                            Location t1 = new Location();
                            t1 = itr.getValue(Location.class);
                            route_id_list.add(i[0], t1.getLocation_name());///////////name of location
                            route_lis.add(i[0] - 1, t1);

                            System.out.println(t1.getLocation_name() + "location names idss");


                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            ///////////////////////////

        }
    }
    public void check_route(View v)
    {
        Intent intent = new Intent(Passenger_main_menu.this, pass_shoe_route.class);
        intent.putExtra("datas",rusid);
        startActivity(intent);

        //intent.putExtra("route_ids", (Serializable) rusid);
    }
    public  void buy_ticket(View v)
    {
        Intent intent = new Intent(Passenger_main_menu.this, Ticket_buying.class);
        startActivity(intent);
        intent.putExtra("user_id",s);
        intent.putExtra("schedule",time_list);
        intent.putExtra("location",route_id_list);
        intent.putExtra("datas",rusid);
        //intent.putExtra("locations",route_lis);
        //intent.putExtra("locations",route_lis);
       // Bundle bundle=new Bundle();
       // bundle.putSerializable("loca",route_lis);
        //intent.putExtras(bundle);
        ///////////////akhan thake location objext list  list pathate hbe
        startActivity(intent);
    }
    public  void  check_bus(View v)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("track_bus");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                l1=snapshot.child("l1").getValue().toString();
                l2=snapshot.child("l2").getValue().toString();
                l3=snapshot.child("l3").getValue().toString();
                l4=snapshot.child("l4").getValue().toString();
                String source= l1+","+l2;
                String destination= l3+","+l4;
                Uri gmmIntentUri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+destination);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mapIntent);
                Toast.makeText(getApplicationContext(),"Please select the first one ",Toast.LENGTH_LONG).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //System.out.println(source);





    }
    public void llog_out(View v)
    {
        Intent intent = new Intent(Passenger_main_menu.this, User_type_menu.class);
        startActivity(intent);
    }
    public void call(View v)
    {
        emergency_call();
    }
    public void emergency_call()
    {
      if(ContextCompat.checkSelfPermission(Passenger_main_menu.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
      {
          ActivityCompat.requestPermissions(Passenger_main_menu.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

      }
      else {
          String dial ="tel:"+"999";
          startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
      }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      if (requestCode==REQUEST_CALL)
      {
          if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
          {
              emergency_call();
          }
          else
          {
              Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
          }
      }
    }
}