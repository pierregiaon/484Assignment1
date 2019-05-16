/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HW1;

/**
 *
 * @author pierregiaon
 */
public class StoreArea {
    public int storeAreaID; 
    public static int nextStoreArea = 0; 
    public String serviceCenterName; 
    public String serviceDept; 
    public String deptDesc; 
    
    public StoreArea(String serviceCenterName, String serviceDept, String deptDesc) {
        this.deptDesc = deptDesc; 
        this.serviceCenterName = serviceCenterName; 
        this.serviceDept = serviceDept; 
        this.storeAreaID = nextStoreArea++; 
    }
    
}
