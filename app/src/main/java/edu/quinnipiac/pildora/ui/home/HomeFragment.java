package edu.quinnipiac.pildora.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import edu.quinnipiac.pildora.PildoraDatabaseHelper;
import edu.quinnipiac.pildora.R;

public class HomeFragment extends Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        Button addMed = layout.findViewById(R.id.button_addMed);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addMedication();//?
            }
        });

        //this section connects the list view to the SimpleCursorAdapter, loading all the attributes of the prescription into a custom list view, list_4items.xml
        ListView listMeds = layout.findViewById(R.id.list_prescriptions);
        SQLiteOpenHelper pildoraDatabaseHelper = new PildoraDatabaseHelper(layout.getContext());
        try{
            db = pildoraDatabaseHelper.getReadableDatabase();
            cursor = db.query("MEDS", new String[]{"_id", "NAME", "DOSAGE", "QTY", "TIMETAKEN"}, null, null, null, null, null);
            SimpleCursorAdapter myListAdapter = new SimpleCursorAdapter(layout.getContext(),
                    R.layout.list_4items,
                    cursor,
                    new String[]{"NAME", "DOSAGE", "QTY", "TIMETAKEN"},
                    new int[]{R.id.name_item, R.id.dosage_item, R.id.qty_item, R.id.whentotake_item,},
                    0);
            listMeds.setAdapter(myListAdapter);
        } catch(SQLiteException err){
            Toast toast = Toast.makeText(layout.getContext(), "Database Unreachable", Toast.LENGTH_LONG);
            toast.show();
        }
        return layout;
    }

    //for testing purposes only, mainly for completely removing the database
    private void deleteDB() {
        db.execSQL("DROP TABLE IF EXISTS MEDS");//fail safe to delete the table
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
