package HW1;

public class Product {
    private int productID;
    public String name;
    public double price;
    public String desc;
    public static int nextProductID = 0;
    
    
    public Product(String name, double price, String desc)
    {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.productID = nextProductID++;
    }
}
