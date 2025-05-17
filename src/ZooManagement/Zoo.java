/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ZooManagement;

/**
 * Zoo Class - Manages a collection of animals
 * @author ashongtical
 */

public class Zoo {
    public String name;
    public Animal[] animals = new Animal[10];
    public int counter = 0;

    public Zoo(String name){
        this.name = name;
    }

    //Adds an animal to the zoo
    public void addAnimal(Animal animal){
        animal = new Animal(animal.getName(),animal.getSpecies(),animal.getAge());
            if (counter < 10){
                animals[counter] = animal;
                counter++;
            }else {
                System.out.println("Zoo is full");
            }
            //return counter;
    }

    //Deletes an animal from the zoo
    public void deleteAnimal(String animalToRemove){
        int index = this.findAnimal(animalToRemove);
        if (index != -1){
            while (index < animals.length-1){
            animals[index] = animals[index+1];
            index++;
            }
            animals[index] = null;
            counter--;
        }else {
            System.out.println("Don't have this animal");
        }
    }

    //Transfers an animal from one zoo to another, recording logistics
    public void moveAnimal(String animalName, Zoo to, Logistics logistics){
        System.out.println("******** ANIMAL TRANSFER INVOICE ********");
        System.out.println("From: "+this.name);
        System.out.println("To: "+to.getName());
        System.out.println("Animal Details:");
        int index =  this.findAnimal(animalName);
        this.animals[index].display();

        System.out.println("----- Logistics Details -----");
        System.out.println("Vehicle Information:");
        logistics.displayVehicle();
        System.out.println("Fuel Information:");
        logistics.displayFuel();
        System.out.print("Caretakers: ");
        for (int j = 0; j < logistics.getCaretakers().length; j++){
            System.out.print(logistics.getCaretakers()[j]+", ");
        }
        System.out.println();//change length
        double sum = logistics.getVehicle().getPrice()+logistics.getFuel().getPrice();
        System.out.println("Total Logistics Cost: $"+sum);

        System.out.println("Animal "+this.animals[index].getName()+" add to "+to.getName());
        System.out.println("Animal "+this.animals[index].getName()+" remove from "+this.getName());
        System.out.println("Animal "+this.animals[index].getName()+" successfully moved from "
                +this.getName()+" to "+to.getName());
        to.addAnimal(this.animals[index]);
        this.deleteAnimal(this.animals[index].getName());
    }

    //Searches for an animal by name
    public int findAnimal(String animalName){
        int key = counter-1;
        while (key != -1){
            if (animals[key].getName().equals(animalName)){
                break;
            }
            key--;
        }
        return key;//The location of this animal
    }

    //Get an animal's index
    public Animal getAnimal(int index){
        return animals[index];
    }

    //Methods to print animals in the zoo details
    public void displayAnimals(){
        System.out.println("--- Animals in "+this.name+"("+counter+"/"+"10"+")"+" ---");
        for (int i = 0 ; i < counter; i++){
            System.out.println((i+1)+"."+" Animal Name:"+animals[i].getName()+","
                    +" Species:"+animals[i].getSpecies()+","
                    +" Age:"+animals[i].getAge());
        }
        System.out.println("----------------------------------------");
    }

    //Accessor and mutator methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }
}
