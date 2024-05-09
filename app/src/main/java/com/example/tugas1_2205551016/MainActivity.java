package com.example.tugas1_2205551016;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText heightInput, weightInput;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightInput = findViewById(R.id.editTextHeight);
        weightInput = findViewById(R.id.editTextWeight);
        calculateButton = findViewById(R.id.buttonCalculate);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get height and weight input
                double height = Double.parseDouble(heightInput.getText().toString());
                double weight = Double.parseDouble(weightInput.getText().toString());

                // Calculate BMI
                double bmi = calculateBMI(height, weight);

                // Start Activity2 and pass BMI value
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("BMI", bmi);
                startActivity(intent);
            }
        });
    }

    private double calculateBMI(double height, double weight) {
        // BMI Formula: weight (kg) / (height (m) * height (m))
        return weight / ((height / 100) * (height / 100));
    }
}
