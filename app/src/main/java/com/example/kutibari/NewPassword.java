package com.example.kutibari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class NewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        /**
         * code for new password
         */
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton submit = (MaterialButton) findViewById(R.id.submit_new_pass);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().matches("")){
                    /**
                     * not empty
                     */
                    Toast.makeText(NewPassword.this, "We've successfully updated your password", Toast.LENGTH_SHORT).show();
                    openLogin();
                    password.setText("");
                }
                else{
                    /**
                     * empty
                     */
                    Toast.makeText(NewPassword.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * redirect to login page
     */
    public void openLogin(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}