/*
Author: Pierre Giaon & Meghana Krishna
Date: 5/14/2019
Purpose: 

*/
package HW1;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import javafx.collections.*; // ObservableArrayLists
import javafx.geometry.Pos;
import javafx.scene.chart.*; // Charts and Tables
import javafx.scene.control.cell.*; // Tableview

import java.sql.*;
import oracle.jdbc.pool.*;
import java.util.*;
import static javafx.application.Application.launch;


public class GUI extends Application {
    // Class level variables / data fields / "globals"
    // Storing data in memory:
    //ArrayList<Sprocket> sprocketInventory = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<Customer>()
    {
        {
            customerList.add(new Customer("Pierre", "9111", "fdsa"));
        }
    };
    static ArrayList<Product> productList = new ArrayList<Product>(){
        {
            productList.add(new Product("Banana", 2.00, "A yellow fruit"));
        }
    };
    
    // FX Controls
    
    // Main Menu
    
    Label lblWelcome = new Label("Select an Option:");
    Button btnAddData = new Button("Add Data");
    Button btnDisplay = new Button("Display Data");
    
    Button btnMain = new Button("Main Menu");
    
    // Add Data Menu
    Button btnCustomer = new Button("Create Customer");
    Button btnProduct = new Button("Create Product");
    Button btnSupplier = new Button("Create Supplier");
    Button btnStoreArea = new Button("Create Store Area");
    
    
    
    // Customer Info
    Label lblCustomer = new Label("Create Customer:");
    Label lblCName = new Label("Name:");
    Label lblCPhone = new Label("Phone #:");
    Label lblCAddress = new Label("Address:");
    TextField txtCName = new TextField();
    TextField txtCPhone = new TextField();
    TextField txtCAddress = new TextField();
    Button btnCreateCustomer = new Button("Create Customer");
    
    // Product Info
    Label lblProduct = new Label("Create Product:");
    Label lblPName = new Label("Name:");
    Label lblPPrice = new Label("Price Paid for Product:");
    Label lblPDesc = new Label("Description:");
    TextField txtPName = new TextField();
    TextField txtPPrice = new TextField();
    TextField txtPDesc = new TextField();
    Button btnCreateProduct = new Button("Create Product");
    
    // Supplier Info
    Label lblSupplier = new Label("Create Supplier:");
    Label lblSName = new Label("Product Supplier Name:");
    Label lblSAddress = new Label("Supplier Address:");
    Label lblContactName = new Label("Sales Contact Name:");
    Label lblContactPhone = new Label("Sales Contact Phone #:");
    Label lblContactEmail = new Label("Sales Contact Email");
    TextField txtSName = new TextField();
    TextField txtSAddress = new TextField();
    TextField txtContactName = new TextField();
    TextField txtContactPhone = new TextField();
    TextField txtContactEmail = new TextField();
    Button btnCreateSupplier = new Button("Create Supplier");
    
    // Store Area Info
    Label lblStoreArea = new Label("Store Area");
    Label lblStoreName = new Label("Service Center Name:");
    Label lblStoreDept = new Label("Service Department:");
    Label lblDeptDesc = new Label("Department Description:");
    TextField txtStoreName = new TextField();
    TextField txtStoreDept = new TextField();
    TextField txtDeptDesc = new TextField();
    Button btnCreateStoreArea = new Button("Create Store Area");
    
    
    TextArea txtOutput = new TextArea();
    GridPane primaryPane = new GridPane();
    TextArea customerDisplay = new TextArea();
    ScrollPane customerScrollPane = new ScrollPane(customerDisplay);
    
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryPane.add(lblWelcome, 0, 0);
        primaryPane.add(btnAddData, 0, 1);
        primaryPane.add(btnDisplay, 0, 3);
        
        
        Scene primaryScene = new Scene(primaryPane, 1050,550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("HV AC / Residential Air");
        primaryStage.show();
        primaryPane.setHgap(10);
        primaryPane.setVgap(5);
        
        
        btnAddData.setOnAction(e -> {
        primaryPane.getChildren().clear();
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryPane.add(btnCustomer, 0, 0);
        primaryPane.add(btnProduct, 0, 1);
        primaryPane.add(btnSupplier, 0, 2);
        primaryPane.add(btnStoreArea,0, 3);
        
        });
        
        btnCustomer.setOnAction(e -> {
        primaryPane.getChildren().clear();
        
        // Create Customer
        primaryPane.add(lblCustomer, 0, 0);
        primaryPane.add(lblCName, 0, 1);
        primaryPane.add(txtCName, 1, 1);
        primaryPane.add(lblCPhone, 0, 2);
        primaryPane.add(txtCPhone, 1, 2);
        primaryPane.add(lblCAddress, 0, 3);
        primaryPane.add(txtCAddress, 1, 3);
        primaryPane.add(btnCreateCustomer, 1, 4);
        primaryPane.add(btnMain, 1, 6);
        
        });
        

        btnProduct.setOnAction(e ->{
        primaryPane.getChildren().clear();
        
        // Create Product
        primaryPane.add(lblProduct, 0, 0);
        primaryPane.add(lblPName, 0, 1);
        primaryPane.add(txtPName, 1, 1);
        primaryPane.add(lblPPrice, 0, 2);
        primaryPane.add(txtPPrice, 1, 2);
        primaryPane.add(lblPDesc, 0, 3);
        primaryPane.add(txtPDesc, 1, 3);
        primaryPane.add(btnCreateProduct, 1, 4);
        primaryPane.add(btnMain, 1, 6);
        });
        
        
        btnSupplier.setOnAction(e ->{
        primaryPane.getChildren().clear();
        // Create Supplier
        primaryPane.add(lblSupplier, 0, 0);
        primaryPane.add(lblSName, 0, 1);
        primaryPane.add(txtSName, 1, 1);
        primaryPane.add(lblSAddress, 0, 2);
        primaryPane.add(txtSAddress, 1, 2);
        primaryPane.add(lblContactName, 0, 3);
        primaryPane.add(txtContactName, 1, 3);
        primaryPane.add(lblContactPhone, 0, 4);
        primaryPane.add(txtContactPhone, 1, 4);
        primaryPane.add(lblContactEmail, 0, 5);
        primaryPane.add(txtContactEmail, 1, 5);
        primaryPane.add(btnCreateSupplier, 1, 6);
        primaryPane.add(btnMain, 1, 8);
        });
        
        
        btnStoreArea.setOnAction(e ->{
        primaryPane.getChildren().clear();
        // Create Store
        primaryPane.add(lblStoreArea, 0, 0);
        primaryPane.add(lblStoreName, 0, 1);
        primaryPane.add(txtStoreName, 1, 1);
        primaryPane.add(lblStoreDept, 0, 2);
        primaryPane.add(txtStoreDept, 1, 2);
        primaryPane.add(lblDeptDesc, 0, 3);
        primaryPane.add(txtDeptDesc, 1, 3);
        primaryPane.add(btnCreateStoreArea, 1, 4);
        primaryPane.add(btnMain, 1, 6);
        });
        
        //primaryPane.add(btnMain, 5, 15);
        
        
        
        
        
        
        btnCreateCustomer.setOnAction(e -> {
            customerList.add(new Customer(txtCName.getText(), 
                    txtCPhone.getText(), 
                    txtCAddress.getText()));
            
            
            txtCName.clear();
            txtCPhone.clear();
            txtCAddress.clear();
        });
        
        
        btnCreateProduct.setOnAction(e -> {
            productList.add(new Product(txtPName.getText(),
                    Double.valueOf(txtPPrice.getText()),
                    txtPDesc.getText()));
            
            
            txtPName.clear();
            txtPPrice.clear();
            txtPDesc.clear();
        });
        
        btnMain.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.setAlignment(Pos.CENTER);
            primaryPane.add(lblWelcome, 0, 0);
            primaryPane.add(btnAddData, 0, 1);
            primaryPane.add(btnDisplay, 0, 3);
            
        });
        
        btnDisplay.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.setAlignment(Pos.CENTER);
            primaryPane.add(customerScrollPane, 0, 0, 5, 5);
            primaryPane.add(btnMain, 0, 10);
            displayCustomers(customerDisplay);
            
        });
        
        
    }

    public void sendDBCommand(String sqlQuery)
    {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; // Change to YOUR Oracle username
        String userPASS = "javapass"; // Change to YOUR Oracle password
        OracleDataSource ds;
        
        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);
        
        // Lets try to connect
        try
        {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID,userPASS);
            // When we get results
            //  -TYPE_SCROLL_SENSITIVE means if the database data changes we
            //   will see our resultset update in real time.
            //  -CONCUR_READ_ONLY means that we cannot accidentally change the
            //   data in our database by using the .update____() methods of
            //   the ResultSet class - TableView controls are impacted by
            //   this setting as well.
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // We send the query to the DB. A ResultSet object is instantiated
            //  and we are returned a reference to it, that we point to from
            // dbResults.
            // Because we declared dbResults at the datafield level
            // we can see the results from anywhere in our Class.
            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
            // The results are stored in a ResultSet structure object
            // pointed to by the reference variable dbResults
            // Because we declared this variable globally above, we can use
            // the results in any method in the class.
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void displayCustomers(TextArea customerDisplay)
    {
        System.out.println(customerList.size());
        String current = "";
        current += String.format("%s\t\t%s\t\t\t%s\t\t\t%s\n", "Customer ID:", "Name", "Phone:", "Address:");
        for(int i = 0; i < customerList.size(); i++)
        {
            current += customerList.get(i);
            current += "\n";
        }
        customerDisplay.setText(current);
    }
}
