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

public class MainActivity extends AppCompatActivity implements HomeFragment.Listener {

    private AppBarConfiguration mAppBarConfiguration;
    private static NavController _navController;
    private static String _rowID;
    private static HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        //Use HomeFragment's refresh() method to refresh its list view when items get deleted
        homeFragment = new HomeFragment();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //navController.navigate(R.id.nav_settings);
            return true;
        }
        if(id == R.id.action_help){
            _navController.navigate(R.id.nav_help);//use nav controller to get to/display help fragment
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void deleteRowID(String id) {
        _rowID = id;
        new DeleteRowAsync().execute(_rowID);
        Toast toast = Toast.makeText(MainActivity.this, "Prescription Deleted!", Toast.LENGTH_LONG);
        toast.show();
    }

    private class DeleteRowAsync extends AsyncTask<String, Void, Boolean>{

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

        protected void onPostExecute(Boolean success) {
            if(!success){
                Toast toast = Toast.makeText(MainActivity.this, "Database Unavailable", Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }



}
