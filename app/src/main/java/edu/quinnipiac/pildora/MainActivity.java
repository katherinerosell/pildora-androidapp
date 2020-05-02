package edu.quinnipiac.pildora;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import edu.quinnipiac.pildora.ui.home.HomeFragment;

/**
 * Main Activity
 *  The MainActivity is responsible for setting up the Navigation Components to connect to the
 *  other screens. The MainActivity also is responsible for running the AsyncTask that deletes
 *  a prescription from the list view and database.
 * @Author: Katherine Rosell
 * @Date: 4/12/2020
 */
public class MainActivity extends AppCompatActivity implements HomeFragment.Listener {

    private AppBarConfiguration mAppBarConfiguration;
    private static NavController _navController;
    private static String _rowID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //removal of floating action (little message icon)
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_editmeds)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        _navController = navController;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        int id = item.getItemId();
        if(id == R.id.action_help){
            _navController.navigate(R.id.nav_help);//use nav controller to get to/display help fragment
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * deleteRowID
     * Using the listener interface from the HomeFragment, the MainActivity uses
     * the id from the list item and deletes the prescription from that row in the database.
     * @param id
     */

    @Override
    public void deleteRowID(String id) {
        _rowID = id;
        DeleteRowAsync myFetchRequest = (DeleteRowAsync) new DeleteRowAsync().execute(_rowID);
    }

    private class DeleteRowAsync extends AsyncTask<String, Void, Boolean> {
        SQLiteOpenHelper pildoraDatabaseHelper = new PildoraDatabaseHelper(MainActivity.this);
        @Override
        protected Boolean doInBackground(String... strings) {
            Log.d("DeleteRowAsync", "   ---------      deleting row...   ------- ");
            try{
                SQLiteDatabase db = pildoraDatabaseHelper.getWritableDatabase();
                db.delete("MEDS", "_id = ?", strings);
                db.close();
                return true;
            }
            catch(SQLiteException err){
                return false;
            }
        }

        /**
         * Once the doInBackground() completes, the navigation controller jumps to the HomeFragment again.
         * This triggers the onStart method that refreshes the list view.
         * @param success
         */

        protected void onPostExecute(Boolean success) {
            //nothing really needed here except for successful toast message!
            if(success){
                Toast toast = Toast.makeText(MainActivity.this, "Prescription Deleted!", Toast.LENGTH_LONG);
                toast.show();
                _navController.navigate(R.id.nav_home);//here is where the view 'reloads'
            } if(!success){
                Toast toast = Toast.makeText(MainActivity.this, "Database Unavailable", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


}
