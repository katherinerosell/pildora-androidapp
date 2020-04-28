package edu.quinnipiac.pildora.ui;

import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.quinnipiac.pildora.MainActivity;
import edu.quinnipiac.pildora.R;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AddMedicationFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);
    @Before
    public void setUp() throws Exception {
        myActivityTestRule.launchActivity(MY_ACTIVITY_INTENT);
    }

    @Test
    public void onStart(){
        AddMedicationFragment fragment = new AddMedicationFragment();
        //fragment.getView();//?
        //myActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.med_name, fragment).commit();

        //onView(withId(R.id.nav_host_fragment)).check(matches((isDisplayed())));
        //onView(withId(R.id.med_name)).check(matches((isDisplayed())));
        onView(withId(R.id.med_name)).perform(typeText("Hocus Pocus Motrin"));
        onView(withId(R.id.med_dosage)).perform(typeText("6000"));
        onView(withId(R.id.med_qty)).perform(typeText("1"));
        onView(withId(R.id.med_whenTaken)).perform(typeText("Take 1 every other day for 3 weeks."));
        closeSoftKeyboard();
    }

    @Test
    public void onClick() {
        //onView(withId(R.id.med_name)).perform(typeText("Hocus Pocus Motrin"));


    }

    @Test
    public void savePrescription() {
    }

}