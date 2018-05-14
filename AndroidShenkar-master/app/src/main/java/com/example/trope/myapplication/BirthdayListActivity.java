package com.example.trope.myapplication;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class BirthdayListActivity extends AppCompatActivity {
    protected BirthdayDatabase db;
    protected RecyclerView rv;
    protected RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbirthday);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initListBirthday();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void initListBirthday(){
        rv = findViewById(R.id.listBirthdayRecycler);
        db  = Room.databaseBuilder(getApplicationContext(),BirthdayDatabase.class, "prod").allowMainThreadQueries().build();
        rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<User> users =  new ArrayList<User >(db.userDao().getAll());
        Collections.sort(users);
        adapter  = new UserAdapter(users);
        rv.setAdapter(adapter);
    }
}


