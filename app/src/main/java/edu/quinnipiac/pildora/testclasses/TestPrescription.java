package edu.quinnipiac.pildora.testclasses;

import android.util.Log;

import edu.quinnipiac.pildora.Prescription;

/**
 * TestPrescriptions
 * Description: this unit test just creates two Prescription objects and gives
 * them unique values for their name, dosage, quantity and when to take.
 * This test class is used to make sure the getter methods retrieve the respective data;
 * so getName() should return the String name in the Prescription.
 * The access to the database in AddMedicationFragment uses the getter methods of the prescription the user
 * created to put values into a Content Values object.
 * Author: Katherine Rosell
 * Date: 4/23/2020
 */

public class TestPrescription {

    private static Prescription medB;
    private static Prescription medC;

    public TestPrescription(){
        medB = new Prescription("Medication B", "300", "1", "Taken every morning for 4 weeks");
        medC = new Prescription("Medication C", "5000", "1", "Take 2 every week with food and water for 8 weeks");
    }

    public void runPrescriptionTest(){
        Log.d("---------", "----------   Running Prescription Test   --------");
        Log.d("--", "----------   Make and Print 2 Prescriptions with correct attribute parings   --------");
        printPrescription(medB);
        printPrescription(medC);
    }

    private void printPrescription(Prescription p){
        Log.d("Printing Prescripts...", " Name: " + p.getName() + "  Dosage: " + p.getDosage() + " Qty: " + p.getQuantity() + " When Taken: " + p.getWhenToTake());
    }
}
