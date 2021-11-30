package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.donationapp.data.request;
import com.example.donationapp.data.requestdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Requestdonation extends AppCompatActivity implements RequestAdapter.OnItemClickListener{
    TextView offerretrieve;

    DatabaseReference databaseReference;
    ArrayList<request> list ;
    RecyclerView recyclerView;
     RequestAdapter adapter;
    List<requestdata> l=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestdonation);
        list= new ArrayList<>();
        adapter=new RequestAdapter(l,this,this);
        offerretrieve = findViewById(R.id.offerretrieve);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.donation_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        getData();
    }

    private void getData() {
        databaseReference.child("RequestInfo")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1: snapshot.getChildren()){
                            String fname=snapshot1.child("firstname").getValue(String.class);
                            String lname=snapshot1.child("lastname").getValue(String.class);
                            String email=snapshot1.child("emailaddress").getValue(String.class);
                            String address=snapshot1.child("address").getValue(String.class);
                            String phone=snapshot1.child("phonenumber").getValue(String.class);
                            String mem=snapshot1.child("members").getValue(String.class);
                            String other=snapshot1.child("others").getValue(String.class);
                            requestdata rdata=new requestdata(fname,lname,email,address,phone,mem,other);
                            l.add(rdata);

                        }
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "cant read"+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onItemDetailsButtonClick(int position, View v) {

    }
}