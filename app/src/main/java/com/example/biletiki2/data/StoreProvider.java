package com.example.biletiki2.data;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.LockedSeats;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StoreProvider {
    private static final StoreProvider instance = new StoreProvider();
    private static final String SP_AUTH = "AUTH";
    private static final String SP_DATA = "DATA";
    public static final String LS_LIST = "LIST";
    private static final String TOKEN = "TOKEN";
    private static final String EVENT = "EVENT";
    private static final String TOTAL_SUM = "TOTAL_SUM";
    public static final String TICKETS_NUM = "TICKETS_NUM";
    public static final String TMP_NUMBER = "TMP_NUMBER";
    private Context context;

    private StoreProvider() {
    }

    public static StoreProvider getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void save(String token) {
        context.getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(TOKEN, token)
                .commit();
    }

    public String getToken() {
        return context.getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE)
                .getString(TOKEN, null);
    }

    public boolean logout() {
        return context.getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE)
                .edit()
                .remove(TOKEN)
                .commit();
    }

    public String getDate(long timeStart) {
        Date date = new Date(timeStart);
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM ", Locale.ENGLISH);
        return format.format(date);
    }


    public void saveLockedSeatsList(List<LockedSeats> list) {
        List<LockedSeats> l = list;
        String listJson = new Gson().toJson(l);
        context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(LS_LIST, listJson)
                .commit();
    }

    public String getList() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .getString(LS_LIST, null);
    }

    public boolean removeList() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .remove(LS_LIST)
                .commit();
    }

    public void saveEvent(Event event) {
        String e = new Gson().toJson(event);
        context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(EVENT, e)
                .commit();
    }

    public String getEvent() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .getString(EVENT, null);
    }

    public boolean removeEvent() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .remove(EVENT)
                .commit();
    }

    public double getTotalPrice() {
        double totalPrice = new Gson().fromJson(context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .getString(TOTAL_SUM, null), Double.class);
        return totalPrice;
    }

    public int getTicketsNum() {
        int ticketsNum = new Gson().fromJson(context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .getString(TICKETS_NUM, null), Integer.class);
        return ticketsNum;
    }

    public boolean removeTotalPrice() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .remove(TOTAL_SUM)
                .commit();
    }

    public boolean removeTicketsNum() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .remove(TICKETS_NUM)
                .commit();
    }

    public void saveTicketsNum(int tNum) {
        String e = new Gson().toJson(tNum);
        context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(TICKETS_NUM, e)
                .commit();
    }

    public void saveTotalPrice(double price) {
        String e = new Gson().toJson(price);
        context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(TOTAL_SUM, e)
                .commit();
    }

    public void saveTime(long time){
        context.getSharedPreferences("TIME",Context.MODE_PRIVATE)
                .edit()
                .putLong("T",time)
                .commit();
    }

    public long getTime(){
        return context.getSharedPreferences("TIME", Context.MODE_PRIVATE)
                .getLong("T", 0);
    }

    public boolean removeTime() {
        return context.getSharedPreferences("TIME", Context.MODE_PRIVATE)
                .edit()
                .remove("T")
                .commit();
    }

    public void saveTmpBookingNumber(String tmpNumber){
        context.getSharedPreferences(SP_DATA,Context.MODE_PRIVATE)
                .edit()
                .putString(TMP_NUMBER, tmpNumber)
                .commit();
    }

    public String getTmpNumber(){
        return context.getSharedPreferences(SP_DATA ,Context.MODE_PRIVATE)
                .getString(TMP_NUMBER, null);
    }

    public boolean removeTmpNumber() {
        return context.getSharedPreferences(SP_DATA, Context.MODE_PRIVATE)
                .edit()
                .remove(TMP_NUMBER)
                .commit();
    }

}

