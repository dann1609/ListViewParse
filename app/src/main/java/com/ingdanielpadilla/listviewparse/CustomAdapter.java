package com.ingdanielpadilla.listviewparse;

/**
 * Created by Laboratorio on 20/10/2015.
 */
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Laboratorio on 29/09/2015.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG="AS_ListView";
    private Context context;
    private ArrayList values;

    public CustomAdapter(Context context,ArrayList values){
        this.context= context;
        this.values=values;
    }



    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String s=values.get(position).toString();
        if(view==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.custom_row,null);
        }
        TextView f1=(TextView) view.findViewById(R.id.tvField1);
        f1.setText(s);
        view.setTag(s);
        return view;
    }
}