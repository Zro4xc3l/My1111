package com.example.myffff;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String selectedSocket = null;
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
        setContentView(R.layout.activity_main);
    }

    // Method linked to the CPU Socket Selection Button
    public void showSocketSelectionPopup(View view) {
        final String[] socketOptions = {"LGA 1151", "LGA 1200", "AM4", "AM5"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select CPU Socket");

        Spinner socketSpinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, socketOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socketSpinner.setAdapter(adapter);

        builder.setView(socketSpinner);

        builder.setPositiveButton("Select", (dialog, which) -> {
            selectedSocket = socketSpinner.getSelectedItem().toString();
            TextView selectedSocketTextView = findViewById(R.id.selected_cpu_socket);
            selectedSocketTextView.setText("Selected CPU Socket: " + selectedSocket);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    // Method linked to the Assembly Guide Button
    public void showAssemblyGuide(View view) {
        if (selectedSocket == null) {
            showSocketSelectionPopup(view);
        } else {
            setContentView(R.layout.activity_assembly_guide);
            updateAssemblyStep();
        }
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
                    Toast.makeText(MainActivity.this, "Step marked as completed", Toast.LENGTH_SHORT).show();
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
