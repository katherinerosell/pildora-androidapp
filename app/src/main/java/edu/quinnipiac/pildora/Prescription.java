package edu.quinnipiac.pildora;

public class Prescription {

    private String _name;
    private String _dosage;
    private String _qty;//the quantity of pills/meds to be ingested
    private String _whenToTake;//when and how much of the medication to take

    public Prescription(){ }

    public Prescription(String name, String dosage, String qty, String whenToTake){
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

    public String getDosage(){
        return _dosage;
    }

    public String getQuantity(){
        return _qty;
    }

    public String getWhenToTake(){
        return _whenToTake;
    }

}
