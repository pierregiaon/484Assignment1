/*
Author: Jeremy D. Ezell - JMU
Date: 5/14/2019
Purpose: JavaFX form for creating and interacting with an
  inventory of Sprockets.

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
    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<Product> productList = new ArrayList<>();
    
    // FX Controls
    
    // Customer Info
    Label lblCustomer = new Label("Create Customer:");
    Label lblCName = new Label("Name:");
    Label lblCPhone = new Label("Phone #:");
    Label lblCAddress = new Label("Address:");
    TextField txtCName = new TextField();
    TextField txtCPhone = new TextField();
    TextField txtCAddress = new TextField();
    Button btnCustomer = new Button("Create Customer");
    
    // Product Info
    Label lblProduct = new Label("Create Product:");
    Label lblPName = new Label("Name:");
    Label lblPPrice = new Label("Price Paid for Product:");
    Label lblPDesc = new Label("Description:");
    TextField txtPName = new TextField();
    TextField txtPPrice = new TextField();
    TextField txtPDesc = new TextField();
    Button btnProduct = new Button("Create Product");
    
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
    Button btnSupplier = new Button("Create Supplier");
    
    // Store Info
    Label lblStore = new Label("Store Area");
    Label lblStoreName = new Label("Service Center Name:");
    Label lblStoreDept = new Label("Service Department:");
    Label lblDeptDesc = new Label("Department Description:");
    TextField txtStoreName = new TextField();
    TextField txtStoreDept = new TextField();
    TextField txtDeptDesc = new TextField();
    Button btnStore = new Button("Create Store Area");
    
    GridPane primaryPane = new GridPane();
    
    
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        primaryPane.setAlignment(Pos.CENTER_LEFT);
        
        
        // Create Customer
        primaryPane.add(lblCustomer, 0, 0);
        primaryPane.add(lblCName, 0, 1);
        primaryPane.add(txtCName, 1, 1);
        primaryPane.add(lblCPhone, 0, 2);
        primaryPane.add(txtCPhone, 1, 2);
        primaryPane.add(lblCAddress, 0, 3);
        primaryPane.add(txtCAddress, 1, 3);
        primaryPane.add(btnCustomer, 1, 4);
        
        // Create Product
        primaryPane.add(lblProduct, 4, 0);
        primaryPane.add(lblPName, 4, 1);
        primaryPane.add(txtPName, 5, 1);
        primaryPane.add(lblPPrice, 4, 2);
        primaryPane.add(txtPPrice, 5, 2);
        primaryPane.add(lblPDesc, 4, 3);
        primaryPane.add(txtPDesc, 5, 3);
        primaryPane.add(btnProduct, 5, 4);
        
        // Create Supplier
        primaryPane.add(lblSupplier, 7, 0);
        primaryPane.add(lblSName, 7, 1);
        primaryPane.add(txtSName, 8, 1);
        primaryPane.add(lblSAddress, 7, 2);
        primaryPane.add(txtSAddress, 8, 2);
        primaryPane.add(lblContactName, 7, 3);
        primaryPane.add(txtContactName, 8, 3);
        primaryPane.add(lblContactPhone, 7, 4);
        primaryPane.add(txtContactPhone, 8, 4);
        primaryPane.add(lblContactEmail, 7, 5);
        primaryPane.add(txtContactEmail, 8, 5);
        primaryPane.add(btnSupplier, 8, 6);
        
        // Create Store
        primaryPane.add(lblStore, 0, 7);
        primaryPane.add(lblStoreName, 0, 8);
        primaryPane.add(txtStoreName, 1, 8);
        primaryPane.add(lblStoreDept, 0, 9);
        primaryPane.add(txtStoreDept, 1, 9);
        primaryPane.add(lblDeptDesc, 0, 10);
        primaryPane.add(txtDeptDesc, 1, 10);
        primaryPane.add(btnStore, 1, 11);
        
        
        
        
        
        Scene primaryScene = new Scene(primaryPane,950,550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("HV AC / Residential Air");
        primaryStage.show();
        primaryPane.setHgap(10);
        primaryPane.setVgap(5);
        
        
        btnCustomer.setOnAction(e -> {
            customerList.add(new Customer(txtCName.getText(), 
                    txtCPhone.getText(), 
                    txtCAddress.getText()));
            
            
            txtCName.clear();
            txtCPhone.clear();
            txtCAddress.clear();
        });
        
        
        btnProduct.setOnAction(e -> {
            productList.add(new Product(txtPName.getText(),
                    Double.valueOf(txtPPrice.getText()),
                    txtPDesc.getText()));
            
            
            txtPName.clear();
            txtPPrice.clear();
            txtPDesc.clear();
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
    
}
