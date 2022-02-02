package com.example.donationapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class LoginActivity extends AppCompatActivity {
    Button signupbt,loginnn;
    EditText id ,pass;
    FirebaseAuth firebaseAuth;
    ImageView image;
    String user,type;
    public void openReset(View view) {
        Intent a = new Intent(LoginActivity.this,ResetPassActivity.class);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupbt = findViewById(R.id.signup);
        loginnn = findViewById(R.id.loginbtn);
        id = findViewById(R.id.emailaddresslg);
        pass = findViewById(R.id.passwordlg);
        image = findViewById(R.id.image);
        type=getIntent().getStringExtra("type");
        user=getIntent().getStringExtra("user");

        signupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActivity.this,SignUp.class);
                startActivity(a);
            }
        });
        firebaseAuth =FirebaseAuth.getInstance();
        loginnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Emailid = id.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if(TextUtils.isEmpty(Emailid)){
                    Toast.makeText(LoginActivity.this,"Please Enter Emailid",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                final ACProgressFlower dialoglogin = new ACProgressFlower.Builder(LoginActivity.this)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .themeColor(Color.WHITE)
                        .text("Logging In")
                        .fadeColor(Color.DKGRAY).build();
                dialoglogin.show();
                firebaseAuth.signInWithEmailAndPassword(Emailid, Password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    String uid=task.getResult().getUser().getUid();
                  FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                  firebaseDatabase.getReference().child("NewUsers").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          int usertype=snapshot.getValue(Integer.class) ;
                          if (usertype==1){
                              Intent a = new Intent(LoginActivity.this,AdminPortal.class);
                              startActivity(a);
                          }
                         else {
                             if(usertype==0) {
                                 Intent a = new Intent(LoginActivity.this,valuedactivity.class);
                                 startActivity(a);
                             }
                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
    dialoglogin.dismiss();
                                     Toast.makeText(LoginActivity.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new  Intent(LoginActivity.this,valuedactivity.class);
                                     startActivity(intent);
                                    finish();
                                  }
                                    else if(!firebaseAuth.getCurrentUser().isEmailVerified()){
                                     Toast.makeText(LoginActivity.this, "Please Verify your email first", Toast.LENGTH_SHORT).show();
                                   }
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                                dialoglogin.dismiss();
                            }
                        });

            }
        });
    }
}
