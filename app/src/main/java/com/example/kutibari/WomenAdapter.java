package com.example.kutibari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WomenAdapter extends BaseAdapter {
    /**
     * my code
     * @param categories
     * @param ctx
     */
//    private String[] categories;
    private Context context;
    public WomenAdapter(Context ctx){
//        this.categories = categories;
        context = ctx;
    }

    @Override
    public int getCount() {
        return MainActivity.womenImages.length;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_woman_list, null);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(MainActivity.womenImages[position]);
        TextView title = convertView.findViewById(R.id.name);
        title.setText(MainActivity.womenName[position]);
        return convertView;
    }
}
