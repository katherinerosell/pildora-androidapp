package edu.quinnipiac.pildora;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PrescriptionTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    private Prescription _med;

    @Before
    public void setUp() throws Exception {
        Prescription med = new Prescription("Universal PainKiller3", "2000", "1", "Take 1 every other day for a month");
        _med = med;
    }

    @Test
    public void getName() {
        assertEquals("Universal PainKiller3", _med.getName());
    }

    @Test
    public void getDosage() {
        assertEquals("2000", _med.getDosage());
    }

    @Test
    public void getQuantity() {
        assertEquals("1", _med.getQuantity());
    }

    @Test
    public void getWhenToTake() {
        assertEquals("Take 1 every other day for a month", _med.getWhenToTake());
    }
}