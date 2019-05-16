package com.example.biletiki2.halls.grosse_saal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.biletiki2.R;
import com.example.biletiki2.UIclasses.MyView;
import com.example.biletiki2.UIclasses.ShoppingCart;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.halls.grosse_saal.areas.FirstArea;
import com.example.biletiki2.halls.grosse_saal.areas.SecondArea;
import com.example.biletiki2.halls.grosse_saal.areas.ThirdArea;

import java.util.List;

public class GrosseSaal extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout firstArea, secondArea, thirdArea;
    private int id;
    private ImageView doneImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grosse_saal);
        firstArea = findViewById(R.id.first_area);
        secondArea = findViewById(R.id.second_area);
        thirdArea = findViewById(R.id.third_area);
        doneImg = findViewById(R.id.done_img);
        doneImg.setOnClickListener(this);
        firstArea.setOnClickListener(this);
        secondArea.setOnClickListener(this);
        thirdArea.setOnClickListener(this);
        id = getIntent().getIntExtra("EVENT", -1);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.first_area) {
            Intent intent = new Intent(this, FirstArea.class);
            intent.putExtra("EVENT", id);
            startActivity(intent);
        } else if (i == R.id.second_area) {
            Intent intent = new Intent(this, SecondArea.class);
            intent.putExtra("EVENT", id);
            startActivity(intent);
        } else if (i == R.id.third_area) {
            Intent intent = new Intent(this, ThirdArea.class);
            intent.putExtra("EVENT", id);
            startActivity(intent);
        } else if (i == R.id.done_img) {
            if (MyView.getList().size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("You haven't choosed any seat!")
                        .setPositiveButton("Ok", null)
                        .setCancelable(false)
                        .show();
            } else {
                new Booking().execute();
            }
        }
    }

    class Booking extends AsyncTask<Void, Void, String> {

        private List<LockedSeats> list = MyView.getList();
        private AlertDialog dialog = new AlertDialog.Builder(GrosseSaal.this)
                .setView(R.layout.frame_progress_dialog)
                .create();
        private String tmpBookingNumber;

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            tmpBookingNumber = "Reserving is successful!";
            try {
                OkHttpProvider.getInstance().getTmpBookingNumber(id, list);
            } catch (Exception e) {
                e.printStackTrace();
//                tmpBookingNumber = e.toString();
            }
            return tmpBookingNumber;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("Reserving is successful!")) {
                dialog.dismiss();
                StoreProvider.getInstance().saveTmpBookingNumber(s);
                new AlertDialog.Builder(GrosseSaal.this)
                        .setMessage(tmpBookingNumber)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StoreProvider.getInstance().saveTime(System.currentTimeMillis());
                                MyView.saveList();
                                Intent intent = new Intent(GrosseSaal.this, ShoppingCart.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                dialog.dismiss();
                new AlertDialog.Builder(GrosseSaal.this)
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
