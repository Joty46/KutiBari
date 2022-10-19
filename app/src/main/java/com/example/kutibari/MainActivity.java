package com.example.kutibari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    /**
     * grid view and arrays for category of products
     */
    private GridView gridcat;
    public static int categoryImages[] = {R.drawable.craft1, R.drawable.craft2, R.drawable.craft3, R.drawable.craft4, R.drawable.craft5, R.drawable.craft6, R.drawable.craft7};
    public static String[] productName = {"cat1", "cat2", "cat3", "cat4", "cat5", "cat6", "cat7"};

    /**
     * grid view and arrays
     * @param savedInstanceState
     */
    private GridView gridView;
    public static int womenImages[] = {R.drawable.woman1, R.drawable.woman2, R.drawable.woman3, R.drawable.woman4, R.drawable.woman5, R.drawable.woman6, R.drawable.woman7};
    public static String[] womenName = {"cat1", "cat2", "cat3", "cat4", "cat5", "cat6", "cat7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * gridview for product category
         */
        gridcat = (GridView) findViewById(R.id.gridcat);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        gridcat.setAdapter(categoryAdapter);
        /**
         * grid view for women
         */
        gridView = (GridView) findViewById(R.id.gridView);
        WomenAdapter adapter = new WomenAdapter(this);
        gridView.setAdapter(adapter);


        /**
         * Image listener to show login dialogue;
         */
        ImageView imageView = findViewById(R.id.image);
//        TextView showtext = findViewById(R.id.login);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfilePage.class));
            }
        });

        /**
         * login button for going to login page
         */
        MaterialButton loginbtn= (MaterialButton) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginPage.class));
                finish();
            }
        });

        /**
         * sign up text to go create account page
         */
        TextView signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationNew.class));
//                finish();
            }
        });

        /**
         * view category to go to category page
         */
        TextView viewcatergory = (TextView) findViewById(R.id.artisanlist);
        viewcatergory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainList.class));
                finish();
            }

        });
    }
}