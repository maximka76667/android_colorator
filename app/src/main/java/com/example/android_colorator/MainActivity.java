package com.example.android_colorator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText inputRed, inputGreen, inputBlue;
    TextView tvColor;

    List<EditText> inputs;

    int[] colorValues = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputRed = (EditText) findViewById(R.id.inputRed);
        inputGreen = (EditText) findViewById(R.id.inputGreen);
        inputBlue = (EditText) findViewById(R.id.inputBlue);

        inputs = (List<EditText>) Arrays.asList(inputRed, inputGreen, inputBlue);

        tvColor = (TextView) findViewById(R.id.tvColor);

        for(int i = 0; i < inputs.size(); i++) {
            EditText input = inputs.get(i);
            // Input's index in array (used for color change)
            int numInput = i;

            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // String value of input
                    String inputValue = input.getText().toString();

                    // Variable for numeric input value
                    int colorIntensity;

                    // Check if input value is numeric
                    try {
                        colorIntensity = Integer.parseInt(inputValue);
                    } catch (NumberFormatException nfe) {
                        return;
                    }

                    // If value is greater than 255 - set 255
                    if(colorIntensity > 255) input.setText("255");

                    // If value is less than 0 - set 0
                    if(colorIntensity < 0) input.setText("0");

                    // If everything is ok - change color
                    changeColor(colorIntensity, numInput);
                }
            });
        }
    }

    private void changeColor(int colorIntensity, int i) {
        colorValues[i] = colorIntensity;
        updateColor();
    }

    private void updateColor() {
        int color = Color.rgb(colorValues[0], colorValues[1], colorValues[2]);
        tvColor.setBackgroundColor(color);
    }
}