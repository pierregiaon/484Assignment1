package HW1;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class Service {
    public int serviceID; 
    public static int serviceIDCount = 0; 
    public String serviceName; 
    public String serviceLevel; 
    public String town; 
    public static ObservableList obsServ = FXCollections.observableArrayList();
    
  public Service(String serviceName, String serviceLevel, String town) {
      this.serviceName = serviceName; 
      this.town = town; 
      this.serviceID = serviceIDCount ++; 
      this.serviceLevel = serviceLevel; 
      obsServ.add(this.serviceID + ": " + this.serviceName);
      
  } 
  
  public String toString()
  {
    String result = "";
    result += String.format("%3d\t\t\t\t%s\t\t\t%s\t\t\t%s", this.serviceID, this.serviceName, this.serviceLevel, this.town);
    return result;
  }
}
