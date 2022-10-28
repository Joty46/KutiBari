package com.example.kutibari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationNew extends AppCompatActivity {
    /**
     * database reference
     * @param savedInstanceState
     */
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kutibari-9a550-default-rtdb.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_new);

        /**
         * code
         */
        final EditText mobile = findViewById(R.id.mobile);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
//        final EditText pas = findViewById(R.id.password);
        final EditText conpass = findViewById(R.id.conpass);


        final MaterialButton register = findViewById(R.id.signup);
        final TextView login = findViewById(R.id.loginnow);



        /**
         * registering process
         */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * get data into string form from edit text
                 */
                final String phone = mobile.getText().toString();
                final String user_name = username.getText().toString();
                final String[] pass = {password.getText().toString()};
                final String confirm = conpass.getText().toString();


                /**
                 * checking if all fields are fulfilled correctly
                 */
                if(user_name.isEmpty() || phone.isEmpty() || pass[0].isEmpty() ){
                    Toast.makeText(RegistrationNew.this,"Please fill out all the fields",Toast.LENGTH_SHORT).show();
                }

                if(!pass[0].equals(confirm)){
                    Toast.makeText(RegistrationNew.this,"Passwords are not matched",Toast.LENGTH_SHORT).show();
                }


                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if phone is not registered before
                            if(snapshot.hasChild(phone)){
                                Toast.makeText(RegistrationNew.this, "Phone number is already is registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //sending data to firebase realtime database
                                byte[] inputData = password.getText().toString().getBytes();
                                byte[] outputData = new byte[0];

                                try {
                                    outputData = sha.encryptSHA(inputData, "SHA-1");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                BigInteger shaData = new BigInteger(1,outputData);
//                                Object tuna = tvOutput.setText(shaData.toString(10));
                                pass[0] = shaData.toString(10);


                                databaseReference.child("users").child(phone).child("user name").setValue(user_name);
                                databaseReference.child("users").child(phone).child("password").setValue(pass[0]);

                                Toast.makeText(RegistrationNew.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationNew.this, LoginPage.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationNew.this, LoginPage.class));
                finish();
            }
        });
    }
/*
    private String getMdHash(String toString){
        String MD5 = "MD5";
//        this creates md5 hash
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(toString.getBytes());
            byte messageDigest[] = digest.digest();

//            to create hex string
            StringBuilder hexString = new StringBuilder();

            for (byte aMsgDigest: messageDigest){
                String h = Integer.toHexString(0xFF & aMsgDigest);
                while (h.length()<2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
}