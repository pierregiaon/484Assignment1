package HW1;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;



public class Customer {
    private int customerID;
    public String name;
    public String phone;
    public String address;
    public static int nextCustomerID = 0;
    public static ObservableList obsCust = FXCollections.observableArrayList();
    public ArrayList<Product> addProdOrder = new ArrayList<>(); 
    public ArrayList<Service> addServiceOrder = new ArrayList<>(); 
    public ArrayList<Store> addStoreOrdered = new ArrayList<>();
    
    
    public Customer(String name, String phone, String address)
    {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.customerID = nextCustomerID++;
        obsCust.add(this.customerID + ": " + this.name);
    }
    public void addProdOrder(Product newProduct) {
        addProdOrder.add(newProduct); 
    }
    public void addServiceOrder(Service newService) {
        addServiceOrder.add(newService); 
    }
    public void addStoreOrdered(Store newStore) {
        addStoreOrdered.add(newStore); 
    }
    
    public String toString()
    {
        String result = "";
        result += String.format("%3d\t\t\t\t%s\t\t\t%s\t\t\t%s", this.customerID, this.name, this.phone, this.address);
        
        return result;
    }
   
    
}

}
