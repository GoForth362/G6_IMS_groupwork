package ZooManagement;


public class Animal {
    public String name;
    public String species;
    public int age;
    public Animal(String name,String species,int age){
        this.name = name;
        this.species = species;
        this.age = age;
    }

    //Accessor and mutator methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //Methods to print animal details
    public void display(){
        System.out.println("Animal Name: "+this.getName()
                +" ,Species: "+this.getSpecies()
                +" ,Age: "+this.getAge());
    }

    @Override
    public String toString() {
        System.out.println("Animal.toString() called: " + name);
        return "Name: " + name + ", Species: " + species + ", Age: " + age;
    }
}
