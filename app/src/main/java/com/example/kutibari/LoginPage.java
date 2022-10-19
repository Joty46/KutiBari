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

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * code
                 */
                final String phone = mobile.getText().toString();
                final String pass = password.getText().toString();

                if(phone.isEmpty()){
                    Toast.makeText(LoginPage.this,"Please Enter your username",Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty() ){
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
                                if(getpass.equals(pass)){
                                    Toast.makeText(LoginPage.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginPage.this, "Sorry you provided wrong password", Toast.LENGTH_SHORT).show();
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

    /**
     * go to forget password page
     */
//    public void openforgotpass(){
//
//        Intent intent = new Intent(this, ForgetPassword.class);
//        startActivity(intent);
//    }

    /**
     * go to home page
     */
//    public void gotohomepage(){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}