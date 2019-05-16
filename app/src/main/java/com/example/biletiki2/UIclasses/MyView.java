package com.example.biletiki2.UIclasses;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.biletiki2.R;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;

import java.util.ArrayList;
import java.util.List;

public class MyView extends FrameLayout {

    private static List<LockedSeats> list = new ArrayList<>();

    private FrameLayout viewById;
    private boolean status = false;
    private static int ticketsNum = 0;
    private static double totalPrice = 0;
    private double price = 0;
    private String color;

    public static int getTicketsNum() {
        return ticketsNum;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public static void clearList() {
        ticketsNum = 0;
        totalPrice = 0;
        list = new ArrayList<>();
        StoreProvider.getInstance().removeList();
        StoreProvider.getInstance().removeTicketsNum();
        StoreProvider.getInstance().removeTotalPrice();
    }

    public static void saveList() {
        StoreProvider.getInstance().saveTotalPrice(totalPrice);
        StoreProvider.getInstance().saveTicketsNum(ticketsNum);
        StoreProvider.getInstance().saveLockedSeatsList(list);
    }

    public static List<LockedSeats> getList() {
        return list;
    }

    public void init() {
        inflate(getContext(), R.layout.activity_my_view, this);
        viewById = findViewById(R.id.view_frame);
    }

    public void changeStatus(LockedSeats lockedSeats, double price) {
        status = !status;
        if (status) {
            if(list.size()==0){
                viewById.setBackgroundColor(Color.parseColor("#07ace8"));
                ticketsNum++;
                totalPrice += price;
                list.add(lockedSeats);
            }else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getRow() == lockedSeats.getRow() && list.get(i).getSeat().equals(lockedSeats.getSeat())) {
                        return;
                    } else {
                        viewById.setBackgroundColor(Color.parseColor("#07ace8"));
                        ticketsNum++;
                        totalPrice += price;
                        list.add(lockedSeats);
                        return;
                    }
                }
            }
        } else {
            viewById.setBackgroundColor(Color.parseColor(color));
            ticketsNum--;
            totalPrice -= price;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRow() == lockedSeats.getRow() && list.get(i).getSeat().equals(lockedSeats.getSeat())) {
                    LockedSeats ls = list.get(i);
                    list.remove(ls);
                }
            }
        }
        invalidate();
    }

    public void changeColor(LockedSeats lockedSeats) {
        viewById.setBackgroundColor(Color.parseColor("#ff1632"));
        viewById.setClickable(false);
    }

    public void changePriceRangeColor(String color) {
        viewById.setBackgroundColor(Color.parseColor(color));
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void changeColorBooked(LockedSeats lockedSeats) {
        viewById.setBackgroundColor(Color.parseColor("#07ace8"));
        status = true;
    }
}
