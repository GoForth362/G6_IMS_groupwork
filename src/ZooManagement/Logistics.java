package ZooManagement;

public class Logistics {
    public Item vehicle;
    public Item fuel;
    public String[] caretakers;

    public Logistics(Item fuel, Item vehicle, String[] caretakers) {
        this.fuel = fuel;
        this.vehicle = vehicle;
        this.caretakers = caretakers;
    }

    //Accessor and mutator methods for all attributes
    public Item getVehicle() {
        return vehicle;
    }

    public void setVehicle(Item vehicle) {
        this.vehicle = vehicle;
    }

    public Item getFuel() {
        return fuel;
    }

    public void setFuel(Item fuel) {
        this.fuel = fuel;
    }

    public String[] getCaretakers() {
        return caretakers;
    }

    public void setCaretakers(String[] caretakers) {
        this.caretakers = caretakers;
    }

    //A Display method to show vehicle detail
    public void displayVehicle(){
        this.getVehicle().display();
    }

    //A Display method to show fuel detail
    public  void displayFuel(){
        this.getFuel().display();
    }
}
