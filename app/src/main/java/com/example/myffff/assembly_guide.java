package com.example.myffff;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class assembly_guide extends AppCompatActivity {

    private int currentStep = 0;
    private final String[] steps = {
            "Step 1: Install the CPU",
            "Step 2: Install the CPU Cooler",
            "Step 3: Insert RAM",
            "Step 4: Install GPU"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_guide);
        updateAssemblyStep();

        Button nextButton = findViewById(R.id.next_step_button);
        Button previousButton = findViewById(R.id.previous_step_button);

        nextButton.setOnClickListener(this::onNextStep);
        previousButton.setOnClickListener(this::onPreviousStep);
    }

    // Update assembly step details
    private void updateAssemblyStep() {
        if (currentStep < steps.length) {
            TextView stepTitle = findViewById(R.id.assembly_step_title);
            TextView stepDescription = findViewById(R.id.assembly_step_description);
            ProgressBar progressBar = findViewById(R.id.step_progress_bar);

            stepTitle.setText(steps[currentStep]);
            stepDescription.setText("Description for " + steps[currentStep]);
            progressBar.setProgress((currentStep + 1) * 100 / steps.length);

            CheckBox stepCompleted = findViewById(R.id.step_completed_checkbox);
            stepCompleted.setChecked(false);
            stepCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    Toast.makeText(assembly_guide.this, "Step marked as completed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Method linked to Next Button
    public void onNextStep(View view) {
        if (currentStep < steps.length - 1) {
            currentStep++;
            updateAssemblyStep();
        } else {
            Toast.makeText(this, "Assembly complete!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method linked to Previous Button
    public void onPreviousStep(View view) {
        if (currentStep > 0) {
            currentStep--;
            updateAssemblyStep();
        }
    }
}
