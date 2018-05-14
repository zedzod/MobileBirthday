package com.example.trope.myapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class BirthdayMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbirthday);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void onAddClicked(View view) {
        startActivity(new Intent(this, BirthdayAddActivity.class));
    }
    public void onListClicked(View view) {
        startActivity(new Intent(this, BirthdayListActivity.class));
    }
    public void onClearClicked(View view) {
        BirthdayDatabase db  = Room.databaseBuilder(getApplicationContext(),BirthdayDatabase.class, "prod").allowMainThreadQueries().build();
        db.clearAllTables();
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_successTitle)
                .content(R.string.dialog_deleteBirthdayContent)
                .positiveText(R.string.dialog_OK)
                .show();
    }

}
