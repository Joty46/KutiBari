package com.example.kutibari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                final String pass = password.getText().toString();
                final String confirm = conpass.getText().toString();

                /**
                 * checking if all fields are fulfilled correctly
                 */
                if(user_name.isEmpty() || phone.isEmpty() || pass.isEmpty() ){
                    Toast.makeText(RegistrationNew.this,"Please fill out all the fields",Toast.LENGTH_SHORT).show();
                }

                if(!pass.equals(confirm)){
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
                                databaseReference.child("users").child(phone).child("user name").setValue(user_name);
                                databaseReference.child("users").child(phone).child("password").setValue(pass);

                                Toast.makeText(RegistrationNew.this, "Registration successful", Toast.LENGTH_SHORT).show();
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
                finish();
            }
        });
    }
}