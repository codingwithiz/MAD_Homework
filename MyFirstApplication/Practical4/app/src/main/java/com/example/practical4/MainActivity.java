package com.example.practical4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox CBRed = findViewById(R.id.CBRed);
        CheckBox CBGreen = findViewById(R.id.CBGreen);
        CheckBox CBBlue = findViewById(R.id.CBBlue);
        final ConstraintLayout CSLayout = findViewById(R.id.CLayoutCS);
        final Button BtnCalculate = findViewById(R.id.BtnCalculate);
        BtnCalculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText Num1 = findViewById(R.id.ETNum1);
                EditText Num2 = findViewById(R.id.ETNum2);
                TextView TVResult = findViewById(R.id.TVResult);
                Double Result = Double.parseDouble(Num1.getText().toString()) +
                        Double.parseDouble(Num2.getText().toString());
                TVResult.setText(Double.toString(Result));
            }
        });

        CBRed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CSLayout.setBackgroundColor(Color.RED);
                }
                else{
                    CSLayout.setBackgroundColor(Color.WHITE);
                }
            }
        });

        CBGreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CSLayout.setBackgroundColor(Color.GREEN);
                }
                else{
                    CSLayout.setBackgroundColor(Color.WHITE);
                }
            }
        });

        CBBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CSLayout.setBackgroundColor(Color.BLUE);
                }
                else{
                    CSLayout.setBackgroundColor(Color.WHITE);
                }
            }
        });

    }

}