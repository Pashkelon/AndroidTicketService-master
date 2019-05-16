package com.example.biletiki2.UIclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.LockedSeats;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SuccessActivity extends AppCompatActivity implements View.OnClickListener {

    private Button downloadBtn;
    private TextView eventNameTxt, totalSumTxt, ticketNumTxt, returnTxt;
    private Event event;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        downloadBtn = findViewById(R.id.download_btn);
        eventNameTxt = findViewById(R.id.event_name_txt);
        totalSumTxt = findViewById(R.id.total_sum_txt);
        ticketNumTxt = findViewById(R.id.ticket_num_txt);
        returnTxt = findViewById(R.id.return_txt);

        returnTxt.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);

        gson = new Gson();
        event = gson.fromJson(StoreProvider.getInstance().getEvent(), Event.class);

        String list = StoreProvider.getInstance().getList();
        List<LockedSeats> l = gson.fromJson(list, new TypeToken<List<LockedSeats>>() {
        }.getType());
        if (event != null && l != null) {
            String time = StoreProvider.getInstance().getDate(event.getEventStart());

            ticketNumTxt.setText(String.valueOf(StoreProvider.getInstance().getTicketsNum()) + " tickets");
            eventNameTxt.setText(event.getEventName() + ", " + event.getArtist() + ", " + time);
            totalSumTxt.setText(String.valueOf(StoreProvider.getInstance().getTotalPrice()));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.return_txt) {
                setResult(RESULT_OK);
                finish();
        } else if (id == R.id.download_btn) {
            setResult(RESULT_OK);
            finish();
        }
    }

}
