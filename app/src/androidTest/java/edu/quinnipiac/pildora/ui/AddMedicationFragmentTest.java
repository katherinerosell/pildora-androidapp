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