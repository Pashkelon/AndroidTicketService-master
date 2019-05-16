package com.example.biletiki2.UIclasses;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biletiki2.R;
import com.example.biletiki2.config.Config;
import com.example.biletiki2.data.StoreProvider;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.LockedSeats;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backImg;
    private Button payBtn;
    private TextView eventNameTxt, totalSumTxt, ticketNumTxt;
    private CheckBox checkBoxPay;
    private Event event;
    private Gson gson;
    private List<LockedSeats> l;
    public static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        backImg = findViewById(R.id.back_img);
        payBtn = findViewById(R.id.pay_btn);
        eventNameTxt = findViewById(R.id.event_name_txt);
        totalSumTxt = findViewById(R.id.total_sum_txt);
        ticketNumTxt = findViewById(R.id.ticket_num_txt);
        checkBoxPay = findViewById(R.id.checkBox_pay);

        backImg.setOnClickListener(this);
        payBtn.setOnClickListener(this);

        gson = new Gson();
        event = gson.fromJson(StoreProvider.getInstance().getEvent(), Event.class);

        String list = StoreProvider.getInstance().getList();
        l = gson.fromJson(list, new TypeToken<List<LockedSeats>>() {
        }.getType());
        if (event != null && l != null) {
            String time = StoreProvider.getInstance().getDate(event.getEventStart());

            ticketNumTxt.setText(String.valueOf(StoreProvider.getInstance().getTicketsNum()) + " tickets");
            eventNameTxt.setText(event.getEventName() + ", " + event.getArtist() + ", " + time);
            totalSumTxt.setText(String.valueOf(StoreProvider.getInstance().getTotalPrice()));
        }

        Intent intent = new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back_img) {
            finish();
        } else if (id == R.id.pay_btn) {
           if (checkBoxPay.isChecked()) {
               PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(totalSumTxt.getText().toString())),"USD",event.getEventName(),
                       PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);
            } else {
                new AlertDialog.Builder(PayActivity.this)
                        .setMessage("Please, select a payment method!")
                        .setPositiveButton("Ok", null)
                        .setCancelable(false)
                        .create()
                        .show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE && resultCode == RESULT_OK) {
            PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirmation!=null){
                try {
                    String paymentDetails = confirmation.toJSONObject().toString(4);
                    startActivity(new Intent(this, SuccessActivity.class)
                            .putExtra("PaymentDetails", paymentDetails)
                            .putExtra("PaymentAmount", totalSumTxt.getText().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            setResult(RESULT_OK);
            finish();
        } else if (requestCode == PAYPAL_REQUEST_CODE && resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
