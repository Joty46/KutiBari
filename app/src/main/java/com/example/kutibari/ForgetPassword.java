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

public class ForgetPassword extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kutibari-9a550-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        /**
         * forgot pass page work
         */

        final EditText phone = findViewById(R.id.mobile);
        final MaterialButton confirm = findViewById(R.id.confirm_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mobile = phone.getText().toString();
                if(!mobile.isEmpty()){
                    /**
                     * not empty
                     */

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mobile)){
                                //mobile number exists in the database
                                Toast.makeText(ForgetPassword.this, "We've sent you a code. Please check your messages", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgetPassword.this, VerificationPage.class));
                                finish();
                            }
                            else {
                                Toast.makeText(ForgetPassword.this, "Sorry this phone number isn't assigned for any account", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    /**
                     * empty
                     */
                    Toast.makeText(ForgetPassword.this, "Please provide your phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openVerification(){
        Intent intent = new Intent(this, VerificationPage.class);
        startActivity(intent);
    }
}