package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donationapp.adapters.DonationsAdapter;
import com.example.donationapp.adapters.NotificationsAdapter;
import com.example.donationapp.data.Cloth;
import com.example.donationapp.data.ClothData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DonationOfferActivity extends AppCompatActivity implements NotificationsAdapter.OnItemClickListener{
    TextView offerretrieve;

    DatabaseReference databaseReference;
    ArrayList<Cloth> list ;
    RecyclerView recyclerView;
    NotificationsAdapter adapter;
    List<ClothData> l=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_offer);
        list= new ArrayList<>();
        adapter=new NotificationsAdapter(l,this,this);
        offerretrieve = findViewById(R.id.offerretrieve);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.donation_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        getData();
    }

    private void getData() {
        databaseReference.child("ClothesInfo")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1: snapshot.getChildren()){
                       String type=snapshot1.child("clothesType").getValue(String.class);
                            String info=snapshot1.child("clothesDes").getValue(String.class);
                            String quan=snapshot1.child("clothesQuantity").getValue(String.class);
                            String size=snapshot1.child("clothesSizes").getValue(String.class);

                            String url=snapshot1.child("url").getValue(String.class);
                            if (url.isEmpty())
                            {
                                return;
                            }
                            ClothData clothData=new ClothData(type,quan,info,size,url);
                            l.add(clothData);

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