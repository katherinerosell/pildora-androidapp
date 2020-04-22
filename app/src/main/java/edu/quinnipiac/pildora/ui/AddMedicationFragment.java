package edu.quinnipiac.pildora.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.quinnipiac.pildora.R;

public class AddMedicationFragment extends Fragment {

    //Contain all edit texts to store prescription attributes
    private static EditText _nameEditText;
    private static EditText _dosageEditText;
    private static EditText _qtyEditText;
    private static EditText _whenTakenEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
        _nameEditText = (EditText) layout.findViewById(R.id.edittext_name);
        _dosageEditText = (EditText) layout.findViewById(R.id.edittext_dosage);
        _qtyEditText = (EditText) layout.findViewById(R.id.edittext_qty);
        _whenTakenEditText = (EditText) layout.findViewById(R.id.edittext_whenTaken);

        return inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
    }



}
