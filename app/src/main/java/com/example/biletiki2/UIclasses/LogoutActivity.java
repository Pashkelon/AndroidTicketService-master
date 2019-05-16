package com.example.biletiki2.UIclasses;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.OkHttpProvider;
import com.example.biletiki2.data.StoreProvider;

import java.io.IOException;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView startArrow;
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
        setContentView(R.layout.activity_logout);
        regBtn = findViewById(R.id.reg_btn);
        logBtn = findViewById(R.id.log_btn);
        logCodeBtn = findViewById(R.id.log_code_btn);
        topRegTxt = findViewById(R.id.top_registration_txt);
        customerTxt = findViewById(R.id.customer_txt);
        forgotPswTxt = findViewById(R.id.forgot_psw_txt);
        newCustomerTxt = findViewById(R.id.new_txt);
        emailTxt = findViewById(R.id.email_txt);
        passwordTxt = findViewById(R.id.pasw_txt);
        backBtn = findViewById(R.id.back_img);
        progressFrame = findViewById(R.id.progress_frame);
        progressFrame.setOnClickListener(null);

        regBtn.setOnClickListener(this);
        logBtn.setOnClickListener(this);
        logCodeBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    private void tokenExist() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void isValid(String email, String password) {
        if(email.isEmpty()||password.isEmpty()){
            dialog = new AlertDialog.Builder(LogoutActivity.this)
                    .setTitle("ERROR")
                    .setMessage("All fields must be filled")
                    .setPositiveButton("Ok", null)
                    .setCancelable(false)
                    .create();
            dialog.show();
        }else {
            new LoginTask().execute();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.log_btn) {
            email = emailTxt.getText().toString().trim();
            password = passwordTxt.getText().toString().trim();
            isValid(email, password);
        } else if (v.getId() == R.id.reg_btn) {
            Intent intent = new Intent(this, RegistrationInput.class);
            startActivity(intent);
        } else if (v.getId() == R.id.log_code_btn) {

        } else if (v.getId() == R.id.back_img) {

        }
    }
//==========================================================================================
    class LoginTask extends AsyncTask<Void,Void,String> {
        private boolean isSuccess = true;

        @Override
        protected void onPreExecute() {
            progressFrame.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String res = "Login ok";
            try {
                String token = OkHttpProvider.getInstance().login(email,password);
                StoreProvider.getInstance().save(token);
            }catch (IOException ex){
                isSuccess = false;
                res = "Connection Error!";
            }catch (Exception e) {
                isSuccess = false;
                res = e.getMessage();
            }

            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            progressFrame.setVisibility(View.GONE);
            if(isSuccess) {
                tokenExist();
            }else{
                new AlertDialog.Builder(LogoutActivity.this)
                        .setMessage(s)
                        .setCancelable(false)
                        .setPositiveButton("Ok",null)
                        .create()
                        .show();
            }
        }
    }
}
