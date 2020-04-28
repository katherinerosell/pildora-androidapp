package edu.quinnipiac.pildora;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PrescriptionTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        Prescription med = new Prescription("Universal PainKiller3", "2000", "1", "Take 1 every other day for a month");
    }

    @Test
    public void getName() {
    }

    @Test
    public void getDosage() {
    }

    @Test
    public void getQuantity() {
    }

    @Test
    public void getWhenToTake() {
    }
}