package com.example.mzdoes.anesthesiacalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton nextButton;
    private EditText nameEditText, weightEditText;
    private RadioGroup anesthesiaRadioGroup;
    private CheckBox epinephrineCheckBox;

    private Patient patient;

    private String nameHolder;
    private int weightHolder;
    private boolean withEpinephrineHolder, anesthesiaTypeHolder; //type: true = lidocaine, false = bupivocaine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patient = new Patient();
        nextButton = findViewById(R.id.floatingActionButton);
        nameEditText = findViewById(R.id.editText_name);
        weightEditText = findViewById(R.id.editText_weight);
        anesthesiaRadioGroup = findViewById(R.id.radioGroup);
        epinephrineCheckBox = findViewById(R.id.checkBox_epinephrine);

        anesthesiaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (radioGroup.indexOfChild(findViewById(checkedId)) == 0) {anesthesiaTypeHolder = true;}
                else if (radioGroup.indexOfChild(findViewById(checkedId)) == 1) {anesthesiaTypeHolder = false;}
            }
        });

        epinephrineCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                withEpinephrineHolder = isChecked;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameHolder = nameEditText.getText().toString();
                weightHolder = Utility.getNextInt(weightEditText.getText().toString());

                patient.setName(nameHolder); patient.setWeight(weightHolder);
                patient.setWithEpinephrine(withEpinephrineHolder); patient.setAnesthesiaType(anesthesiaTypeHolder);



            }
        });
    }

    public String getNameHolder() {
        return nameHolder;
    }

    public void setNameHolder(String nameHolder) {
        this.nameHolder = nameHolder;
    }

    public int getWeightHolder() {
        return weightHolder;
    }

    public void setWeightHolder(int weightHolder) {
        this.weightHolder = weightHolder;
    }

    public boolean isWithEpinephrineHolder() {
        return withEpinephrineHolder;
    }

    public void setWithEpinephrineHolder(boolean withEpinephrineHolder) {
        this.withEpinephrineHolder = withEpinephrineHolder;
    }

    public boolean isAnesthesiaTypeHolder() {
        return anesthesiaTypeHolder;
    }

    public void setAnesthesiaTypeHolder(boolean anesthesiaTypeHolder) {
        this.anesthesiaTypeHolder = anesthesiaTypeHolder;
    }
}
