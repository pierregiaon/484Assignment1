/*
Author: Pierre Giaon & Meghana Krishna
Date: 5/14/2019
Purpose: 

*/
package HW1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.DecimalFormat;
import javafx.geometry.Pos;
import java.sql.*;
import java.util.*;
import oracle.jdbc.pool.*;


public class GUI extends Application {
    
    static ArrayList<Customer> customerList = new ArrayList<Customer>();
    static ArrayList<Product> productList = new ArrayList<Product>();
    static ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    static ArrayList<StoreArea> storeAreaList = new ArrayList<StoreArea>();
    static ArrayList<Technician> techList = new ArrayList<Technician>();
    static ArrayList<Store> storeList = new ArrayList<Store>();
    static ArrayList<Service> serviceList = new ArrayList<Service>();
    // FX Controls
    
    // ComboBoxes
    ObservableList obsCust = FXCollections.observableArrayList();
    ComboBox cmboCust = new ComboBox(Customer.obsCust);
    ObservableList obsTech = FXCollections.observableArrayList();
    ComboBox cmboTech = new ComboBox(Technician.obsTech);
    ObservableList obsStore = FXCollections.observableArrayList();
    ComboBox cmboStore = new ComboBox(Store.obsStore);
    ObservableList obsProd = FXCollections.observableArrayList();
    ComboBox cmboProd = new ComboBox(Product.obsProd);
    ObservableList obsService = FXCollections.observableArrayList();
    ComboBox cmboService = new ComboBox(Service.obsServ);
    
    // Main Menu
    
    Label lblWelcome = new Label("Select an Option:");
    Button btnAddData = new Button("Add Data");
    Button btnDisplay = new Button("Display Data");
    Button btnActions = new Button("Order and Services");
    Button btnMain = new Button("Main Menu");
    
    // Add Data Menu
    Button btnCustomer = new Button("Create Customer");
    Button btnProduct = new Button("Create Product");
    Button btnSupplier = new Button("Create Supplier");
    Button btnStoreArea = new Button("Create Store Area");
    Button btnTech = new Button("Create Technician");
    Button btnStore = new Button("Create Store");
    Button btnMService = new Button("Create Service");
    
    // Actions Menu
    Button btnOrder = new Button("Customer Order");
    Button btnService = new Button("Technician Services");
    
        
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
    Label lblStoreArea = new Label("Store Area:");
    Label lblStoreName = new Label("Service Center Name:");
    Label lblStoreDept = new Label("Service Department:");
    Label lblDeptDesc = new Label("Department Description:");
    TextField txtStoreName = new TextField();
    TextField txtStoreDept = new TextField();
    TextField txtDeptDesc = new TextField();
    Button btnCreateStoreArea = new Button("Create Store Area");
    
    // Technician Info
    Label lblTech = new Label("Technician:");
    Label lblTechName = new Label("Name:");
    TextField txtTechName = new TextField();
    Button btnCreateTech = new Button("Create Technician");
    
    // Store Info
    Label lblStore = new Label("Store:");
    Label lblStName = new Label("Store Name:");
    TextField txtStName = new TextField();
    Button btnCreateStore = new Button("Create Store");
    
    // Service Info
    Label lblSService = new Label("Service:");
    Label lblServName = new Label("Name: ");
    TextField txtServName = new TextField();
    Label lblServiceLvl = new Label("Service Level:");
    ObservableList<String> serviceLvl = FXCollections.observableArrayList(
            "Bronze",
            "Silver",
            "Gold");
    final ComboBox cmboServiceLvl = new ComboBox(serviceLvl);
    Label lblTown = new Label("Town:");
    TextField txtTown = new TextField();
    Button btnCreateService = new Button("Create Service");
    
    
    // Order Menu
    Label lblOrder = new Label("Customer Order Menu");
    Label lblOCust = new Label("Customer: ");
    Label lblOProd = new Label("Product: ");
    Label lblOStore = new Label("Store: ");
    Button btnCreateO = new Button("Create Order");
    
    // Technician Service Menu
    Label lblTechService = new Label("Technician Service Menu");
    Label lblTServ = new Label("Technician:");
    Label lblService = new Label("Service:");
    Label lblServStore = new Label("Store:");
    Button btnCreateS = new Button("Create Service");
    
    TextArea txtOutput = new TextArea();
    GridPane primaryPane = new GridPane();
    TextArea customerDisplay = new TextArea();
    //ScrollPane customerScrollPane = new ScrollPane(customerDisplay);
    
    // Display Window
    Label lblDisplayCustomers = new Label("Select Customer:");
    Label lblDisplayTech = new Label("Select Technician:");
    Label lblDisplayStore = new Label("Select Store:");
    Button btnDisplayCustomers = new Button("Display Customer");
    Button btnDisplayTech = new Button("Display Technician");
    Button btnDisplayStore = new Button("Display Store");
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
        primaryPane.add(btnActions, 0, 2);
        primaryPane.add(btnDisplay, 0, 3);
        
        
        Scene primaryScene = new Scene(primaryPane, 900,550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("HV AC / Residential Air");
        primaryStage.show();
        primaryPane.setHgap(10);
        primaryPane.setVgap(10);
        
        
        btnAddData.setOnAction(e -> {
        primaryPane.getChildren().clear();
        primaryPane.setAlignment(Pos.CENTER);
        primaryPane.add(lblWelcome, 0, 0);
        primaryPane.add(btnCustomer, 0, 1);
        primaryPane.add(btnProduct, 0, 2);
        primaryPane.add(btnSupplier, 0, 3);
        primaryPane.add(btnStoreArea,0, 4);
        primaryPane.add(btnTech, 0, 5);
        primaryPane.add(btnStore, 0, 6);
        primaryPane.add(btnMService, 0, 7);
        primaryPane.add(btnMain, 0, 8);
        
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
        // Create StoreArea
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
        
        
        //Technician
        btnTech.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblTech, 0, 0);
            primaryPane.add(lblTechName, 0, 1);
            primaryPane.add(txtTechName, 1, 1);
            primaryPane.add(btnCreateTech, 1, 2);
            primaryPane.add(btnMain, 1, 3);
            
        });
        
        // Store
        btnStore.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblStore, 0, 0);
            primaryPane.add(lblStName, 0, 1);
            primaryPane.add(txtStName, 1, 1);
            primaryPane.add(btnCreateStore, 1, 2);
            primaryPane.add(btnMain, 1, 3);
            
        });
        
        // Service
        btnMService.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblSService, 0, 0);
            primaryPane.add(lblServName, 0, 1);
            primaryPane.add(txtServName, 1, 1);
            primaryPane.add(lblServiceLvl, 0, 2);
            primaryPane.add(cmboServiceLvl, 1, 2);
            primaryPane.add(lblTown, 0, 3);
            primaryPane.add(txtTown, 1, 3);
            primaryPane.add(btnCreateService, 1, 4);
            primaryPane.add(btnMain, 1, 5);
        });
        
        btnCreateSupplier.setOnAction(e -> {
            supplierList.add(new Supplier(txtSName.getText(),
                    txtSAddress.getText(),
                    txtContactName.getText(),
                    txtContactPhone.getText(),
                    txtContactEmail.getText()));
            
            
            txtSName.clear();
            txtSAddress.clear();
            txtContactName.clear();
            txtContactPhone.clear();
            txtContactEmail.clear();
        });
        
        
        
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
        
        btnCreateStoreArea.setOnAction(e -> {
            storeAreaList.add(new StoreArea(txtStoreName.getText(), 
                    txtStoreDept.getText(), txtDeptDesc.getText()));
        
            txtStoreName.clear();
            txtStoreDept.clear();
            txtDeptDesc.clear();
        });
        
        btnCreateTech.setOnAction(e -> {
            techList.add(new Technician(txtTechName.getText()));
            
            txtTechName.clear();
        });
        
        btnCreateStore.setOnAction(e -> {
           storeList.add(new Store(txtStName.getText()));
           
           txtStName.clear();
        });
        
        btnCreateService.setOnAction(e -> {
            serviceList.add(new Service(txtServName.getText(), (String)cmboServiceLvl.getValue(), 
            txtTown.getText()));
            
            txtServName.clear();
            cmboServiceLvl.getSelectionModel().clearSelection();
            txtTown.clear();
        });
        
        
        btnMain.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.setAlignment(Pos.CENTER);
            primaryPane.add(lblWelcome, 0, 0);
            primaryPane.add(btnAddData, 0, 1);
            primaryPane.add(btnActions, 0, 2);
            primaryPane.add(btnDisplay, 0, 3);
            
        });
        
        btnDisplay.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.setAlignment(Pos.CENTER);
            primaryPane.add(lblDisplayCustomers, 0, 0);
            primaryPane.add(cmboCust, 1, 0);
            primaryPane.add(btnDisplayCustomers, 1, 1);
            primaryPane.add(lblDisplayStore, 0, 2);
            primaryPane.add(cmboStore, 1, 2);
            primaryPane.add(btnDisplayStore, 1, 3);
            primaryPane.add(lblDisplayTech, 0, 4);
            primaryPane.add(cmboTech, 1, 4);
            primaryPane.add(btnDisplayTech, 1, 5);
            primaryPane.add(txtOutput, 2, 0, 10, 10);
            primaryPane.add(btnMain, 1, 11);
            
        });
        
        btnDisplayCustomers.setOnAction(e -> {
            txtOutput.clear();
            
            displayCustomers(txtOutput);
        });
        
        btnActions.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblWelcome, 0, 0);
            primaryPane.add(btnOrder, 0, 1);
            primaryPane.add(btnService, 0, 2);
            primaryPane.add(btnMain, 0, 3);
        });
        
        btnOrder.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblOrder, 0, 0);
            primaryPane.add(lblOCust, 0, 1);
            primaryPane.add(cmboCust, 1, 1);
            primaryPane.add(lblOProd, 0, 2);
            primaryPane.add(cmboProd, 1, 2);
            primaryPane.add(lblOStore, 0, 3);
            primaryPane.add(cmboStore, 1, 3);
            primaryPane.add(lblService, 0, 4);
            primaryPane.add(cmboService, 1, 4);
            primaryPane.add(btnCreateO, 1, 5);
            primaryPane.add(btnMain, 1, 6);
        });
        
        btnService.setOnAction(e -> {
            primaryPane.getChildren().clear();
            primaryPane.add(lblTechService, 0, 0);
            primaryPane.add(lblTServ, 0, 1);
            primaryPane.add(cmboTech, 1, 1);
            primaryPane.add(lblService, 0, 2);
            primaryPane.add(cmboService, 1, 2);
            primaryPane.add(lblServStore, 0, 3);
            primaryPane.add(cmboStore, 1, 3);
            primaryPane.add(btnCreateS, 1, 4);
            primaryPane.add(btnMain, 1, 5);
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
        current += String.format("%s\t\t%s\t\t\t%s\t\t\t%s\n", "Customer ID:", "Name:", "Phone:", "Address:");
        for(int i = 0; i < customerList.size(); i++)
        {
            current += customerList.get(i).toString();
            current += "\n";
        }
        customerDisplay.setText(current);
    }
    
    
}
