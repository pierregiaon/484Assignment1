package HW1;

public class Supplier {
    private int supplierID;
    public String supplierName;
    public String supplierAddress;
    public String contactName;
    public String contactPhone;
    public String contactEmail;
    public static int nextSupplierID = 0;
    
    public Supplier(String supplierName, String supplierAddress, String contactName,
            String contactPhone, String contactEmail)
    {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.supplierID = nextSupplierID++;
    }
}
