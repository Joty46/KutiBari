package com.example.kutibari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {

    /**
     * my code
     * @param categories
     * @param ctx
     */
//    private String[] categories;
    private Context context;
    public CategoryAdapter(Context ctx){
//        this.categories = categories;
        context = ctx;
    }



    @Override
    public int getCount() {
        return MainActivity.categoryImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * my code
         */
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_category_show, null);
        ImageView imageView = convertView.findViewById(R.id.categoryImage);
        imageView.setImageResource(MainActivity.categoryImages[position]);
        TextView title = convertView.findViewById(R.id.productname);
        title.setText(MainActivity.productName[position]);
        return convertView;
    }
}