package com.example.trope.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.icu.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.function.LongToIntFunction;

@Entity
public class User implements Comparable<User> {
    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(getBirthday());
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public int compareTo(User o) {
        long now = Instant.now().toEpochMilli();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try {
            String fD1 = getBirthdayDate().substring(0, getBirthdayDate().length() - 4) + year;
            fD1 = fD1.substring(0, fD1.length() - 4) + year ;
            Date mils1 = new SimpleDateFormat("dd/MM/yyyy").parse(fD1);
            if (mils1.getTime()-now < 0) {
                fD1 = fD1.substring(0, fD1.length() - 4) + (year+1);
                mils1 = new SimpleDateFormat("dd/MM/yyyy").parse(fD1);
            }
            String fD2 = o.getBirthdayDate().substring(0, o.getBirthdayDate().length() - 4) + year;
            Date mils2 = new SimpleDateFormat("dd/MM/yyyy").parse(fD2);
            if (mils2.getTime()-now < 0) {
                fD2 = fD2.substring(0, fD2.length() - 4) + (year+1);
                mils2 = new SimpleDateFormat("dd/MM/yyyy").parse(fD2);
            }
            return mils1.compareTo(mils2);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Date birthday;
}
