package com.example.mzdoes.anesthesiacalc;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private Patient currentPatient;
    private String result, type, epinephrine, description;
    private double concentration;
    private TextView resultTextView, descriptionTextView;
    private ImageButton shareButton;
    private FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent i = getIntent();
        concentration = i.getDoubleExtra("concentration", 1.0);
        currentPatient = i.getParcelableExtra("currentPatient");

        result = calculateDose() + " mg";
        if (currentPatient.isAnesthesiaType()) {type = "Lidocaine";} else {type = "Bupivocaine";}
        if (currentPatient.isWithEpinephrine()) {epinephrine = "with Epinephrine";} else {epinephrine = "without Ephinephrine";}
        description = "for " + currentPatient.getName() + " (" + currentPatient.getWeight() + " kg), using " + type + " " + epinephrine;

        resultTextView = findViewById(R.id.textView_result);
        descriptionTextView = findViewById(R.id.textView_description);
        resultTextView.setText(result); descriptionTextView.setText(description);

        shareButton = findViewById(R.id.imageButton);
        backButton = findViewById(R.id.floatingActionButton_backButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, result + " " + description);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public double calculateDose() {
        int allowableDose;
        if (currentPatient.isAnesthesiaType()) {
            if (currentPatient.isWithEpinephrine()) {allowableDose = 7;} else {allowableDose = 3;}
        } else {allowableDose = 2;}

        double weightComponent = currentPatient.getWeight()/10;

        double concentrationComponent = 1/concentration;
        Toast.makeText(this, "" + concentration, Toast.LENGTH_SHORT).show();

        double dose = allowableDose * weightComponent * concentrationComponent;
        return (dose);
    }
}
