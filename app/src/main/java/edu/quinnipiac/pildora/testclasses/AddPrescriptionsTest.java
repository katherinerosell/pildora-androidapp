package edu.quinnipiac.pildora.testclasses;

import android.app.Activity;
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
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import edu.quinnipiac.pildora.PildoraDatabaseHelper;
import edu.quinnipiac.pildora.Prescription;
import edu.quinnipiac.pildora.R;

public class AddPrescriptionsTest extends Fragment {

    //Prescriptions I will add
    private static Prescription prescriptionD;
    private static Prescription prescriptionE;
    private static Prescription prescriptionG;
    //Database entities needed
    private static Prescription _prescription;
    private PildoraDatabaseHelper pildoraDBHelper;
    private Cursor cursor;

    public AddPrescriptionsTest(){
        prescriptionD = new Prescription("MedicationD", "2.0", "2", "Take 2 every night for 4 months");
        prescriptionE = new Prescription("MedicationE", "2.0", "2", "Take 2 every night for 4 months");
        prescriptionG = new Prescription("MedicationG", "2.0", "2", "Take 2 every night for 4 months");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        final View layout = inflater.inflate(R.layout.blank_fragment, container, false);
        return inflater.inflate(R.layout.blank_fragment, container, false);
    }

    public void runTest() {

        ContentValues testDVal = new ContentValues();
        testDVal.put("NAME", prescriptionD.getName());
        testDVal.put("DOSAGE", prescriptionD.getDosage());
        testDVal.put("QTY", prescriptionD.getQuantity());
        testDVal.put("TIMETAKEN", prescriptionD.getWhenToTake());

        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper();

        try{
            SQLiteDatabase db = pildoraDBHelper.getWritableDatabase();
            db.insert("MEDS", null, testDVal);
            pildoraDBHelper.onUpgrade(db, db.getVersion(), 3);
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "TEST FAILED - UNREACHABLE DATABASE", Toast.LENGTH_LONG);
            toast.show();
        }

    }



}
