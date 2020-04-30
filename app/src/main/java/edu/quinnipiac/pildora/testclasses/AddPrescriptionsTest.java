package edu.quinnipiac.pildora.testclasses;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.quinnipiac.pildora.PildoraDatabaseHelper;
import edu.quinnipiac.pildora.Prescription;
import edu.quinnipiac.pildora.R;

/**
 * AddPrescriptionsTest
 * Description: this unit test tests the HomeFragment and it's connection to the database.
 * The HomeFragment should update its list view onStart (when home screen is loaded back in) which indicates
 * that its database has been updated!
 * If the list view displays the below prescriptions, the HomeFragment accesses and reads the database properly,
 * and refreshes the cursor correctly.
 * Author: Katherine Rosell
 * Date: 4/23/2020
 */

public class AddPrescriptionsTest extends Fragment {

    //Prescriptions I will add
    private static Prescription prescriptionD;
    private static SQLiteDatabase db;
    private static TestPrescription testPrescription;

    public AddPrescriptionsTest(SQLiteDatabase theDB){
        prescriptionD = new Prescription("MedicationD", "2.0", "2", "Take 2 every night for 4 months");
        db = theDB;
        testPrescription = new TestPrescription();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        final View layout = inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
        return inflater.inflate(R.layout.fragment_edit_add_meds, container, false);
    }

    public void runTest() {

        ContentValues testDVal = new ContentValues();
        testDVal.put("NAME", prescriptionD.getName());
        testDVal.put("DOSAGE", prescriptionD.getDosage());
        testDVal.put("QTY", prescriptionD.getQuantity());
        testDVal.put("TIMETAKEN", prescriptionD.getWhenToTake());

        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper(this.getContext());

        try{
            db.insert("MEDS", null, testDVal);
            pildoraDBHelper.onUpgrade(db, db.getVersion(), 3);
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "TEST FAILED - UNREACHABLE DATABASE", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
