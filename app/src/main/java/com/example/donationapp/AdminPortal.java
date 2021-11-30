package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminPortal extends AppCompatActivity {
    ImageButton backB;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        backB=findViewById(R.id.backB);
        backB.setOnClickListener(view -> startActivity(new Intent(getApplication(),LoginActivity.class)));
    }
    public void openofferdonation(View view) {
        Intent a = new Intent(AdminPortal.this, DonationOfferActivity.class);
        startActivity(a);
    }

    public void openReqDonation(View view) {
        Intent a = new Intent(AdminPortal.this, AddDonReq.class);
        startActivity(a);
    }

    public void openRDonationRequested(View view) {
        Intent a = new Intent(AdminPortal.this, ShowDonReq.class);
        startActivity(a);
    }
    public void openMedHelp(View view) {

        try {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.vmac.chatbot");
            startActivity(launchIntent);
        } catch (Exception e) {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(AdminPortal.this);

            builder.setMessage("There was no app found to open this");
            builder.setTitle("Alert !");
            builder.setCancelable(false);
            builder
                    .setPositiveButton(
                            "Okay",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void onExit(View view) {
        finishAffinity();
        System.exit(0);
    }
    public void openChatboard(View view) {
        Intent a = new Intent(AdminPortal.this, ChatboardActivity.class);
        startActivity(a);
    }
}
