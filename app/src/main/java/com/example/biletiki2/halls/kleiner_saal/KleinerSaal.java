package com.example.biletiki2.halls.kleiner_saal;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import interfaces.LockSeat;

import com.example.biletiki2.UIclasses.EventInfo;
import com.example.biletiki2.UIclasses.MyView;
import com.example.biletiki2.R;
import com.example.biletiki2.UIclasses.ShoppingCart;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.ListLockedSeats;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.data.dto.PriceRanges;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class KleinerSaal extends AppCompatActivity implements View.OnClickListener, LockSeat {

    private MyView r1_1L, r1_2L, r1_3L, r1_4L, r1_5L, r1_6L, r1_7L, r1_8L, r1_9L, r1_8R, r1_7R, r1_6R, r1_5R, r1_4R, r1_3R, r1_2R, r1_1R;
    private MyView r2_1L, r2_2L, r2_3L, r2_4L, r2_5L, r2_6L, r2_7L, r2_8L, r2_9L, r2_9R, r2_8R, r2_7R, r2_6R, r2_5R, r2_4R, r2_3R, r2_2R, r2_1R;
    private MyView r3_1L, r3_2L, r3_3L, r3_4L, r3_5L, r3_6L, r3_7L, r3_8L, r3_9L, r3_10L, r3_9R, r3_8R, r3_7R, r3_6R, r3_5R, r3_4R, r3_3R, r3_2R, r3_1R;
    private MyView r4_1L, r4_2L, r4_3L, r4_4L, r4_5L, r4_6L, r4_7L, r4_8L, r4_9L, r4_10L, r4_10R, r4_9R, r4_8R, r4_7R, r4_6R, r4_5R, r4_4R, r4_3R, r4_2R, r4_1R;
    private MyView r5_1L, r5_2L, r5_3L, r5_4L, r5_5L, r5_6L, r5_7L, r5_8L, r5_9L, r5_10L, r5_11L, r5_10R, r5_9R, r5_8R, r5_7R, r5_6R, r5_5R, r5_4R, r5_3R, r5_2R, r5_1R;
    private MyView r6_1L, r6_2L, r6_3L, r6_4L, r6_5L, r6_6L, r6_7L, r6_8L, r6_9L, r6_10L, r6_11L, r6_11R, r6_10R, r6_9R, r6_8R, r6_7R, r6_6R, r6_5R, r6_4R, r6_3R, r6_2R, r6_1R;
    private MyView r7_1L, r7_2L, r7_3L, r7_4L, r7_5L, r7_6L, r7_7L, r7_8L, r7_9L, r7_10L, r7_11L, r7_12L, r7_11R, r7_10R, r7_9R, r7_8R, r7_7R, r7_6R, r7_5R, r7_4R, r7_3R, r7_2R, r7_1R;
    private MyView r8_1L, r8_2L, r8_3L, r8_4L, r8_5L, r8_6L, r8_7L, r8_8L, r8_9L, r8_10L, r8_11L, r8_12L, r8_12R, r8_11R, r8_10R, r8_9R, r8_8R, r8_7R, r8_6R, r8_5R, r8_4R, r8_3R, r8_2R, r8_1R;
    private MyView r9_1L, r9_2L, r9_3L, r9_4L, r9_5L, r9_6L, r9_7L, r9_8L, r9_9L, r9_10L, r9_11L, r9_12L, r9_13L, r9_12R, r9_11R, r9_10R, r9_9R, r9_8R, r9_7R, r9_6R, r9_5R, r9_4R, r9_3R, r9_2R, r9_1R;
    private ImageView doneImg;
    private int id;
    private List<LockedSeats> lockedSeatsList;
    private List<PriceRanges> priceRangesList;
    private MyView[] arr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kleiner_saal);

        //row 1
        r1_1L = findViewById(R.id.R1_1L);
        r1_2L = findViewById(R.id.R1_2L);
        r1_3L = findViewById(R.id.R1_3L);
        r1_4L = findViewById(R.id.R1_4L);
        r1_5L = findViewById(R.id.R1_5L);
        r1_6L = findViewById(R.id.R1_6L);
        r1_7L = findViewById(R.id.R1_7L);
        r1_8L = findViewById(R.id.R1_8L);
        r1_9L = findViewById(R.id.R1_9L);
        r1_8R = findViewById(R.id.R1_8R);
        r1_7R = findViewById(R.id.R1_7R);
        r1_6R = findViewById(R.id.R1_6R);
        r1_5R = findViewById(R.id.R1_5R);
        r1_4R = findViewById(R.id.R1_4R);
        r1_3R = findViewById(R.id.R1_3R);
        r1_2R = findViewById(R.id.R1_2R);
        r1_1R = findViewById(R.id.R1_1R);

        //row 2
        r2_1L = findViewById(R.id.R2_1L);
        r2_2L = findViewById(R.id.R2_2L);
        r2_3L = findViewById(R.id.R2_3L);
        r2_4L = findViewById(R.id.R2_4L);
        r2_5L = findViewById(R.id.R2_5L);
        r2_6L = findViewById(R.id.R2_6L);
        r2_7L = findViewById(R.id.R2_7L);
        r2_8L = findViewById(R.id.R2_8L);
        r2_9L = findViewById(R.id.R2_9L);
        r2_9R = findViewById(R.id.R2_9R);
        r2_8R = findViewById(R.id.R2_8R);
        r2_7R = findViewById(R.id.R2_7R);
        r2_6R = findViewById(R.id.R2_6R);
        r2_5R = findViewById(R.id.R2_5R);
        r2_4R = findViewById(R.id.R2_4R);
        r2_3R = findViewById(R.id.R2_3R);
        r2_2R = findViewById(R.id.R2_2R);
        r2_1R = findViewById(R.id.R2_1R);

        //row 3
        r3_1L = findViewById(R.id.R3_1L);
        r3_2L = findViewById(R.id.R3_2L);
        r3_3L = findViewById(R.id.R3_3L);
        r3_4L = findViewById(R.id.R3_4L);
        r3_5L = findViewById(R.id.R3_5L);
        r3_6L = findViewById(R.id.R3_6L);
        r3_7L = findViewById(R.id.R3_7L);
        r3_8L = findViewById(R.id.R3_8L);
        r3_9L = findViewById(R.id.R3_9L);
        r3_10L = findViewById(R.id.R3_10L);
        r3_9R = findViewById(R.id.R3_9R);
        r3_8R = findViewById(R.id.R3_8R);
        r3_7R = findViewById(R.id.R3_7R);
        r3_6R = findViewById(R.id.R3_6R);
        r3_5R = findViewById(R.id.R3_5R);
        r3_4R = findViewById(R.id.R3_4R);
        r3_3R = findViewById(R.id.R3_3R);
        r3_2R = findViewById(R.id.R3_2R);
        r3_1R = findViewById(R.id.R3_1R);

        //row 4
        r4_1L = findViewById(R.id.R4_1L);
        r4_2L = findViewById(R.id.R4_2L);
        r4_3L = findViewById(R.id.R4_3L);
        r4_4L = findViewById(R.id.R4_4L);
        r4_5L = findViewById(R.id.R4_5L);
        r4_6L = findViewById(R.id.R4_6L);
        r4_7L = findViewById(R.id.R4_7L);
        r4_8L = findViewById(R.id.R4_8L);
        r4_9L = findViewById(R.id.R4_9L);
        r4_10L = findViewById(R.id.R4_10L);
        r4_10R = findViewById(R.id.R4_10R);
        r4_9R = findViewById(R.id.R4_9R);
        r4_8R = findViewById(R.id.R4_8R);
        r4_7R = findViewById(R.id.R4_7R);
        r4_6R = findViewById(R.id.R4_6R);
        r4_5R = findViewById(R.id.R4_5R);
        r4_4R = findViewById(R.id.R4_4R);
        r4_3R = findViewById(R.id.R4_3R);
        r4_2R = findViewById(R.id.R4_2R);
        r4_1R = findViewById(R.id.R4_1R);

        //row 5
        r5_1L = findViewById(R.id.R5_1L);
        r5_2L = findViewById(R.id.R5_2L);
        r5_3L = findViewById(R.id.R5_3L);
        r5_4L = findViewById(R.id.R5_4L);
        r5_5L = findViewById(R.id.R5_5L);
        r5_6L = findViewById(R.id.R5_6L);
        r5_7L = findViewById(R.id.R5_7L);
        r5_8L = findViewById(R.id.R5_8L);
        r5_9L = findViewById(R.id.R5_9L);
        r5_10L = findViewById(R.id.R5_10L);
        r5_11L = findViewById(R.id.R5_11L);
        r5_10R = findViewById(R.id.R5_10R);
        r5_9R = findViewById(R.id.R5_9R);
        r5_8R = findViewById(R.id.R5_8R);
        r5_7R = findViewById(R.id.R5_7R);
        r5_6R = findViewById(R.id.R5_6R);
        r5_5R = findViewById(R.id.R5_5R);
        r5_4R = findViewById(R.id.R5_4R);
        r5_3R = findViewById(R.id.R5_3R);
        r5_2R = findViewById(R.id.R5_2R);
        r5_1R = findViewById(R.id.R5_1R);

        //row 6
        r6_1L = findViewById(R.id.R6_1L);
        r6_2L = findViewById(R.id.R6_2L);
        r6_3L = findViewById(R.id.R6_3L);
        r6_4L = findViewById(R.id.R6_4L);
        r6_5L = findViewById(R.id.R6_5L);
        r6_6L = findViewById(R.id.R6_6L);
        r6_7L = findViewById(R.id.R6_7L);
        r6_8L = findViewById(R.id.R6_8L);
        r6_9L = findViewById(R.id.R6_9L);
        r6_10L = findViewById(R.id.R6_10L);
        r6_11L = findViewById(R.id.R6_11L);
        r6_11R = findViewById(R.id.R6_11R);
        r6_10R = findViewById(R.id.R6_10R);
        r6_9R = findViewById(R.id.R6_9R);
        r6_8R = findViewById(R.id.R6_8R);
        r6_7R = findViewById(R.id.R6_7R);
        r6_6R = findViewById(R.id.R6_6R);
        r6_5R = findViewById(R.id.R6_5R);
        r6_4R = findViewById(R.id.R6_4R);
        r6_3R = findViewById(R.id.R6_3R);
        r6_2R = findViewById(R.id.R6_2R);
        r6_1R = findViewById(R.id.R6_1R);

        //row 7
        r7_1L = findViewById(R.id.R7_1L);
        r7_2L = findViewById(R.id.R7_2L);
        r7_3L = findViewById(R.id.R7_3L);
        r7_4L = findViewById(R.id.R7_4L);
        r7_5L = findViewById(R.id.R7_5L);
        r7_6L = findViewById(R.id.R7_6L);
        r7_7L = findViewById(R.id.R7_7L);
        r7_8L = findViewById(R.id.R7_8L);
        r7_9L = findViewById(R.id.R7_9L);
        r7_10L = findViewById(R.id.R7_10L);
        r7_11L = findViewById(R.id.R7_11L);
        r7_12L = findViewById(R.id.R7_12L);
        r7_11R = findViewById(R.id.R7_11R);
        r7_10R = findViewById(R.id.R7_10R);
        r7_9R = findViewById(R.id.R7_9R);
        r7_8R = findViewById(R.id.R7_8R);
        r7_7R = findViewById(R.id.R7_7R);
        r7_6R = findViewById(R.id.R7_6R);
        r7_5R = findViewById(R.id.R7_5R);
        r7_4R = findViewById(R.id.R7_4R);
        r7_3R = findViewById(R.id.R7_3R);
        r7_2R = findViewById(R.id.R7_2R);
        r7_1R = findViewById(R.id.R7_1R);

        //row 8
        r8_1L = findViewById(R.id.R8_1L);
        r8_2L = findViewById(R.id.R8_2L);
        r8_3L = findViewById(R.id.R8_3L);
        r8_4L = findViewById(R.id.R8_4L);
        r8_5L = findViewById(R.id.R8_5L);
        r8_6L = findViewById(R.id.R8_6L);
        r8_7L = findViewById(R.id.R8_7L);
        r8_8L = findViewById(R.id.R8_8L);
        r8_9L = findViewById(R.id.R8_9L);
        r8_10L = findViewById(R.id.R8_10L);
        r8_11L = findViewById(R.id.R8_11L);
        r8_12L = findViewById(R.id.R8_12L);
        r8_12R = findViewById(R.id.R8_12R);
        r8_11R = findViewById(R.id.R8_11R);
        r8_10R = findViewById(R.id.R8_10R);
        r8_9R = findViewById(R.id.R8_9R);
        r8_8R = findViewById(R.id.R8_8R);
        r8_7R = findViewById(R.id.R8_7R);
        r8_6R = findViewById(R.id.R8_6R);
        r8_5R = findViewById(R.id.R8_5R);
        r8_4R = findViewById(R.id.R8_4R);
        r8_3R = findViewById(R.id.R8_3R);
        r8_2R = findViewById(R.id.R8_2R);
        r8_1R = findViewById(R.id.R8_1R);

        //row 9
        r9_1L = findViewById(R.id.R9_1L);
        r9_2L = findViewById(R.id.R9_2L);
        r9_3L = findViewById(R.id.R9_3L);
        r9_4L = findViewById(R.id.R9_4L);
        r9_5L = findViewById(R.id.R9_5L);
        r9_6L = findViewById(R.id.R9_6L);
        r9_7L = findViewById(R.id.R9_7L);
        r9_8L = findViewById(R.id.R9_8L);
        r9_9L = findViewById(R.id.R9_9L);
        r9_10L = findViewById(R.id.R9_10L);
        r9_11L = findViewById(R.id.R9_11L);
        r9_12L = findViewById(R.id.R9_12L);
        r9_13L = findViewById(R.id.R9_13L);
        r9_12R = findViewById(R.id.R9_12R);
        r9_11R = findViewById(R.id.R9_11R);
        r9_10R = findViewById(R.id.R9_10R);
        r9_9R = findViewById(R.id.R9_9R);
        r9_8R = findViewById(R.id.R9_8R);
        r9_7R = findViewById(R.id.R9_7R);
        r9_6R = findViewById(R.id.R9_6R);
        r9_5R = findViewById(R.id.R9_5R);
        r9_4R = findViewById(R.id.R9_4R);
        r9_3R = findViewById(R.id.R9_3R);
        r9_2R = findViewById(R.id.R9_2R);
        r9_1R = findViewById(R.id.R9_1R);

        arr = new MyView[]{r1_1L, r1_2L, r1_3L, r1_4L, r1_5L, r1_6L, r1_7L, r1_8L, r1_9L, r1_8R, r1_7R, r1_6R, r1_5R, r1_4R, r1_3R, r1_2R, r1_1R,
                r2_1L, r2_2L, r2_3L, r2_4L, r2_5L, r2_6L, r2_7L, r2_8L, r2_9L, r2_9R, r2_8R, r2_7R, r2_6R, r2_5R, r2_4R, r2_3R, r2_2R, r2_1R,
                r3_1L, r3_2L, r3_3L, r3_4L, r3_5L, r3_6L, r3_7L, r3_8L, r3_9L, r3_10L, r3_9R, r3_8R, r3_7R, r3_6R, r3_5R, r3_4R, r3_3R, r3_2R, r3_1R,
                r4_1L, r4_2L, r4_3L, r4_4L, r4_5L, r4_6L, r4_7L, r4_8L, r4_9L, r4_10L, r4_10R, r4_9R, r4_8R, r4_7R, r4_6R, r4_5R, r4_4R, r4_3R, r4_2R, r4_1R,
                r5_1L, r5_2L, r5_3L, r5_4L, r5_5L, r5_6L, r5_7L, r5_8L, r5_9L, r5_10L, r5_11L, r5_10R, r5_9R, r5_8R, r5_7R, r5_6R, r5_5R, r5_4R, r5_3R, r5_2R, r5_1R,
                r6_1L, r6_2L, r6_3L, r6_4L, r6_5L, r6_6L, r6_7L, r6_8L, r6_9L, r6_10L, r6_11L, r6_11R, r6_10R, r6_9R, r6_8R, r6_7R, r6_6R, r6_5R, r6_4R, r6_3R, r6_2R, r6_1R,
                r7_1L, r7_2L, r7_3L, r7_4L, r7_5L, r7_6L, r7_7L, r7_8L, r7_9L, r7_10L, r7_11L, r7_12L, r7_11R, r7_10R, r7_9R, r7_8R, r7_7R, r7_6R, r7_5R, r7_4R, r7_3R, r7_2R, r7_1R,
                r8_1L, r8_2L, r8_3L, r8_4L, r8_5L, r8_6L, r8_7L, r8_8L, r8_9L, r8_10L, r8_11L, r8_12L, r8_12R, r8_11R, r8_10R, r8_9R, r8_8R, r8_7R, r8_6R, r8_5R, r8_4R, r8_3R, r8_2R, r8_1R,
                r9_1L, r9_2L, r9_3L, r9_4L, r9_5L, r9_6L, r9_7L, r9_8L, r9_9L, r9_10L, r9_11L, r9_12L, r9_13L, r9_12R, r9_11R, r9_10R, r9_9R, r9_8R, r9_7R, r9_6R, r9_5R, r9_4R, r9_3R, r9_2R, r9_1R};

        for (int i = 0; i < arr.length; i++) {
            arr[i].setOnClickListener(this);
        }

        doneImg = findViewById(R.id.done_img);
        doneImg.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("EVENT", -1);
        if (id > 0) {
            new Hall().execute();
            MyView.clearList();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.done_img) {
            if (MyView.getList().size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("You haven't choosed any seat!")
                        .setPositiveButton("Ok", null)
                        .setCancelable(false)
                        .show();
            } else {
                new Booking().execute();
            }
        } else {
            onSeatClick((MyView) v);
        }
    }

    @Override
    public void onBackPressed() {
        MyView.clearList();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSeatClick(MyView view) {
        int id = view.getId();
        String[] arr = getResources().getResourceEntryName(id).split("_");
        char[] chars = arr[0].toCharArray();
        String row = String.valueOf(chars[1]);

        Toast.makeText(this, "row " + row + " " + "place " + arr[1] + "\nPrice: " + view.getPrice(), Toast.LENGTH_SHORT).show();

        LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), arr[1]);
        view.changeStatus(lockedSeats, view.getPrice());
    }

    class Hall extends AsyncTask<Void, Void, ListLockedSeats> {

        private AlertDialog dialog = new AlertDialog.Builder(KleinerSaal.this)
                .setView(R.layout.frame_progress_dialog)
                .create();
        private ListLockedSeats l;

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected ListLockedSeats doInBackground(Void... voids) {
            try {
                l = OkHttpProvider.getInstance().listLockedSeats(id);
                return l;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ListLockedSeats listLockedSeats) {
            if (listLockedSeats != null) {
                dialog.dismiss();
                lockedSeatsList = listLockedSeats.getList();
                priceRangesList = listLockedSeats.getPriceList();

                for (int i = 0; i < lockedSeatsList.size(); i++) {
                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        String row = String.valueOf(chars[1]);
                        LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                        if (lockedSeats.equals(lockedSeatsList.get(i))) {
                            arr[j].changeColor(lockedSeats);
                        }
                    }
                }

                for (int i = 0; i < priceRangesList.size(); i++) {
                    PriceRanges priceRanges = priceRangesList.get(i);
                    int[] rows = priceRanges.getRows();
                    double price = priceRanges.getPrice();
                    String col = priceRanges.getColor();

                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        String row = String.valueOf(chars[1]);
                        LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                        for (int k = 0; k < rows.length; k++) {
                            if (lockedSeats.getRow() == rows[k]) {
                                arr[j].setColor(col);
                                arr[j].changePriceRangeColor(col);
                                arr[j].setPrice(price);
                            }
                        }
                    }
                }
                for(int a = 0; a < MyView.getList().size(); a++){
                    for (int j = 0; j < arr.length; j++) {
                        int id = arr[j].getId();
                        String[] array = getResources().getResourceEntryName(id).split("_");
                        char[] chars = array[0].toCharArray();
                        if (chars.length==2) {
                            String row = String.valueOf(chars[1]);
                            checkIfChoosed(a, j, new LockedSeats(Integer.parseInt(row), array[1]));
                        }else if(chars.length==3){
                            String row = String.valueOf(chars[1])+String.valueOf(chars[2]);
                            checkIfChoosed(a, j, new LockedSeats(Integer.parseInt(row), array[1]));
                        }
                    }
                }

            } else {
                dialog.dismiss();
                new AlertDialog.Builder(KleinerSaal.this)
                        .setTitle("ERROR")
                        .setMessage("Something wrong")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .create()
                        .show();
            }
        }
        private void checkIfChoosed(int a, int j, LockedSeats lockedSeats1) {
            LockedSeats lockedSeats = lockedSeats1;
            if (lockedSeats.getRow() == MyView.getList().get(a).getRow() && lockedSeats.getSeat().equals(MyView.getList().get(a).getSeat())) {
                arr[j].changeColorBooked(lockedSeats);
            }
        }
    }

    class Booking extends AsyncTask<Void, Void, String> {

        private String tmpBookingNumber;
        private boolean getTmpNumber = true;
        private List<LockedSeats> list = MyView.getList();
        private AlertDialog dialog = new AlertDialog.Builder(KleinerSaal.this)
                .setView(R.layout.frame_progress_dialog)
                .create();
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            getTmpNumber = true;
            tmpBookingNumber = "Reserving is successful!";
            try {
                OkHttpProvider.getInstance().getTmpBookingNumber(id, list);
                getTmpNumber = true;
            } catch (Exception e) {
                e.printStackTrace();
                getTmpNumber = false;
//                tmpBookingNumber = e.toString();
            }
            return tmpBookingNumber;
        }

        @Override
        protected void onPostExecute(String s) {
            if(getTmpNumber = true){
                dialog.dismiss();
                StoreProvider.getInstance().saveTmpBookingNumber(s);
                new AlertDialog.Builder(KleinerSaal.this)
                        .setMessage("Reservation was successful!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StoreProvider.getInstance().saveTime(System.currentTimeMillis());
                                MyView.saveList();
                                Intent intent = new Intent(KleinerSaal.this, ShoppingCart.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            }else {
                dialog.dismiss();
                new AlertDialog.Builder(KleinerSaal.this)
                        .setTitle("ERROR")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .create()
                        .show();
            }

        }
    }
}
