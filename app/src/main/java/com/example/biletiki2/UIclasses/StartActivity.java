package com.example.biletiki2.UIclasses;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.StoreProvider;

import net.danlew.android.joda.JodaTimeAndroid;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView startArrow;
    private LinearLayout bottomSheet;
    private Button regBtn, logBtn, logCodeBtn;
    private TextView topRegTxt, customerTxt, forgotPswTxt, newCustomerTxt;
    private EditText emailTxt, passwordTxt;
    private ImageView backBtn;
    private RelativeLayout rlStart;
    private FrameLayout progressFrame;
    private AlertDialog dialog;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JodaTimeAndroid.init(this);

        StoreProvider.getInstance().setContext(this);
        if (StoreProvider.getInstance().getToken() != null) {
            tokenExist();
        }
        setContentView(R.layout.activity_start_actitivity);
        startArrow = findViewById(R.id.start_arrow);
        rlStart = findViewById(R.id.rl_start);
        startArrow.setOnClickListener(this);
    }

    private void tokenExist() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_arrow) {
            startArrow.setVisibility(View.GONE);
            Intent intent = new Intent(this, MainActivity .class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim,R.anim.anim);
            finish();
        }
    }




}
