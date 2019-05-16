package hw1;


public class Technician {
   
    public int techNum; 
    public static int techIDCount = 0; 
    public String technicianName; 
  
    public Technician(String technicianName) {
        this.technicianName = technicianName; 
        this.techNum = techIDCount++; 
    }
    public static int getTechIDCount() {
        return techIDCount++; 
    }
}
