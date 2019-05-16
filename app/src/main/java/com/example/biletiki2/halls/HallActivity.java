package com.example.biletiki2.halls;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.biletiki2.R;
import com.example.biletiki2.halls.grosse_saal.GrosseSaal;
import com.example.biletiki2.halls.kleiner_saal.KleinerSaal;

public class HallActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout grosseSaal, kleinerSaal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_hall);
        grosseSaal = findViewById(R.id.grosse_saal_frame);
        kleinerSaal = findViewById(R.id.kleiner_saal_frame);
        grosseSaal.setOnClickListener(this);
        kleinerSaal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.grosse_saal_frame){
            Intent intent = new Intent(this, GrosseSaal.class);
            startActivity(intent);
        }else if (id == R.id.kleiner_saal_frame){
            Intent intent = new Intent(this, KleinerSaal.class);
            startActivity(intent);
        }
    }
}
