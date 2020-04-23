package edu.quinnipiac.pildora.testclasses;

import android.util.Log;

import edu.quinnipiac.pildora.Prescription;

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
