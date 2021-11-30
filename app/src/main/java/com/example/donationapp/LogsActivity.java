package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LogsActivity extends AppCompatActivity {

    ImageView logo;
    TextView text,text2;
    Button user,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        logo=findViewById(R.id.logo);
        text=findViewById(R.id.text);
        text2=findViewById(R.id.text2);
        user=findViewById(R.id.user);
        admin=findViewById(R.id.admin);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("user","user");
                i.putExtra("type","0");
            startActivity(i);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("user","admin");
                i.putExtra("type","1");
                startActivity(i);
            }
        });



    }


}
