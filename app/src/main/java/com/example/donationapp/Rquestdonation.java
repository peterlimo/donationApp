package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

public class Rquestdonation extends AppCompatActivity {
    CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5;
    private EditText firstname;
    private EditText lastname;
    private EditText address;
    private EditText members;
    private EditText emailaddress;
    private EditText phone;
    private EditText other;
    ImageView back_icon;
    TextView title,don;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    //creating variables for  our reference for firebase
    DatabaseReference databaseReference;
    RequestInfo requestInfo;

    public Rquestdonation() {
    }
    //private Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rquestdonation);
       firstname= findViewById(R.id.firstname);
        lastname= findViewById(R.id.lastname);
        emailaddress= findViewById(R.id.emailaddress);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        members = findViewById(R.id.members);
        other = findViewById(R.id.other);
        checkBox= findViewById(R.id.checkBox);
        checkBox2= findViewById(R.id.checkBox2);
        checkBox3= findViewById(R.id.checkBox3);
        checkBox4= findViewById(R.id.checkBox4);
        checkBox5= findViewById(R.id.checkBox5);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        don = findViewById(R.id.don);
        back_icon= findViewById(R.id.back_icon);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RequestInfo");
        //initialise ou object

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), valuedactivity.class));
            }
        });
        requestInfo = new RequestInfo();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Firstname = firstname.getText().toString();
                final String Lastname = lastname.getText().toString();
                final String Emailaddress = emailaddress.getText().toString();
                final String Address = address.getText().toString();
                final String Phone = phone.getText().toString();
                final String Members = members.getText().toString();
                final String Other = other.getText().toString();
                if(Firstname.isEmpty())
                    firstname.setError("enter firstname");
                if(Lastname.isEmpty())
                    lastname.setError("enter lastname");
                if(Emailaddress.isEmpty())
                    emailaddress.setError("enter emailaddress");
                if(Address.isEmpty())
                    address.setError("enter address");
                if(Phone.isEmpty())
                    phone.setError("enter phone number");
                if(Members.isEmpty())
                    members.setError("enter members of your family");
                if(Other.isEmpty())
                    other.setError("specify others");

                if (TextUtils.isEmpty(Firstname) && TextUtils.isEmpty(Lastname) && TextUtils.isEmpty(Emailaddress)
                        && TextUtils.isEmpty(Address) && TextUtils.isEmpty(Phone) && TextUtils.isEmpty(Members)
                        && TextUtils.isEmpty(Other)) {
                    Toast.makeText(Rquestdonation.this, "Please Add some data", Toast.LENGTH_SHORT).show(); }

                else {

                    //call the method to add data to our firebase
                    addDatatoFirebase(Firstname,  Lastname, Emailaddress, Phone,Address,Members,Other);
                }

            }

        });


    }


    private void addDatatoFirebase(String firstname, String lastname,String emailaddress,String phone,String address,String members,String other){
        //set data to our object class
        requestInfo.setFirstname(firstname);
        requestInfo.setLastname(lastname);
        requestInfo.setEmailaddress(emailaddress);
        requestInfo.setPhone(phone);
        requestInfo.setAddress(address);
        requestInfo.setMembers(members);
        requestInfo.setOther(other);

        //We are use add value event listener method which is called with database reference.
        databaseReference.push().setValue(requestInfo).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Rquestdonation.this, "data added" , Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Rquestdonation.this, "Fail to add data" , Toast.LENGTH_SHORT).show();
                    }
                });

    }

}

