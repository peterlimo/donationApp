
package com.example.donationapp;
        import androidx.appcompat.app.AppCompatActivity;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
public class ChatboardActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatboard);
        //taking reference to edittext
          TextView message = findViewById(R.id.message);
         //taking reference to button
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          try {
                                              String text = " ";// Replace with your message.
                                              String toNumber = "254706352557";
                                              // Replace with mobile phone number without +Sign or leading zeros, but with country code
//Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
                                              Intent intent = new Intent(Intent.ACTION_VIEW);
                                              intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                                              startActivity(intent);
                                          } catch (Exception e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  });

        /** submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String message = messageEditText.getText().toString();
                 sendMessage(message);
                  {
                  PackageManager pm = ChatboardActivity.this.getPackageManager();
                  try {
                  Intent waIntent = new Intent(Intent.ACTION_SEND);
                  //waIntent.setType("text/plain");
                  String text = "YOUR TEXT HERE";
                 // String toNumber="254724443578";
                  PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                  waIntent.setPackage("com.whatsapp");
                 // waIntent.putExtra(Intent.EXTRA_TEXT, text);
                  startActivity(Intent.createChooser(waIntent, "Share with"));
                  } catch (PackageManager.NameNotFoundException e)

             {

                  }
                  }
             }
             });
     }
     private void sendMessage(String message) {
         // Creating new intent
         Intent intent = new Intent(Intent.ACTION_SEND);
         String toNumber = "254724443578";
         intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+message));
         intent.setPackage("com.whatsapp");
 // Give your message here
         intent.putExtra(Intent.EXTRA_TEXT, message);
 // Checking whether Whatsapp
 // is installed or not
         if (intent.resolveActivity(getPackageManager()) == null) {
             Toast.makeText(this, "Please install whatsapp first.", Toast.LENGTH_SHORT).show();
             return;
         }
 // Starting Whatsapp
                 startActivity(intent);**/



}}