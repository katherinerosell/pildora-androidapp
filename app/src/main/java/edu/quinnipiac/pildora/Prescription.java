package edu.quinnipiac.pildora;

public class Prescription {

    private String _name;
    private int _dosage;
    private int _qty;//the quantity of pills/meds to be ingested
    private String _whenToTake;//when and how much of the medication to take

    public Prescription(){ }

    public Prescription(String name, int dosage, int qty, String whenToTake){
        _name = name;
        _dosage = dosage;
        _qty = qty;
        _whenToTake = whenToTake;
    }

    /**
     * List of GETTER methods
     * to retrieve prescription information easily
     **/

    public String getName(){
        return _name;
    }

    public int getDosage(){
        return _dosage;
    }

    public int getQuantity(){
        return _qty;
    }

    public String getWhenToTake(){
        return _whenToTake;
    }

}
