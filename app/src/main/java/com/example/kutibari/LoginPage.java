package com.example.kutibari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginPage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kutibari-9a550-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        /**
         * login page work
         */
        final EditText mobile = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final MaterialButton loginbtn = findViewById(R.id.loginbtn);
        final TextView regnow = findViewById(R.id.regnow);
//        password.setText(getMdHash(password.getText().toString()));

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * code
                 */
                final String phone = mobile.getText().toString();
                final String[] pass = {password.getText().toString()};

                if(phone.isEmpty()){
                    Toast.makeText(LoginPage.this,"Please Enter your username",Toast.LENGTH_SHORT).show();
                }
                else if(pass[0].isEmpty() ){
                    Toast.makeText(LoginPage.this,"Please Enter your password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if mobile phone exits in firebase
                            if(snapshot.hasChild(phone)){
                                //mobile exits in firebase now take password from user
                                final String getpass = snapshot.child(phone).child("password").getValue(String.class);


                                //hash the entered password
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
                                if(getpass.equals(pass[0])){
                                    Toast.makeText(LoginPage.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginPage.this, "Sorry you provided wrong password", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginPage.this, pass[0], Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginPage.this, "Sorry you don't have an account, please create an account first", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPage.this, RegistrationNew.class));
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

        /**
         * go to register page
         */
        regnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, RegistrationNew.class));
            }
        });

        TextView forgotpass = (TextView) findViewById(R.id.forgotpass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, ForgetPassword.class));
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
                while (h.length()<2){
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
}