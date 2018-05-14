package com.example.trope.myapplication;

import android.icu.text.SimpleDateFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    protected ArrayList<User> list;
    public UserAdapter(ArrayList<User> list){
        this.list = list;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_room,null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int i){
        User tmpUser = list.get(i);
        long diffInSeconds = (new Date().getTime() - tmpUser.getBirthday().getTime()) / 1000;
        long years =  diffInSeconds/(604800*52);
        String formattedDate = tmpUser.getBirthdayDate();
        String output = formattedDate + " - " + Long.toString(years )+ " Year" + (years>=1?"s":"");
        holder.name.setText(tmpUser.getName());
        holder.date.setText(output);
    }
    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView date;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.birthdayName);
            date = itemView.findViewById(R.id.birthdayDate);
        }
    }
}
