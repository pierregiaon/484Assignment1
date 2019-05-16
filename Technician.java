package HW1;

import javafx.collections.FXCollections;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class Technician {
    public int techNum; 
    public static int nextTechID = 0; 
    public String techName; 
    public static ObservableList obsTech = FXCollections.observableArrayList();
    
  
    public Technician(String techName) {
        this.techName = techName; 
        this.techNum = nextTechID++; 
        obsTech.add(this.techNum + ": " + this.techName);
    }
    public static int getTechIDCount() {
        return nextTechID++; 
    }
}
