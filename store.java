package HW1;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
public class Store {
   


    public int storeID; 
    public static int nextStoreID = 0; 
    public String storeName; 
    public static ObservableList obsStore = FXCollections.observableArrayList();
    public ArrayList<Technician> addStoreTech = new ArrayList<>(); 
    
    public void addStoreTech(Technician newTechnician) {
        addStoreTech.add(newTechnician); 
    }
    
    public Store(String storeName) {
        this.storeName = storeName;
        this.storeID = nextStoreID++;
        obsStore.add(this.storeName);
    }
    public String toString() {
        return this.storeName; 
}
}
