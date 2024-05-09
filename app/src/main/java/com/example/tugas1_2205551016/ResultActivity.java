package com.example.tugas1_2205551016;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ResultActivity extends AppCompatActivity {

    TextView resultTextView, categoryTextView;
    Button shareButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.textViewResult);
        categoryTextView = findViewById(R.id.textViewCategory);
        shareButton = findViewById(R.id.buttonShare);
        backButton = findViewById(R.id.buttonBack);

        // Get BMI value from Intent
        final double bmi = getIntent().getDoubleExtra("BMI", 0);

        // Display BMI result and category
        resultTextView.setText(String.format("%.2f", bmi));
        String category = getBMICategory(bmi);
        categoryTextView.setText(category);

        // Set text color based on BMI category
        int colorId;
        if (category.equals("Underweight")) {
            colorId = R.color.underweight;
        } else if (category.equals("Normal weight")) {
            colorId = R.color.normal;
        } else if (category.equals("Overweight")) {
            colorId = R.color.overweight;
        } else {
            colorId = R.color.obesity;
        }
        categoryTextView.setTextColor(ContextCompat.getColor(this, colorId));

        // Share Button Click Listener
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement share functionality here
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "My BMI is: " + String.format("%.2f", bmi));
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        // Back Button Click Listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to previous activity
                finish();
            }
        });
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
