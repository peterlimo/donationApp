package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class FoodsActivity extends AppCompatActivity {
ImageButton backB;
TextView textView,textView13,textView14,textView16,textimage;
Button button6,imageb;
ImageView previewImage;
 private EditText editTextTextPersonName4, editTextTextPersonName6, editTextTextPersonName8;
 FirebaseDatabase firebaseDatabase;
//creating variables for  our reference for firebase
    DatabaseReference databaseReference;
    FoodInfo foodInfo;
    Uri imageuri;
    public static final int IMAGE_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        previewImage=findViewById(R.id.previewImage);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName8 = findViewById(R.id.editTextTextPersonName8);
        backB = findViewById(R.id.backB);
        imageb = findViewById(R.id.imageb);
        textimage = findViewById(R.id.textimage);
        textView = findViewById(R.id.textView);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView16 = findViewById(R.id.textView16);
        button6 = findViewById(R.id.button6);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("FoodInfo");
        //initialise ou object
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), MapsActivity.class));
            }
        });
        foodInfo = new FoodInfo();
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String foodType = editTextTextPersonName4.getText().toString();
                final String foodQuantity = editTextTextPersonName6.getText().toString();
                final String foodDesc = editTextTextPersonName8.getText().toString();
                if(foodType.isEmpty())
                    editTextTextPersonName4.setError("enter food type");
                if(foodQuantity.isEmpty())
                    editTextTextPersonName6.setError("enter quantity");
                if(foodDesc.isEmpty())
                    editTextTextPersonName8.setError("write a description");
                if (TextUtils.isEmpty(foodType) && TextUtils.isEmpty(foodQuantity) && TextUtils.isEmpty(foodDesc)) {
                    Toast.makeText(FoodsActivity.this, "Please Add some data", Toast.LENGTH_SHORT).show();
                } else {
                    //call the method to add data to our firebase
                    addDatatoFirebase(foodType, foodQuantity, foodDesc);
                }
            }
        });
        imageb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        IMAGE_CODE);
            }
        });
    }

    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(FoodsActivity.this, new String[] { permission }, requestCode);
        }
        else {
            openimageform();
        }
    }
    private void openimageform() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_CODE );
    }

    private void addDatatoFirebase(String foodType, String foodQuantity, String foodDesc){
        //set data to our object class
        foodInfo.setFoodType(foodType);
        foodInfo.setFoodQuantity(foodQuantity);
        foodInfo.setFoodDesc(foodDesc);
        //We are use add value event listener method which is called with database reference.
        databaseReference.push().setValue(foodInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(FoodsActivity.this, "data added" , Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FoodsActivity.this, "Fail to add data" , Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_CODE && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            imageuri=data.getData();
            previewImage.setImageURI(imageuri);
        }
    }

    // This function is called when user accept or decline the permission.
// Request Code is used to check which permission called this function.
// This request code is provided when user is prompt for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode ==IMAGE_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                openimageform();
            }
            else {
                Toast.makeText(getApplicationContext(), "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == 2) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

