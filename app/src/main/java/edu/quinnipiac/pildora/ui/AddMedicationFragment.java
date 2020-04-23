package edu.quinnipiac.pildora.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import edu.quinnipiac.pildora.PildoraDatabaseHelper;
import edu.quinnipiac.pildora.Prescription;
import edu.quinnipiac.pildora.R;

public class AddMedicationFragment extends Fragment {

    //Contain all edit texts to store prescription attributes
    private static EditText _nameEditText;
    private static EditText _dosageEditText;
    private static EditText _qtyEditText;
    private static EditText _whenTakenEditText;
    private static Button _saveButton;//saves the prescription information
    //Prescription Object
    private static Prescription _prescription;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        final View layout = inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
        _nameEditText = (EditText) layout.findViewById(R.id.edittext_name);
        _dosageEditText = (EditText) layout.findViewById(R.id.edittext_dosage);
        _qtyEditText = (EditText) layout.findViewById(R.id.edittext_qty);
        _whenTakenEditText = (EditText) layout.findViewById(R.id.edittext_whenTaken);
        _saveButton = (Button) layout.findViewById(R.id.button_savePrescription);
        _saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast didItWork = new Toast(layout.getContext());
                //save the prescription by adding it into the database
                if(_nameEditText.getText().toString() == "" || _nameEditText.getText().toString() == " "){
                    didItWork = Toast.makeText(layout.getContext(), "Do not leave any values blank!", Toast.LENGTH_SHORT); }
                if(_dosageEditText.getText().toString() == "" || _dosageEditText.getText().toString() == " "){
                    didItWork = Toast.makeText(layout.getContext(), "Do not leave any values blank!", Toast.LENGTH_SHORT);
                }
                if(_qtyEditText.getText().toString() == "" || _qtyEditText.getText().toString() == " "){
                    didItWork = Toast.makeText(layout.getContext(), "Do not leave any values blank!", Toast.LENGTH_SHORT);
                }
                if(_whenTakenEditText.getText().toString() == "" || _whenTakenEditText.getText().toString() == " "){
                    didItWork = Toast.makeText(layout.getContext(), "Do not leave any values blank!", Toast.LENGTH_SHORT);
                }
                else {
                    didItWork = Toast.makeText(layout.getContext(), "Prescription Saved!", Toast.LENGTH_SHORT);
                    savePrescription();
                }


            }
        });

        return inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
    }

    public void savePrescription(){
        String name = _nameEditText.getText().toString();
        int dosage = Integer.parseInt(_dosageEditText.getText().toString());//converting text to int... hopefully that won't break with decimals
        int qty = Integer.parseInt(_qtyEditText.getText().toString());//converting text to int... hopefully that won't break with decimals
        String whenTaken = _whenTakenEditText.getText().toString();

        PildoraDatabaseHelper pildoraDBHelper = new PildoraDatabaseHelper(getLayoutInflater().getContext());
        SQLiteDatabase db = pildoraDBHelper.getWritableDatabase();

        pildoraDBHelper.insertPrescription(db, name, dosage, qty, whenTaken);

        /**
        ContentValues medVals = new ContentValues();
        medVals.put("NAME", name);
        medVals.put("DOSAGE", dosage);
        medVals.put("QTY", qty);
        medVals.put("TIMETAKEN", whenTaken);

        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper(getLayoutInflater().getContext());
        try{
            SQLiteDatabase db = pildoraDBHelper.getWritableDatabase();
            db.insert("MEDS", null, medVals);
            Log.d("-- Add Med Frag --", "Med Name:  " + name);
            pildoraDBHelper.onUpgrade(db, db.getVersion(), 3);
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "Database Unable to Add Prescription", Toast.LENGTH_LONG);
            toast.show();
        }

        **/


    }

}
