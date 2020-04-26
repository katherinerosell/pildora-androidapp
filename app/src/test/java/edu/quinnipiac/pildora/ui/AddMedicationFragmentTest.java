package edu.quinnipiac.pildora.ui;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.quinnipiac.pildora.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
@RunWith(AndroidJUnit4.class)
public class AddMedicationFragmentTest {

    //I'm testing a fragment, not an Activity
    //@Rule
    //public ActivityTestRule<AddMedicationFragment> addMedicationFragmentActivityTestRule = new ActivityTestRule<>(AddMedicationFragment.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreateView() {
    }

    @Test
    public void onStart() {
    }

    @Test
    public void doTest() {
    }

    /**
     * this button is responsible for saving the prescription
     * to the database
     */
    @Test
    public void onClick() {
        //simulate edit text objects filled in
        onView(withId(R.id.med_name)).perform(typeText("Medicine D"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.med_dosage)).perform(typeText("300"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.med_qty)).perform(typeText("2"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.med_whenTaken)).perform(typeText("Twice a day for 5 weeks"),
                ViewActions.closeSoftKeyboard());
        //perform the save prescriptions part by running the save button
        onView(withId(R.id.button_savePrescription)).perform(click());

    }

    @Test
    public void savePrescription() {
    }


}