package edu.quinnipiac.pildora.ui.home;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import edu.quinnipiac.pildora.PildoraDatabaseHelper;
import edu.quinnipiac.pildora.R;
import edu.quinnipiac.pildora.testclasses.AddPrescriptionsTest;
import edu.quinnipiac.pildora.ui.AddMedicationFragment;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{
    public static interface Listener{
        void deleteRowID(String id);
    }
    private SQLiteDatabase db;
    private Cursor cursor;
    private View _layout;
    private static TextView addNewHint;
    private static Listener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

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
        _layout = layout;
        return layout;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("HomeFragment :   ", "------------- on start home fragment -------------");//id is relative to the database's row number!
        Cursor newCursor = db.query("MEDS", new String[]{"_id", "NAME", "DOSAGE", "QTY", "TIMETAKEN"}, null, null, null, null, null);
        //if the database is empty, meaning there is nothing in the first row, set the hint text to display the hint
        addNewHint = getView().findViewById(R.id.text_hint);
        if(cursor.moveToFirst()){addNewHint.setText("");}
        else if(!cursor.moveToFirst()){addNewHint.setText("No Prescriptions Added :( Click the menu at the TOP-LEFT to add a new one!");}
        //update list view with new cursor; refreshing the cursor
        ListView listMeds = (ListView) _layout.findViewById(R.id.list_prescriptions);
        CursorAdapter cursorAdapter = (CursorAdapter) listMeds.getAdapter();
        cursorAdapter.changeCursor(newCursor);
        cursor = newCursor;
        //right now, just delete the prescription clicked from the view and db for debugging
        listMeds.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
        Log.d("ListItemClick :   ", "" + id);//id is relative to the database's row number!
        int keyID = (int) id;
        String keyIDString = Integer.toString(keyID);
        _listener.deleteRowID(keyIDString);
        refresh();
    }

    public void refresh() {
        Log.d("HomeFragment ", "*******          refreshing list view...           ********");
        //refresh the cursor to update list view
        Cursor newCursor = db.query("MEDS", new String[]{"_id", "NAME", "DOSAGE", "QTY", "TIMETAKEN"}, null, null, null, null, null);
        ListView listMeds = (ListView) _layout.findViewById(R.id.list_prescriptions);
        SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) listMeds.getAdapter();
        cursorAdapter.changeCursor(newCursor);
        cursor = newCursor;
    }

    /**
     * onAttach
     * Called when the fragment gets attached to the activity.
     * @param context
     */
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this._listener = (Listener)context;
    }


}
