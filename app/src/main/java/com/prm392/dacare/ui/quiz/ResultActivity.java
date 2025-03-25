package com.prm392.dacare.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prm392.dacare.MainActivity;
import com.prm392.dacare.R;
import com.prm392.dacare.model.SkinType;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button backButton;
    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize UI components
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton);

        // Get skin type from Intent
        SkinType skinType = (SkinType) getIntent().getSerializableExtra("skinType");
        if (skinType != null) {
            Log.i(TAG, "SkinType received: " + skinType.getType());
            resultTextView.setText("Your skin type: " + skinType.getType() + "\n\n" +
                    "Description: " + skinType.getDescription() + "\n\n" +
                    "Treatment: " + skinType.getTreatment());
        } else {
            Log.w(TAG, "No SkinType received in Intent");
            resultTextView.setText("No skin type data available.");
        }

        // Handle back button click to return to RoutineFragment
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("navigateToRoutine", true);
            startActivity(intent);
            finish();
        });
    }
}