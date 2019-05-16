package hw1;


public class Service {
  public int serviceID; 
  public static int serviceIDCount = 0; 
  public String serviceName; 
  public String serviceLevel; 
  public String town; 
  public Technician techNum; 
  //public static Technician techNumCount = 0; 
  
  public Service(String serviceName, String serviceLevel, String town, Technician techNum) {
      this.serviceName = serviceName; 
      this.town = town; 
      this.serviceID = serviceIDCount ++; 
      this.serviceLevel = serviceLevel; 
      //this.techNum = techNum.getTechIDCount();
      //need to get techID to correctly incremate with tech count from the tech class 
      //so it can work as like an employee id for technicians 
  }
}
