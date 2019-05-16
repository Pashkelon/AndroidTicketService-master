package com.example.biletiki2.halls.grosse_saal.areas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.biletiki2.R;
import com.example.biletiki2.UIclasses.MyView;
import com.example.biletiki2.UIclasses.ShoppingCart;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.ListLockedSeats;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.data.dto.PriceRanges;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;

import java.util.ArrayList;
import java.util.List;

import interfaces.LockSeat;

public class FirstArea extends AppCompatActivity implements View.OnClickListener, LockSeat {
    private MyView r1_1L, r1_2L, r1_3L;
    private MyView r2_1L, r2_2L, r2_3L, r2_4L;
    private MyView r3_1L, r3_2L, r3_3L, r3_4L, r3_5L;
    private MyView r4_1L, r4_2L, r4_3L, r4_4L, r4_5L, r4_6L;
    private MyView r5_1L, r5_2L, r5_3L, r5_4L, r5_5L, r5_6L, r5_7L;
    private MyView r6_1L, r6_2L, r6_3L, r6_4L, r6_5L, r6_6L, r6_7L, r6_8L;
    private MyView r7_1L, r7_2L, r7_3L, r7_4L, r7_5L, r7_6L, r7_7L, r7_8L, r7_9L;
    private MyView r8_1L, r8_2L, r8_3L, r8_4L, r8_5L, r8_6L, r8_7L, r8_8L, r8_9L, r8_10L;
    private MyView r9_1L, r9_2L, r9_3L, r9_4L, r9_5L, r9_6L, r9_7L, r9_8L, r9_9L, r9_10L;
    private MyView r10_1L, r10_2L, r10_3L, r10_4L, r10_5L, r10_6L, r10_7L, r10_8L, r10_9L;
    private MyView r11_1L, r11_2L, r11_3L, r11_4L, r11_5L, r11_6L, r11_7L, r11_8L;
    private MyView r12_1L, r12_2L, r12_3L, r12_4L, r12_5L, r12_6L, r12_7L;
    private MyView r13_1L, r13_2L, r13_3L, r13_4L, r13_5L, r13_6L;
    private MyView r14_1L, r14_2L, r14_3L, r14_4L, r14_5L;
    private ImageView doneImg;
    private int id;
    private List<LockedSeats> lockedSeatsList;
    private List<PriceRanges> priceRangesList;
    private MyView[] arr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_area);

        //row 1
        r1_1L = findViewById(R.id.R1_1L);
        r1_2L = findViewById(R.id.R1_2L);
        r1_3L = findViewById(R.id.R1_3L);

        //row 2
        r2_1L = findViewById(R.id.R2_1L);
        r2_2L = findViewById(R.id.R2_2L);
        r2_3L = findViewById(R.id.R2_3L);
        r2_4L = findViewById(R.id.R2_4L);

        //row 3
        r3_1L = findViewById(R.id.R3_1L);
        r3_2L = findViewById(R.id.R3_2L);
        r3_3L = findViewById(R.id.R3_3L);
        r3_4L = findViewById(R.id.R3_4L);
        r3_5L = findViewById(R.id.R3_5L);

        //row 4
        r4_1L = findViewById(R.id.R4_1L);
        r4_2L = findViewById(R.id.R4_2L);
        r4_3L = findViewById(R.id.R4_3L);
        r4_4L = findViewById(R.id.R4_4L);
        r4_5L = findViewById(R.id.R4_5L);
        r4_6L = findViewById(R.id.R4_6L);

        //row 5
        r5_1L = findViewById(R.id.R5_1L);
        r5_2L = findViewById(R.id.R5_2L);
        r5_3L = findViewById(R.id.R5_3L);
        r5_4L = findViewById(R.id.R5_4L);
        r5_5L = findViewById(R.id.R5_5L);
        r5_6L = findViewById(R.id.R5_6L);
        r5_7L = findViewById(R.id.R5_7L);

        //row 6
        r6_1L = findViewById(R.id.R6_1L);
        r6_2L = findViewById(R.id.R6_2L);
        r6_3L = findViewById(R.id.R6_3L);
        r6_4L = findViewById(R.id.R6_4L);
        r6_5L = findViewById(R.id.R6_5L);
        r6_6L = findViewById(R.id.R6_6L);
        r6_7L = findViewById(R.id.R6_7L);
        r6_8L = findViewById(R.id.R6_8L);

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

        //row10
        r10_1L = findViewById(R.id.R10_1L);
        r10_2L = findViewById(R.id.R10_2L);
        r10_3L = findViewById(R.id.R10_3L);
        r10_4L = findViewById(R.id.R10_4L);
        r10_5L = findViewById(R.id.R10_5L);
        r10_6L = findViewById(R.id.R10_6L);
        r10_7L = findViewById(R.id.R10_7L);
        r10_8L = findViewById(R.id.R10_8L);
        r10_9L = findViewById(R.id.R10_9L);

        //row11
        r11_1L = findViewById(R.id.R11_1L);
        r11_2L = findViewById(R.id.R11_2L);
        r11_3L = findViewById(R.id.R11_3L);
        r11_4L = findViewById(R.id.R11_4L);
        r11_5L = findViewById(R.id.R11_5L);
        r11_6L = findViewById(R.id.R11_6L);
        r11_7L = findViewById(R.id.R11_7L);
        r11_8L = findViewById(R.id.R11_8L);

        //row12
        r12_1L = findViewById(R.id.R12_1L);
        r12_2L = findViewById(R.id.R12_2L);
        r12_3L = findViewById(R.id.R12_3L);
        r12_4L = findViewById(R.id.R12_4L);
        r12_5L = findViewById(R.id.R12_5L);
        r12_6L = findViewById(R.id.R12_6L);
        r12_7L = findViewById(R.id.R12_7L);

        //row13
        r13_1L = findViewById(R.id.R13_1L);
        r13_2L = findViewById(R.id.R13_2L);
        r13_3L = findViewById(R.id.R13_3L);
        r13_4L = findViewById(R.id.R13_4L);
        r13_5L = findViewById(R.id.R13_5L);
        r13_6L = findViewById(R.id.R13_6L);

        //row14
        r14_1L = findViewById(R.id.R14_1L);
        r14_2L = findViewById(R.id.R14_2L);
        r14_3L = findViewById(R.id.R14_3L);
        r14_4L = findViewById(R.id.R14_4L);
        r14_5L = findViewById(R.id.R14_5L);

        arr = new MyView[]{r1_1L, r1_2L, r1_3L,
                r2_1L, r2_2L, r2_3L, r2_4L,
                r3_1L, r3_2L, r3_3L, r3_4L, r3_5L,
                r4_1L, r4_2L, r4_3L, r4_4L, r4_5L, r4_6L,
                r5_1L, r5_2L, r5_3L, r5_4L, r5_5L, r5_6L, r5_7L,
                r6_1L, r6_2L, r6_3L, r6_4L, r6_5L, r6_6L, r6_7L, r6_8L,
                r7_1L, r7_2L, r7_3L, r7_4L, r7_5L, r7_6L, r7_7L, r7_8L, r7_9L,
                r8_1L, r8_2L, r8_3L, r8_4L, r8_5L, r8_6L, r8_7L, r8_8L, r8_9L, r8_10L,
                r9_1L, r9_2L, r9_3L, r9_4L, r9_5L, r9_6L, r9_7L, r9_8L, r9_9L, r9_10L,
                r10_1L, r10_2L, r10_3L, r10_4L, r10_5L, r10_6L, r10_7L, r10_8L, r10_9L,
                r11_1L, r11_2L, r11_3L, r11_4L, r11_5L, r11_6L, r11_7L, r11_8L,
                r12_1L, r12_2L, r12_3L, r12_4L, r12_5L, r12_6L, r12_7L,
                r13_1L, r13_2L, r13_3L, r13_4L, r13_5L, r13_6L,
                r14_1L, r14_2L, r14_3L, r14_4L, r14_5L};

        for (int i = 0; i < arr.length; i++) {
            arr[i].setOnClickListener(this);
        }

        doneImg = findViewById(R.id.done_img);
        doneImg.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("EVENT", -1);
        if (id > 0) {
            new FirstArea.Hall().execute();
//            MyView.clearList();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.done_img) {
                finish();
        } else {
            onSeatClick((MyView) v);
        }
    }

    @Override
    public void onBackPressed() {
//        MyView.clearList();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSeatClick(MyView view) {
        int id = view.getId();
        String[] arr = getResources().getResourceEntryName(id).split("_");
        char[] chars = arr[0].toCharArray();
        if(chars.length==2) {
            String row = String.valueOf(chars[1]);
            Toast.makeText(this, "row " + row + " " + "place " + arr[1] + "\nPrice: " + view.getPrice(), Toast.LENGTH_SHORT).show();
            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), arr[1]);
            view.changeStatus(lockedSeats, view.getPrice());
        }else if(chars.length==3){
            String row = String.valueOf(chars[1])+String.valueOf(chars[2]);
            Toast.makeText(this, "row " + row + " " + "place " + arr[1] + "\nPrice: " + view.getPrice(), Toast.LENGTH_SHORT).show();
            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), arr[1]);
            view.changeStatus(lockedSeats, view.getPrice());
        }
    }

    class Hall extends AsyncTask<Void, Void, ListLockedSeats> {

        private AlertDialog dialog = new AlertDialog.Builder(FirstArea.this)
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
                        if (chars.length==2) {
                            String row = String.valueOf(chars[1]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            if (lockedSeats.equals(lockedSeatsList.get(i))) {
                                arr[j].changeColor(lockedSeats);
                            }
                        }else if(chars.length==3){
                            String row = String.valueOf(chars[1])+String.valueOf(chars[2]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            if (lockedSeats.equals(lockedSeatsList.get(i))) {
                                arr[j].changeColor(lockedSeats);
                            }
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
                        if(chars.length==2) {
                            String row = String.valueOf(chars[1]);
                            LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
                            for (int k = 0; k < rows.length; k++) {
                                if (lockedSeats.getRow() == rows[k]) {
                                    arr[j].setColor(col);
                                    arr[j].changePriceRangeColor(col);
                                    arr[j].setPrice(price);
                                }
                            }
                        }else if(chars.length==3){
                            String row = String.valueOf(chars[1])+String.valueOf(chars[2]);
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
                }


            } else {
                dialog.dismiss();
                new AlertDialog.Builder(FirstArea.this)
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
}
