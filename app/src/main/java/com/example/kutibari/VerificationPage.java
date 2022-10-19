package com.example.kutibari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class VerificationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_page);

        /**
         * code for verification page
         */
        TextView code = (TextView) findViewById(R.id.code);
        TextView resend = (TextView) findViewById(R.id.resend);
        MaterialButton submit = (MaterialButton) findViewById(R.id.submit_button);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerificationPage.this, "We've resent the code", Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewpassword();
                code.setText("");
            }
        });
    }

    /**
     * open new password page
     */
    public void openNewpassword(){
        Intent intent = new Intent(this, NewPassword.class);
        startActivity(intent);
    }
}