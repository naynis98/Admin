package com.example.a16033774.fourtruthonelieadmin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class UserArrayAdapter extends android.widget.ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> user;

    public UserArrayAdapter(Context context, int resource, ArrayList<User> user) {
        super(context, resource, user);
        this.context = context;
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false); //change row according to xml


        return rowView;
    }
}
