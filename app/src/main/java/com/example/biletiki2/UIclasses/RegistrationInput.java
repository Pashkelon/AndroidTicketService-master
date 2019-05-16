package com.example.biletiki2.UIclasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.OkHttpProvider;

import java.io.IOException;

public class RegistrationInput extends AppCompatActivity implements View.OnClickListener {
    private Spinner genderSpinner;
    private ConstraintLayout regInpConstr;
    private TextView topRegInpTxt, headerTxt, reg1Txt, reg2Txt, reg3Txt, reg4Txt, reg5Txt, reg6Txt,
            reg7Txt, reg8Txt;
    private Button regBtn;
    private ImageView backImg;
    private TextInputEditText nameInp, surnameInp, emailInp, passwordInp, phoneInp, cityInp, countryInp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_input);
        genderSpinner = findViewById(R.id.gender_sp);
        regInpConstr = findViewById(R.id.reg_inp_constr);
        topRegInpTxt = findViewById(R.id.top_reg_inp_txt);
        headerTxt = findViewById(R.id.header_txt);
        reg1Txt = findViewById(R.id.reg1_txt);
        reg2Txt = findViewById(R.id.reg2_txt);
        reg3Txt = findViewById(R.id.reg3_txt);
        reg4Txt = findViewById(R.id.reg4_txt);
        reg5Txt = findViewById(R.id.reg5_txt);
        reg6Txt = findViewById(R.id.reg6_txt);
        reg7Txt = findViewById(R.id.reg7_txt);
        reg8Txt = findViewById(R.id.reg8_txt);
        regBtn = findViewById(R.id.register_btn);
        backImg = findViewById(R.id.back_img);
        nameInp = findViewById(R.id.name_inp);
        surnameInp = findViewById(R.id.surname_inp);
        emailInp = findViewById(R.id.email_inp);
        passwordInp = findViewById(R.id.password_inp);
        phoneInp = findViewById(R.id.phone_inp);
        cityInp = findViewById(R.id.city_inp);
        countryInp = findViewById(R.id.country_inp);

        backImg.setOnClickListener(this);
        regBtn.setOnClickListener(this);

        setAdapter();
    }

    private void setAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_img) {
            finish();
        } else if (v.getId() == R.id.register_btn) {
            new RegistrationTask().execute();
        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LogoutActivity.class);
        startActivity(intent);
    }
//============================================================================================

    class RegistrationTask extends AsyncTask<Void, Void, String> {
        private boolean isSuccess = true;
        private AlertDialog progressDialog;
        private String name, surname, email, password, phone, city, country, gender;
        private ProgressBar progressBar = findViewById(R.id.progress_bar);

        @Override
        protected void onPreExecute() {
            name = nameInp.getText().toString();
            surname = surnameInp.getText().toString();
            email = emailInp.getText().toString();
            password = passwordInp.getText().toString();
            phone = phoneInp.getText().toString();
            city = cityInp.getText().toString();
            country = countryInp.getText().toString();
//            gender = genderSpinner.getSelectedItem().toString();
            gender = "1";
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res = "Registration ok!";
            try {
                OkHttpProvider.getInstance().registration(email, name, gender,
                        surname, password, phone);
            } catch (IOException ex) {
                isSuccess = false;
                res = "Connection error! Check your internet!";
            } catch (Exception e) {
                isSuccess = false;
                res = e.getMessage();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            if (isSuccess) {
                progressDialog = new AlertDialog.Builder(RegistrationInput.this)
                        .setView(R.layout.reg_success)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startLoginActivity();
                            }
                        })
                        .setCancelable(false)
                        .create();
                progressDialog.show();
            } else {
                new AlertDialog.Builder(RegistrationInput.this)
                        .setTitle("Error!...")
                        .setMessage(s)
                        .setPositiveButton("ok", null)
                        .setCancelable(false)
                        .create()
                        .show();
            }
        }
    }
}
