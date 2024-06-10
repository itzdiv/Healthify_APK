package com.example.healthkit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4_bmi extends AppCompatActivity {

    private EditText editWeight;
    private EditText editHeight;
    private Button calculateButton;
    private TextView textResult;
    private Button backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_bmi);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editWeight = findViewById(R.id.edit_systolic);
        editHeight = findViewById(R.id.edit_diastolic);
        calculateButton = findViewById(R.id.calculate_button);
        textResult = findViewById(R.id.text_result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editWeight.getText().toString();
        String heightStr = editHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            float bmi = weight / ((height / 100) * (height / 100));


            String bmiResult = String.format("%.2f", bmi);

            textResult.setText("BMI: " + bmiResult);

            String message;
            if (bmi < 18.5) {
                message = "Your BMI is " + bmiResult + ". It falls within the underweight range.";
            } else if (bmi >= 18.5 && bmi < 25) {
                message = "Your BMI is " + bmiResult + ". It falls within the healthy weight range.";
            } else if (bmi >= 25 && bmi < 30) {
                message = "Your BMI is " + bmiResult + ". It falls within the overweight range.";
            } else {
                message = "Your BMI is " + bmiResult + ". It falls within the obesity range.";
            }

            Toast.makeText(MainActivity4_bmi.this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity4_bmi.this, "Please enter weight and height values", Toast.LENGTH_SHORT).show();
        }
    }

}
