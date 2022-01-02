package com.example.retinolab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class PaypalTest extends AppCompatActivity {

    TextView m_response;
    Button click;

    PayPalConfiguration  m_configuration;
    //the id is the link to the paypal account
    String m_paypalClientId= "AaOjKEoDoUu6CTJfrbiXoXXvXeCVywiwhiLnPfP1b4p52CuQUhLThHzG-D6lwFomDLGHNWsV5gCejFB3";
    Intent m_service;
    int m_paypalRequestCode = 999; //any number




    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_test);

        //m_response= (TextView) findViewById(R.id.response);

        click = findViewById(R.id.payer);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Login.class));
                pay(v);

            }
        });


        m_configuration=  new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        startService(m_service);// paypal srvice, linstinning to calls to paypal apps

    }

    void pay(View view){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(30), "USD", "Test payment with paypal",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent, m_paypalRequestCode);
    }


    protected void onActivityResult(int requestCode, int resultCode,  Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == m_paypalRequestCode)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation !=null)
                {
                    String state = confirmation.getProofOfPayment().getState();

                    if(state.equals("approuved"))
                        m_response.setText("Payment approuved");

                    else
                        m_response.setText("Error in the payment");
                }
                else
                    m_response.setText("confirmation is null");
            }
        }
    }
}