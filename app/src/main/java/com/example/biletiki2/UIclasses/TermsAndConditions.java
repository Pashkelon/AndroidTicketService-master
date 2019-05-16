package com.example.biletiki2.UIclasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.biletiki2.R;

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener {
    private ImageView backImg;
    private Button termsReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        backImg = findViewById(R.id.back_img);
        termsReturnButton = findViewById(R.id.terms_return_button);
        backImg.setOnClickListener(this);
        termsReturnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
