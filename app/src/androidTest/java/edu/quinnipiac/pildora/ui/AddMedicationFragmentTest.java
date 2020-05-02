package edu.quinnipiac.pildora.ui;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.quinnipiac.pildora.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;

/**
 * AddMeciationFragmentTest
 * NOT FINISHED
 * Unfortunately, this test class does not run correctly. Because the fragment is not made directly
 * in the MainActivity, the test is not able to access the EditText objects from the view. This has to
 * do with the Navigation Components that actually navigate to and create the fragments. I was unfortunately
 * unable to find a solution that accesses a fragment from a navigation component but have learned that
 * this is an obstacle I will avoid in the future.
 * @Author: Katherine Rosell
 * @Date: 4/28/2020
 */

@RunWith(AndroidJUnit4.class)
public class AddMedicationFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void setUp() throws Exception {
        //ActivityScenario myActivityTestRule = ActivityScenario.launch(MainActivity.class);
    }

    /**
     * Perform the save action
     * put everyhting in this method
     */
    @Test
    public void onClick() {
        onView(withHint("Add Prescription Name")).perform(typeText("Hocus Pocus Pillz"));
        //onView(withHint(R.id.med_name)).perform(typeText("Hocus Pocus Pillz"));;
        //onView(withHint("Add Prescription Name")).perform(typeTextIntoFocusedView("Hocus Pocus Pillz"));
        //onView(withId(R.id.med_name)).perform(typeText("Hocus Pocus Pillz"));
    }


}