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

import java.util.List;

import interfaces.LockSeat;

public class ThirdArea extends AppCompatActivity implements View.OnClickListener, LockSeat {
    private MyView r1_1R, r1_2R, r1_3R;
    private MyView r2_1R, r2_2R, r2_3R, r2_4R;
    private MyView r3_1R, r3_2R, r3_3R, r3_4R, r3_5R;
    private MyView r4_1R, r4_2R, r4_3R, r4_4R, r4_5R, r4_6R;
    private MyView r5_1R, r5_2R, r5_3R, r5_4R, r5_5R, r5_6R, r5_7R;
    private MyView r6_1R, r6_2R, r6_3R, r6_4R, r6_5R, r6_6R, r6_7R, r6_8R;
    private MyView r7_1R, r7_2R, r7_3R, r7_4R, r7_5R, r7_6R, r7_7R, r7_8R, r7_9R;
    private MyView r8_1R, r8_2R, r8_3R, r8_4R, r8_5R, r8_6R, r8_7R, r8_8R, r8_9R, r8_10R;
    private MyView r9_1R, r9_2R, r9_3R, r9_4R, r9_5R, r9_6R, r9_7R, r9_8R, r9_9R, r9_10R;
    private MyView r10_1R, r10_2R, r10_3R, r10_4R, r10_5R, r10_6R, r10_7R, r10_8R, r10_9R;
    private MyView r11_1R, r11_2R, r11_3R, r11_4R, r11_5R, r11_6R, r11_7R, r11_8R;
    private MyView r12_1R, r12_2R, r12_3R, r12_4R, r12_5R, r12_6R, r12_7R;
    private MyView r13_1R, r13_2R, r13_3R, r13_4R, r13_5R, r13_6R;
    private MyView r14_1R, r14_2R, r14_3R, r14_4R, r14_5R;
    private ImageView doneImg;
    private int id;
    private List<LockedSeats> lockedSeatsList;
    private List<PriceRanges> priceRangesList;
    private MyView[] arr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_area);

        //row 1
        r1_1R = findViewById(R.id.R1_1R);
        r1_2R = findViewById(R.id.R1_2R);
        r1_3R = findViewById(R.id.R1_3R);

        //row 2
        r2_1R = findViewById(R.id.R2_1R);
        r2_2R = findViewById(R.id.R2_2R);
        r2_3R = findViewById(R.id.R2_3R);
        r2_4R = findViewById(R.id.R2_4R);

        //row 3
        r3_1R = findViewById(R.id.R3_1R);
        r3_2R = findViewById(R.id.R3_2R);
        r3_3R = findViewById(R.id.R3_3R);
        r3_4R = findViewById(R.id.R3_4R);
        r3_5R = findViewById(R.id.R3_5R);

        //row 4
        r4_1R = findViewById(R.id.R4_1R);
        r4_2R = findViewById(R.id.R4_2R);
        r4_3R = findViewById(R.id.R4_3R);
        r4_4R = findViewById(R.id.R4_4R);
        r4_5R = findViewById(R.id.R4_5R);
        r4_6R = findViewById(R.id.R4_6R);

        //row 5
        r5_1R = findViewById(R.id.R5_1R);
        r5_2R = findViewById(R.id.R5_2R);
        r5_3R = findViewById(R.id.R5_3R);
        r5_4R = findViewById(R.id.R5_4R);
        r5_5R = findViewById(R.id.R5_5R);
        r5_6R = findViewById(R.id.R5_6R);
        r5_7R = findViewById(R.id.R5_7R);

        //row 6
        r6_1R = findViewById(R.id.R6_1R);
        r6_2R = findViewById(R.id.R6_2R);
        r6_3R = findViewById(R.id.R6_3R);
        r6_4R = findViewById(R.id.R6_4R);
        r6_5R = findViewById(R.id.R6_5R);
        r6_6R = findViewById(R.id.R6_6R);
        r6_7R = findViewById(R.id.R6_7R);
        r6_8R = findViewById(R.id.R6_8R);

        //row 7
        r7_1R = findViewById(R.id.R7_1R);
        r7_2R = findViewById(R.id.R7_2R);
        r7_3R = findViewById(R.id.R7_3R);
        r7_4R = findViewById(R.id.R7_4R);
        r7_5R = findViewById(R.id.R7_5R);
        r7_6R = findViewById(R.id.R7_6R);
        r7_7R = findViewById(R.id.R7_7R);
        r7_8R = findViewById(R.id.R7_8R);
        r7_9R = findViewById(R.id.R7_9R);

        //row 8
        r8_1R = findViewById(R.id.R8_1R);
        r8_2R = findViewById(R.id.R8_2R);
        r8_3R = findViewById(R.id.R8_3R);
        r8_4R = findViewById(R.id.R8_4R);
        r8_5R = findViewById(R.id.R8_5R);
        r8_6R = findViewById(R.id.R8_6R);
        r8_7R = findViewById(R.id.R8_7R);
        r8_8R = findViewById(R.id.R8_8R);
        r8_9R = findViewById(R.id.R8_9R);
        r8_10R = findViewById(R.id.R8_10R);

        //row 9
        r9_1R = findViewById(R.id.R9_1R);
        r9_2R = findViewById(R.id.R9_2R);
        r9_3R = findViewById(R.id.R9_3R);
        r9_4R = findViewById(R.id.R9_4R);
        r9_5R = findViewById(R.id.R9_5R);
        r9_6R = findViewById(R.id.R9_6R);
        r9_7R = findViewById(R.id.R9_7R);
        r9_8R = findViewById(R.id.R9_8R);
        r9_9R = findViewById(R.id.R9_9R);
        r9_10R = findViewById(R.id.R9_10R);

        //row10
        r10_1R = findViewById(R.id.R10_1R);
        r10_2R = findViewById(R.id.R10_2R);
        r10_3R = findViewById(R.id.R10_3R);
        r10_4R = findViewById(R.id.R10_4R);
        r10_5R = findViewById(R.id.R10_5R);
        r10_6R = findViewById(R.id.R10_6R);
        r10_7R = findViewById(R.id.R10_7R);
        r10_8R = findViewById(R.id.R10_8R);
        r10_9R = findViewById(R.id.R10_9R);

        //row11
        r11_1R = findViewById(R.id.R11_1R);
        r11_2R = findViewById(R.id.R11_2R);
        r11_3R = findViewById(R.id.R11_3R);
        r11_4R = findViewById(R.id.R11_4R);
        r11_5R = findViewById(R.id.R11_5R);
        r11_6R = findViewById(R.id.R11_6R);
        r11_7R = findViewById(R.id.R11_7R);
        r11_8R = findViewById(R.id.R11_8R);

        //row12
        r12_1R = findViewById(R.id.R12_1R);
        r12_2R = findViewById(R.id.R12_2R);
        r12_3R = findViewById(R.id.R12_3R);
        r12_4R = findViewById(R.id.R12_4R);
        r12_5R = findViewById(R.id.R12_5R);
        r12_6R = findViewById(R.id.R12_6R);
        r12_7R = findViewById(R.id.R12_7R);

        //row13
        r13_1R = findViewById(R.id.R13_1R);
        r13_2R = findViewById(R.id.R13_2R);
        r13_3R = findViewById(R.id.R13_3R);
        r13_4R = findViewById(R.id.R13_4R);
        r13_5R = findViewById(R.id.R13_5R);
        r13_6R = findViewById(R.id.R13_6R);

        //row14
        r14_1R = findViewById(R.id.R14_1R);
        r14_2R = findViewById(R.id.R14_2R);
        r14_3R = findViewById(R.id.R14_3R);
        r14_4R = findViewById(R.id.R14_4R);
        r14_5R = findViewById(R.id.R14_5R);

        arr = new MyView[]{r1_1R, r1_2R, r1_3R,
                r2_1R, r2_2R, r2_3R, r2_4R,
                r3_1R, r3_2R, r3_3R, r3_4R, r3_5R,
                r4_1R, r4_2R, r4_3R, r4_4R, r4_5R, r4_6R,
                r5_1R, r5_2R, r5_3R, r5_4R, r5_5R, r5_6R, r5_7R,
                r6_1R, r6_2R, r6_3R, r6_4R, r6_5R, r6_6R, r6_7R, r6_8R,
                r7_1R, r7_2R, r7_3R, r7_4R, r7_5R, r7_6R, r7_7R, r7_8R, r7_9R,
                r8_1R, r8_2R, r8_3R, r8_4R, r8_5R, r8_6R, r8_7R, r8_8R, r8_9R, r8_10R,
                r9_1R, r9_2R, r9_3R, r9_4R, r9_5R, r9_6R, r9_7R, r9_8R, r9_9R, r9_10R,
                r10_1R, r10_2R, r10_3R, r10_4R, r10_5R, r10_6R, r10_7R, r10_8R, r10_9R,
                r11_1R, r11_2R, r11_3R, r11_4R, r11_5R, r11_6R, r11_7R, r11_8R,
                r12_1R, r12_2R, r12_3R, r12_4R, r12_5R, r12_6R, r12_7R,
                r13_1R, r13_2R, r13_3R, r13_4R, r13_5R, r13_6R,
                r14_1R, r14_2R, r14_3R, r14_4R, r14_5R};

        for (int i = 0; i < arr.length; i++) {
            arr[i].setOnClickListener(this);
        }

        doneImg = findViewById(R.id.done_img);
        doneImg.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("EVENT", -1);
        if (id > 0) {
            new ThirdArea.Hall().execute();
//            MyView.clearList();
        }

//        if(MyView.getList().size()>0) {
//            for (int i = 0; i < MyView.getList().size(); i++) {
//                for (int j = 0; j < arr.length; j++) {
//                    int id = arr[j].getId();
//                    String[] array = getResources().getResourceEntryName(id).split("_");
//                    char[] chars = array[0].toCharArray();
//                    if (chars.length == 2) {
//                        String row = String.valueOf(chars[1]);
//                        LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
//                        if (lockedSeats.equals(lockedSeatsList.get(i))) {
//                            arr[j].changeMyBookingColor(lockedSeats);
//                        }
//                    } else if (chars.length == 3) {
//                        String row = String.valueOf(chars[1]) + String.valueOf(chars[2]);
//                        LockedSeats lockedSeats = new LockedSeats(Integer.parseInt(row), array[1]);
//                        if (lockedSeats.equals(lockedSeatsList.get(i))) {
//                            arr[j].changeMyBookingColor(lockedSeats);
//                        }
//                    }
//                }
//            }
//        }
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

        private AlertDialog dialog = new AlertDialog.Builder(ThirdArea.this)
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
                new AlertDialog.Builder(ThirdArea.this)
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
