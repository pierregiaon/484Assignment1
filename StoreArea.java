package hw1;


public class StoreArea {
    public int storeAreaID; 
    public static int storeAreaCount = 0; 
    public String serviceCenterName; 
    public String serviceDep; 
    public String depDesc; 
    
    public StoreArea(String serviceCenterName, String serviceDep, String depDesc) {
        this.depDesc = depDesc; 
        this.serviceCenterName = serviceCenterName; 
        this.serviceDep = serviceDep; 
        this.storeAreaID = 0 + storeAreaCount; 
    }
    
}
