package edu.quinnipiac.pildora.ui;

import android.content.ContentValues;
import android.content.Context;
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

public class AddMedicationFragment extends Fragment implements View.OnClickListener {

    //Contain all edit texts to store prescription attributes
    private static EditText _nameEditText;
    private static EditText _dosageEditText;
    private static EditText _qtyEditText;
    private static EditText _whenTakenEditText;
    private static Button _saveButton;//saves the prescription information
    //Test
    //private static AddPrescriptionsTest testClass;

    private static View _layout;
    //Prescription Object
    private static Prescription _prescription;
    //private PildoraDatabaseHelper pildoraDBHelper;
    //private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        Log.d("-- ADD MED FRAGMENT ---", "-----------------------    ADD MED FRAGMENT    -------------------------");
        final View layout = inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
        _layout = layout;//for some reason does not work after app is closed then opened? using getView in onStart is more reliable
        //Create and add a database and reference to MEDS db, pass to test
        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper(getLayoutInflater().getContext());
        SQLiteDatabase mydb = pildoraDBHelper.getWritableDatabase();
        //testClass = new AddPrescriptionsTest(mydb);
        return inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(_layout != null){
            _saveButton = (Button) _layout.findViewById(R.id.button_savePrescription);
            _saveButton.setOnClickListener(this);
            Log.d("-- ADD MED FRAGMENT ---", "-----------------------    Save button initialized    -------------------------");
            /**
             * BUG FIX - list view does not update to display medications. why? was it database related? NO
             * The bellow views were removed from the onCreateView function and moved to onStart. Unclear why this works?
             * - i have to check the lifecycle states again
             */
            _nameEditText = (EditText) _layout.findViewById(R.id.edittext_name);
            _dosageEditText = (EditText) _layout.findViewById(R.id.edittext_dosage);
            _qtyEditText = (EditText) _layout.findViewById(R.id.edittext_qty);
            _whenTakenEditText = (EditText) _layout.findViewById(R.id.edittext_whenTaken);
        }
    }

    public void doTest(){
        Toast doTestToast = Toast.makeText(_layout.getContext(), "Test Run!", Toast.LENGTH_LONG);
        doTestToast.show();
        //testClass.runTest();
    }


    @Override
    public void onClick(View v) {
        Log.d("------ ADD MED ------", "---------- Save Button ----------");
        Toast saveToast = Toast.makeText(v.getContext(), "New Prescription Added! Check Home Screen.", Toast.LENGTH_LONG);
        saveToast.show();
        savePrescription();
    }

    public void savePrescription(){
        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper(getLayoutInflater().getContext());
        SQLiteDatabase db = pildoraDBHelper.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + "MEDS", null);
        Boolean rowExists;
        if (mCursor.moveToFirst()) {
            String name = _nameEditText.getText().toString();
            String dosage = _dosageEditText.getText().toString();//converting text to int... hopefully that won't break with decimals
            String qty = _qtyEditText.getText().toString();//converting text to int... hopefully that won't break with decimals
            String whenTaken = _whenTakenEditText.getText().toString();

            _prescription = new Prescription(name, dosage, qty, whenTaken);
            Log.d("", "PRESCRIPTION ENTERED: " + _prescription.getName());

            ContentValues medVals = new ContentValues();
            medVals.put("NAME", name);
            medVals.put("DOSAGE", dosage);
            medVals.put("QTY", qty);
            medVals.put("TIMETAKEN", whenTaken);

            SQLiteOpenHelper pildoraDBHelper2 = new PildoraDatabaseHelper(getLayoutInflater().getContext());
            try{
                SQLiteDatabase dbb = pildoraDBHelper2.getWritableDatabase();
                dbb.insert("MEDS", null, medVals);
                //Log.d("-- Add Med Frag -- ", "Med Name:  " + name);
                pildoraDBHelper.onUpgrade(dbb, dbb.getVersion(), 3);
                dbb.close();
            } catch (SQLiteException e){
                Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "Database Unable to Add Prescription", Toast.LENGTH_LONG);
                toast.show();
            }
            rowExists = true; //there is stuff in the database

        } else { //this is an empty database
            rowExists = false;
            pildoraDBHelper.onUpgrade(db, 0, 3);
            String name = _nameEditText.getText().toString();
            String dosage = _dosageEditText.getText().toString();//converting text to int... hopefully that won't break with decimals
            String qty = _qtyEditText.getText().toString();//converting text to int... hopefully that won't break with decimals
            String whenTaken = _whenTakenEditText.getText().toString();

            _prescription = new Prescription(name, dosage, qty, whenTaken);
            Log.d("", "PRESCRIPTION ENTERED: " + _prescription.getName());

            ContentValues medVals = new ContentValues();
            medVals.put("NAME", name);
            medVals.put("DOSAGE", dosage);
            medVals.put("QTY", qty);
            medVals.put("TIMETAKEN", whenTaken);

            SQLiteOpenHelper pildoraDBHelper2 = new PildoraDatabaseHelper(getLayoutInflater().getContext());
            try{
                SQLiteDatabase dbb = pildoraDBHelper2.getWritableDatabase();
                dbb.insert("MEDS", null, medVals);
                //Log.d("-- Add Med Frag -- ", "Med Name:  " + name);
                pildoraDBHelper.onUpgrade(dbb, dbb.getVersion(), 3);
                dbb.close();
            } catch (SQLiteException e){
                Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "Database Unable to Add Prescription", Toast.LENGTH_LONG);
                toast.show();
            }
        }



    }

    public View getView(){
        return _layout;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

}
