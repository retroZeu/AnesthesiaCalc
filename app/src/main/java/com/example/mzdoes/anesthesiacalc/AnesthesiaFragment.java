package com.example.mzdoes.anesthesiacalc;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnesthesiaFragment extends Fragment {


    private boolean type, epinephrine;

    public AnesthesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_anesthesia, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (radioGroup.indexOfChild(view.findViewById(checkedId)) == 0) {type = true;}
                else if (radioGroup.indexOfChild(view.findViewById(checkedId)) == 1) {type = false;}
            }
        });

        final CheckBox epinephrineCheckBox = view.findViewById(R.id.checkBox_epinephrine);
        epinephrineCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                epinephrine = isChecked;
            }
        });
        return view;
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioButton_lidocaine:
//                if (checked)
//                    type = true;
//                    break;
//            case R.id.radioButton_bupivocaine:
//                if (checked)
//                    type = false;
//                    break;
//        }
//    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkBox_epinephrine:
                if (checked) {epinephrine = true;} else {epinephrine = false;}
                break;
        }
    }


    public boolean isType() {
        return type;
    }

    public boolean isEpinephrine() {
        return epinephrine;
    }

    public static AnesthesiaFragment newInstance() {
        AnesthesiaFragment fragment = new AnesthesiaFragment();
        return fragment;
    }
}
