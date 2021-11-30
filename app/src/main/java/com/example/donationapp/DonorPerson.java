package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DonorPerson extends AppCompatActivity {
    TextView money;
    TextView cloth;
    TextView food;
    ImageButton backB;
    ImageView money2,clot1,foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove toolbar and make the app full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_donor_person);
        money=findViewById(R.id.money);
        cloth=findViewById(R.id.cloth);
        food=findViewById(R.id.food);
        foods=findViewById(R.id.foods);
        clot1=findViewById(R.id.clot1);
        money2=findViewById(R.id.money2);
        backB=findViewById(R.id.backB);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),valuedactivity.class));
            }
        });
        money2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),MpesaActivity.class));
            }
        });

        clot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),ClothesActivity.class));
            }
        });
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),FoodsActivity.class));
            }
        });
    }
}