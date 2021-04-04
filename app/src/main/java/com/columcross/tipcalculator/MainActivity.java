package com.columcross.tipcalculator;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import androidx.wear.ambient.AmbientModeSupport;

public class MainActivity extends WearableActivity {

    //private TextView tipView;
    private TextView totalView;
    private EditText basePriceInput;
    private EditText tipInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tipView = (TextView) findViewById(R.id.tip);
        totalView = (TextView) findViewById(R.id.total);
        basePriceInput = (EditText) findViewById(R.id.editTextTotal);
        tipInput = (EditText) findViewById(R.id.editTextTipPercentage);

        // Enables Always-on
        //setAmbientEnabled();
    }

    public void calculateTip(View view) {
        StringBuilder amountDisplay = new StringBuilder();
        amountDisplay.append(getResources().getString(R.string.totals_line_front));
        BigDecimal basePrice;
        BigDecimal tipPercentage;

        try {
            basePrice = new BigDecimal(basePriceInput.getText().toString());
            tipPercentage = new BigDecimal(tipInput.getText().toString());
        } catch (NumberFormatException e) {
            totalView.setText(getString(R.string.error_message));
            return;
        }

        BigDecimal tipAmount = basePrice.multiply(tipPercentage.divide(BigDecimal.valueOf(100)));
        //tipView.setText(tipAmount.setScale(2,BigDecimal.ROUND_UP).toString());
        amountDisplay.append(tipAmount.setScale(2,BigDecimal.ROUND_UP).toString());

        amountDisplay.append(getResources().getString(R.string.totals_line_middle));

        BigDecimal totalAmount = basePrice.add(tipAmount);
        //totalView.setText(totalAmount.setScale(2,BigDecimal.ROUND_UP).toString());
        amountDisplay.append(totalAmount.setScale(2,BigDecimal.ROUND_UP).toString());

        totalView.setText(amountDisplay.toString());

    }

}