package edu.quinnipiac.pildora;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    private void insertPrescription(SQLiteDatabase db, String name, double dosage, int quantityToTake, String whenToTake){
        ContentValues prescriptionVals = new ContentValues();
        prescriptionVals.put("NAME", name);
        prescriptionVals.put("DOSAGE", dosage);
        prescriptionVals.put("QTY", quantityToTake);
        prescriptionVals.put("TIMETAKEN", whenToTake);
        db.insert("MEDS", null, prescriptionVals);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){ //first time loading in db
            db.execSQL("CREATE TABLE MEDS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, " + "DOSAGE DOUBLE, " + "QTY INTEGER, " + "TIMETAKEN TEXT);");
            insertPrescription(db, "Medicine A", 0.50, 2, "Taken every morning.");
            insertPrescription(db, "Medicine B", 0.20, 1, "Taken every 4 hours for a 3 days.");
        }
        if(oldVersion < 2){
            db.execSQL("ALTER TABLE MEDS ADD COLUMN STAR NUMERIC");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }
}
