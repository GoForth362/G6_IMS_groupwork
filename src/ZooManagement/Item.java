package ZooManagement;

public class Item {
    public String name;
    public double price;
    public String code;

    public Item(String name, String code) {
        this.name = name;
        this.code = code;
    }

    //Accessor and mutator methods for all attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void display(){
        //A Display method to print item details
        System.out.println("Name: "+this.getName()+
                " ,Price: $"+this.getPrice()+
                " ,Code: "+this.getCode());
    }
}
