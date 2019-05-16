package com.example.biletiki2.UIclasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.adapter.ShoppingCartAdapter;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class ShoppingCart extends AppCompatActivity implements View.OnClickListener {

    private TextView topCartTxt, noticeTxt, deleteTxt, eventNameTxt, ticketNumTxt, totalSumTxt, termsTxt,
            artistTxt, eventDateTxt;
    private Button payBtn;
    private ImageView backImg;
    private RecyclerView lockedSeatsList;
    private ShoppingCartAdapter adapter;
    private Event event = null;
    private long t;
    private String tmpNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        noticeTxt = findViewById(R.id.not_txt);
        deleteTxt = findViewById(R.id.delete_txt);
        eventNameTxt = findViewById(R.id.event_name_txt);
        ticketNumTxt = findViewById(R.id.ticket_num_txt);
        totalSumTxt = findViewById(R.id.total_sum_txt);
        termsTxt = findViewById(R.id.terms_txt);
        payBtn = findViewById(R.id.pay_btn);
        backImg = findViewById(R.id.back_img);
        artistTxt = findViewById(R.id.artist_txt);
        eventDateTxt = findViewById(R.id.event_date_txt);

        lockedSeatsList = findViewById(R.id.locked_seats_list);

        payBtn.setOnClickListener(this);
        backImg.setOnClickListener(this);
        termsTxt.setOnClickListener(this);
        deleteTxt.setOnClickListener(this);

        String list = StoreProvider.getInstance().getList();
        t = StoreProvider.getInstance().getTime();
        if (t != 0) {
            if ((System.currentTimeMillis() - t) >= 600000) {
                bookingCanceledDialog();
            } else if (list != null) {
                Gson gson = new Gson();
                List<LockedSeats> l = gson.fromJson(list, new TypeToken<List<LockedSeats>>() {
                }.getType());
                adapter = new ShoppingCartAdapter(l);
                LinearLayoutManager manager = new LinearLayoutManager(ShoppingCart.this);
                lockedSeatsList.setLayoutManager(manager);
                DividerItemDecoration divider = new DividerItemDecoration(ShoppingCart.this,
                        manager.getOrientation());
                lockedSeatsList.addItemDecoration(divider);
                lockedSeatsList.setAdapter(adapter);
                ticketNumTxt.setText(String.valueOf(StoreProvider.getInstance().getTicketsNum()) + " tickets");
                event = gson.fromJson(StoreProvider.getInstance().getEvent(), Event.class);
                eventNameTxt.setText(event.getEventName());
                artistTxt.setText(event.getArtist());
                totalSumTxt.setText(String.valueOf(StoreProvider.getInstance().getTotalPrice()));

                String time = StoreProvider.getInstance().getDate(event.getEventStart());
                eventDateTxt.setText(time);
            }
        }
    }

    private void bookingCanceledDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Sorry, your booking has been canceled!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StoreProvider.getInstance().removeTime();
                        StoreProvider.getInstance().removeTmpNumber();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back_img) {
            finish();
        } else if (id == R.id.pay_btn) {
            if (event == null) {
                new AlertDialog.Builder(this)
                        .setMessage("Sorry, but there is nothing to pay for!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else if ((System.currentTimeMillis() - t) >= 600000) {
                bookingCanceledDialog();
            } else {
                Intent intent = new Intent(this, PayActivity.class);
                startActivityForResult(intent, 4);
            }
        } else if (id == R.id.terms_txt) {
            Intent intent = new Intent(this, TermsAndConditions.class);
            startActivity(intent);
        } else if (id == R.id.delete_txt) {
            clear();
            tmpNumber = StoreProvider.getInstance().getTmpNumber();
            if(tmpNumber!=null) {
                new CancelBooking().execute();
            }else {
                new AlertDialog.Builder(this)
                        .setMessage("Something wrong!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create().show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void clear() {
        StoreProvider.getInstance().removeList();
        StoreProvider.getInstance().removeEvent();
        StoreProvider.getInstance().removeTicketsNum();
        StoreProvider.getInstance().removeTotalPrice();
        StoreProvider.getInstance().removeTime();
//        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK) {
            finish();
        }
    }

    class CancelBooking extends AsyncTask<Void, Void, String> {

        private AlertDialog dialog = new AlertDialog.Builder(ShoppingCart.this)
                .setView(R.layout.frame_progress_dialog)
                .create();
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res = "";
            try {
                OkHttpProvider.getInstance().deleteTemporaryBookingSeats(tmpNumber);
                res = "Success!";
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("Success!")){
                dialog.dismiss();
                StoreProvider.getInstance().saveTmpBookingNumber(s);
                new AlertDialog.Builder(ShoppingCart.this)
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            }else {
                dialog.dismiss();
                new AlertDialog.Builder(ShoppingCart.this)
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
