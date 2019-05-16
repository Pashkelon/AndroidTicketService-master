package com.example.biletiki2.UIclasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.halls.grosse_saal.GrosseSaal;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class EventInfo extends AppCompatActivity implements View.OnClickListener {

    private Event event;
    private int eventId;
    private ImageView eventImg;
    private TextView eventDescriptionTxt, eventDateTxt, eventTimeTxt, eventTicketsTxt, eventMinPriceTxt, eventMaxPriceTxt;
    private Button buyTicketsBtn;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Intent intent = getIntent();
        eventId = intent.getIntExtra("EVENT", -1);
        eventImg = findViewById(R.id.event_img);
        eventDescriptionTxt = findViewById(R.id.event_description_txt);
        eventDateTxt = findViewById(R.id.event_date_txt);
        eventTimeTxt = findViewById(R.id.event_time_txt);
        eventTicketsTxt = findViewById(R.id.event_tickets_txt);
        eventMinPriceTxt = findViewById(R.id.event_min_price_txt);
        eventMaxPriceTxt = findViewById(R.id.event_max_price_txt);
        buyTicketsBtn = findViewById(R.id.buy_tickets_btn);
        backImg = findViewById(R.id.back_img);
        backImg.setOnClickListener(this);
        buyTicketsBtn.setOnClickListener(this);

        new EventInformation().execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back_img) {
            finish();
        } else if (id == R.id.buy_tickets_btn) {
            MyView.clearList();
            if (event.getHall() == 1) {
                Intent intent = new Intent(this, KleinerSaal.class);
                StoreProvider.getInstance().saveEvent(event);
                intent.putExtra("EVENT", event.getEventId());
                startActivityForResult(intent, 2);
            } else if (event.getHall() == 2) {
                Intent intent = new Intent(this, GrosseSaal.class);
                StoreProvider.getInstance().saveEvent(event);
                intent.putExtra("EVENT", event.getEventId());
                startActivityForResult(intent, 2);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_CANCELED) {
            finish();
        }
    }

    //===========================================================================================
    class EventInformation extends AsyncTask<Void, Void, Event> {

        private AlertDialog dialog = new AlertDialog.Builder(EventInfo.this)
                .setView(R.layout.frame_progress_dialog)
                .create();

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected Event doInBackground(Void... voids) {
            try {
                event = OkHttpProvider.getInstance().getEventInfo(eventId);
                return event;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Event event) {
            if (event != null) {
                dialog.dismiss();
                long time = event.getEventStart();
                String eventStart = StoreProvider.getInstance().getDate(time);
                eventDateTxt.setText(eventStart);
                eventDescriptionTxt.setText(event.getDescription());
                eventTicketsTxt.setText(String.valueOf(event.getTicketsAvailable()));
                eventMinPriceTxt.setText(String.valueOf(event.getMinPrice()));
                eventMaxPriceTxt.setText(String.valueOf(event.getMaxPrice()));
                String[] arr = event.getImages();
                Picasso.get().load(arr[0]).into(eventImg);
            } else {
                dialog.dismiss();
                new AlertDialog.Builder(EventInfo.this)
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
    }
}
