package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ClothesActivity extends AppCompatActivity {
    //CircleImageView linearlayout;
   // Uri imageuri,placeholder;
    //private static final int CAMERA_REQUEST = 1888;
    //private  CircleImageView imageView;
   // private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ImageButton backB;
    TextView textView,textView13,textView14,textView16,textimage;
    private EditText editTextTextPersonName4, editTextTextPersonName6, editTextTextPersonName7, editTextTextPersonName8;
    Button button6,imageb;
    FirebaseDatabase firebaseDatabase;
    //creating variables for  our reference for firebase
    DatabaseReference databaseReference;
    ClothesInfo clothesInfo;
    Uri imageuri;
    public static final int IMAGE_CODE=1;
    //private Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName8 = findViewById(R.id.editTextTextPersonName8);
        backB = findViewById(R.id.backB);
        backB = findViewById(R.id.backB);
        textView = findViewById(R.id.textView);
        textimage = findViewById(R.id.textimage);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView16 = findViewById(R.id.textView16);
        button6 = findViewById(R.id.button6);
        imageb = findViewById(R.id.imageb);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ClothesInfo");
        //initialise ou object
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), DonorPerson.class));
            }
        });
        clothesInfo = new ClothesInfo();
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String clothesType = editTextTextPersonName4.getText().toString();
                final String clothesQuantity = editTextTextPersonName6.getText().toString();
                final String clothesSizes = editTextTextPersonName7.getText().toString();
                final String clothesDesc = editTextTextPersonName8.getText().toString();
                if(clothesType.isEmpty())
                    editTextTextPersonName4.setError("enter clothe type");
                if(clothesQuantity.isEmpty())
                    editTextTextPersonName6.setError("enter quantity");
                if(clothesSizes.isEmpty())
                    editTextTextPersonName7.setError("enter clothe sizes");
                if(clothesDesc.isEmpty())
                    editTextTextPersonName8.setError("write a description");
                if (TextUtils.isEmpty(clothesType) && TextUtils.isEmpty(clothesQuantity) && TextUtils.isEmpty(clothesSizes)
                        && TextUtils.isEmpty(clothesDesc)) {
                    Toast.makeText(ClothesActivity.this, "Please Add some data", Toast.LENGTH_SHORT).show(); }

                else {
                  saveImage();
                    //call the method to add data to our firebase
                   //addDatatoFirebase(clothesType, clothesQuantity, clothesDesc, clothesSizes);
                }
            }
        });
        imageb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openimageform();
            }
        });

    }

    private void openimageform() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_CODE );
    }
private void saveImage()
{
    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference ref=storage.getReference("clothImage");
    if (imageuri!=null)
    {
      ref.putFile(imageuri)
      .addOnSuccessListener(taskSnapshot -> {
          Toast.makeText(getApplicationContext(), "Image Added successfully!!", Toast.LENGTH_SHORT).show();
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                 String clothesTyp = editTextTextPersonName4.getText().toString();
                String clothesQuantit = editTextTextPersonName6.getText().toString();
                String clothesSize = editTextTextPersonName7.getText().toString();
                 String clothesDes = editTextTextPersonName8.getText().toString();
                addDatatoFirebase(clothesTyp, clothesQuantit, clothesDes, clothesSize,uri.toString());

            }
        }) ;
      })
      .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(ClothesActivity.this, "Failed to upload image!", Toast.LENGTH_SHORT).show();
          }
      });
    }
    else
    {
        Toast.makeText(this, "Please Select image to continue!!!", Toast.LENGTH_SHORT).show();
    }
}
    private void addDatatoFirebase(String clothesType, String clothesQuantity, String clothesDesc, String clothesSizes,String url){
        //set data to our object class
        clothesInfo.setClothesType(clothesType);
        clothesInfo.setClothesQuantity(clothesQuantity);
        clothesInfo.setClothesSizes(clothesSizes);
        clothesInfo.setClothesDesc(clothesDesc);
        clothesInfo.setUrl(url);
        //We are use add value event listener method which is called with database reference.
        databaseReference.push().setValue(clothesInfo).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ClothesActivity.this, "data added" , Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ClothesActivity.this, "Fail to add data" , Toast.LENGTH_SHORT).show();
            }
        });

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_CODE && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            imageuri=data.getData();

        }

    }
}

