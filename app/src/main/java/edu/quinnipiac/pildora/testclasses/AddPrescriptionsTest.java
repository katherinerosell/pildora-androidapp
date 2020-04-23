package edu.quinnipiac.pildora.testclasses;

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
    private static Prescription prescriptionE;
    private static Prescription prescriptionG;
    //Database entities needed
    //private static Prescription _prescription;
    private static SQLiteDatabase db;
    //private PildoraDatabaseHelper pildoraDBHelper;
    //private Cursor cursor;
    private static TestPrescription testPrescription;

    public AddPrescriptionsTest(SQLiteDatabase theDB){
        prescriptionD = new Prescription("MedicationD", "2.0", "2", "Take 2 every night for 4 months");
        prescriptionE = new Prescription("MedicationE", "400", "1", "Take 1 every night with food for 4 weeks");
        prescriptionG = new Prescription("MedicationG", "1000", "1", "Take 1 every day for a week");
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

        ContentValues testEVal = new ContentValues();
        testEVal.put("NAME", prescriptionE.getName());
        testEVal.put("DOSAGE", prescriptionE.getDosage());
        testEVal.put("QTY", prescriptionE.getQuantity());
        testEVal.put("TIMETAKEN", prescriptionE.getWhenToTake());

        ContentValues testGVal = new ContentValues();
        testGVal.put("NAME", prescriptionG.getName());
        testGVal.put("DOSAGE", prescriptionG.getDosage());
        testGVal.put("QTY", prescriptionG.getQuantity());
        testGVal.put("TIMETAKEN", prescriptionG.getWhenToTake());

        SQLiteOpenHelper pildoraDBHelper = new PildoraDatabaseHelper(this.getContext());

        try{
            //db = pildoraDBHelper.getWritableDatabase();
            //insert 3 new content values of the prescription objects
            db.insert("MEDS", null, testDVal);
            db.insert("MEDS", null, testEVal);
            db.insert("MEDS", null, testGVal);
            pildoraDBHelper.onUpgrade(db, db.getVersion(), 3);
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this.getLayoutInflater().getContext(), "TEST FAILED - UNREACHABLE DATABASE", Toast.LENGTH_LONG);
            toast.show();
        }

        Log.d("---------", "----------   Print Added Prescriptions + Attributes   --------");
        Log.d(" Prescription D:  ", "Name: " + prescriptionD.getName()+ " Dosage: " + prescriptionD.getDosage()+ " Quantity: " + prescriptionD.getQuantity()+ " When Taken:" + prescriptionD.getWhenToTake());
        Log.d(" Prescription E:  ", "Name: " + prescriptionE.getName()+ " Dosage: " + prescriptionE.getDosage()+ " Quantity: " + prescriptionE.getQuantity()+ " When Taken:" + prescriptionE.getWhenToTake());
        Log.d(" Prescription G:  ", "Name: " + prescriptionG.getName()+ " Dosage: " + prescriptionG.getDosage()+ " Quantity: " + prescriptionG.getQuantity()+ " When Taken:" + prescriptionG.getWhenToTake());
        testPrescription.runPrescriptionTest();
    }


}
