package edu.quinnipiac.pildora;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * PildoraDatabaseHelper
 * Using SQLiteOpenHelper, this class uses OOP features to store prescriptions and their attributes into a
 * database.
 */

public class PildoraDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Pildora";
    private static final int DB_VERSION = 2;

    public PildoraDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public void insertPrescription(SQLiteDatabase db, String name, String dosage, String quantityToTake, String whenToTake){
        ContentValues prescriptionVals = new ContentValues();
        prescriptionVals.put("NAME", name);
        prescriptionVals.put("DOSAGE", dosage);
        prescriptionVals.put("QTY", quantityToTake);
        prescriptionVals.put("TIMETAKEN", whenToTake);
        //db.insert("MEDS", null, prescriptionVals);

        long result = db.insert("MEDS", null, prescriptionVals);

        if(result == -1){
            Log.d(" INSERT RESULT ", "FALSE, not added in :  " + result);
        } else {
            Log.d(" INSERT RESULT ", "TRUE, added in : " + result);
        }
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){//on first loading in
            db.execSQL("CREATE TABLE MEDS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, " + "DOSAGE TEXT, " + "QTY TEXT, " + "TIMETAKEN TEXT);");
            insertPrescription(db, "Medicine A", "0.50", "2", "Taken every morning.");
            //insertPrescription(db, "Medicine B", "0.20", "1", "Taken every 4 hours for a 3 days.");
            //insertPrescription(db, "Medicine C", "0.30", "2", "Take x2 a day for 3 weeks.");
        }
        else if(oldVersion == 2){
            //insertPrescription(db, "Medicine D", 0.40, 1, "Take 1 a day for 3 weeks.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }


}
