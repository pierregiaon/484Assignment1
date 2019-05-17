package HW1;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class Product {
    private int productID;
    public String name;
    public double price;
    public String desc;
    public static int nextProductID = 0;
    public static ObservableList obsProd = FXCollections.observableArrayList();
    
    
    public Product(String name, double price, String desc)
    {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.productID = nextProductID++;
        obsProd.add(this.productID + ": " + this.name);
    }
    
    public String toString()
    {
        String result = "";
        result += String.format("%3d\t\t\t\t%s\t\t\t$%3.2f\t\t\t%s", this.productID,
                this.name, this.price, this.desc);
        
        return result;
    }
}
