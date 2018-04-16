package com.example.mzdoes.anesthesiacalc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameWeightFragment extends Fragment {

    private EditText nameEditText, weightEditText;

    public NameWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_name_weight, container, false);
        nameEditText = view.findViewById(R.id.editText_name);
        weightEditText = view.findViewById(R.id.editText_weight);

        return view;
    }

    public static NameWeightFragment newInstance() {
        NameWeightFragment fragment = new NameWeightFragment();

        return fragment;
    }

    public String getNameHolder() {
        String fromEditText = nameEditText.getText().toString();
        return fromEditText;
    }

    public int getWeightHolder() {
        int fromEditText = Utility.getNextInt(weightEditText.getText().toString());
        return fromEditText;
    }
}
