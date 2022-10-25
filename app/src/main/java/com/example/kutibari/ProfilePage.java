package com.example.kutibari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class ProfilePage extends AppCompatActivity {

    String[] itemfororder = {"item1","item2","item3","item4"};
    String[] itemforworks = {"item1","item2","item3","item4"};
    AutoCompleteTextView autoCompleteTxtForOrders,autoCompleteTxtForWorks;
    ArrayAdapter<String> adapterItemforOrders,adapterItemforWorks;
    Button uploadbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        autoCompleteTxtForOrders=findViewById(R.id.auto_complete_text_orders);
        adapterItemforOrders=new ArrayAdapter<String>(this,R.layout.list_for_orders,itemfororder);
        autoCompleteTxtForOrders.setAdapter(adapterItemforOrders);
        autoCompleteTxtForOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String itemsfororder=parent.getItemAtPosition(position).toString();
//                Intent intent=new Intent(ProfilePage.this,OrderDetails.class);//order details
//                startActivity(intent);
            }
        });
        autoCompleteTxtForWorks=findViewById(R.id.auto_complete_text_uploadedworks);
        adapterItemforWorks=new ArrayAdapter<String>(this,R.layout.list_for_orders,itemforworks);
        autoCompleteTxtForWorks.setAdapter(adapterItemforWorks);
        autoCompleteTxtForWorks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String itemsforworks=parent.getItemAtPosition(position).toString();
//                Intent intent=new Intent(ProfilePage.this,Works.class);//works.java
//                startActivity(intent);
            }
        });
        uploadbtn=findViewById(R.id.upload);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,UploadWorks.class));
            }
        });
    }
}