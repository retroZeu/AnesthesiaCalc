package com.example.mzdoes.anesthesiacalc;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {

    private Patient currentPatient;
    private String description;

    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        TextView resultTextView = view.findViewById(R.id.textView_result);
        TextView descriptionTextView = view.findViewById(R.id.textView_description);

        resultTextView.setText(currentPatient.calculateDose() + " mg");
        descriptionTextView.setText(description);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPatient = getArguments().getParcelable("currentPatient");
        description = getArguments().getString("descString");

    }

    public static ResultsFragment newInstance(Patient patient) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable("currentPatient", patient);
        String type = "";
        String epinephrine = "";
        if (patient.isAnesthesiaType()) {type = "Lidocaine";} else {type = "Bupivocaine";}
        if (patient.isWithEpinephrine()) {epinephrine = "with Epinephrine";} else {epinephrine = "without Ephinephrine";}
        args.putString("descString", "for " + patient.getName() + " (" + patient.getWeight() + " kg), using " + type + " " + epinephrine);
        return fragment;
    }
}
