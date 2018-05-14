package com.example.trope.myapplication;

import android.arch.persistence.room.Room;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Calendar;

import java.util.Date;

public class BirthdayAddActivity extends AppCompatActivity {
    protected CalendarView calendarAddBirthdayView;
    protected String actualDate;
    protected BirthdayDatabase db;
    protected EditText addNameBirthday;
    private String dateFormat = "dd/MM/yyyy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbirthday);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initAddBirthday();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void initAddBirthday(){

        addNameBirthday = findViewById(R.id.addNameBirthday);
        calendarAddBirthdayView = findViewById(R.id.calendarAddBirthdayView);
        calendarAddBirthdayView.setDate(631200000000L);
        actualDate = getDate(calendarAddBirthdayView.getDate(),dateFormat);
        calendarAddBirthdayView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                actualDate  =  String.format("%02d", day) + "/" + String.format("%02d", month+1) + "/" + String.valueOf(year);
            }
        });
    }
    public void onAddBirthdayClicked(View view) {
        String name = addNameBirthday.getText().toString();
        if (!name.trim().isEmpty()){
        User tmpUser = new User(name,parseDate(actualDate,dateFormat));
        try {
            db = Room.databaseBuilder(getApplicationContext(),BirthdayDatabase.class, "prod").allowMainThreadQueries().build();
            db.userDao().insertAll(tmpUser);
            new MaterialDialog.Builder(this)
                    .title(R.string.dialog_successTitle)
                    .content(R.string.dialog_addBirthday_successContent)
                    .positiveText(R.string.dialog_addBirthday_successButton)
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            onSupportNavigateUp();
                        }
                    }).show();
        }
        catch(Exception e){
            new MaterialDialog.Builder(this)
                    .title(R.string.dialog_addBirthday_errorTitle)
                    .content(R.string.dialog_addBirthday_errorContent)
                    .positiveText(R.string.dialog_addBirthday_errorButton)
                    .show();
        }
        }
        else {
            new MaterialDialog.Builder(this)
                    .title(R.string.dialog_addBirthday_errorTitle)
                    .content(R.string.dialog_addBirthday_nameError)
                    .positiveText(R.string.dialog_addBirthday_errorButton)
                    .show();
        }
    }

    private Date parseDate(String theDate, String theDateFormat){
        try {
            return new SimpleDateFormat(theDateFormat).parse(theDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }





}


