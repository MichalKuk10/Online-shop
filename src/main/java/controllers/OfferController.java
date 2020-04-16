package controllers;

import basic.product.Product;
import basic.product.ProductJDBCDAOAdmin;
import basic.product.ProductsControllerView;
import input_manager.InputManager;
import java.sql.SQLException;
import static basic.product.ProductsControllerView.printProduct;

public class OfferController {
    private InputManager inputManager = new InputManager();
    private ProductJDBCDAOAdmin productAdmin = new ProductJDBCDAOAdmin();
    private ProductsControllerView printProduct = new ProductsControllerView();


    private final String[] menuOptions = {"Show all products", "Find product by ID", "Find product by category ID",
    "Insert new product", "Delete product", "Modify product", "Go to Main Menu"};

    public OfferController(ProductJDBCDAOAdmin productJDBCDAOAdmin){
    }

    public void run() throws SQLException {
        boolean running = true;
        while(true){
            int userDecision = presentMenuOptions();
            reactToUserChoice(userDecision);
            }
        }

    public int presentMenuOptions(){

        int userDecision = inputManager.askForMenuOption(menuOptions, "Admin Menu");
        return userDecision;
    }

    public void reactToUserChoice(int userDecision) throws SQLException {

        if(userDecision == 1) {
            printProduct(productAdmin.getAllProducts());
        }
        else if(userDecision == 2) {
            int id = askForProductId();
            printProduct(productAdmin.getProductById(id));
        }
        else if(userDecision == 3) {
            int id = askForProductId();
            printProduct(productAdmin.getProductsByCategory(id));
        }
        else if(userDecision == 4) {
            Product product = askForProductDetails();
            productAdmin.insertNewProduct(product);
        }
        else if(userDecision == 5) {
            int id = askForProductId();
            productAdmin.deleteProductById(id);
        }
        else if (userDecision == 6){
            int id = askForProductId();
            Product product = askForProductDetails();
            productAdmin.modifyProduct(product, id);
        }
//        else(userDecision == 7) {
//            //MainControllerAdmin.run(); to be confirmed
//        }
    }

    public Product askForProductDetails(){
        String name = inputManager.getStringInput("Product name: ");
        int categoryId = inputManager.getIntInput("Category ID: ");
        String brand = inputManager.getStringInput("Brand: ");
        Double price = inputManager.getDoubleInput("Price: ");
        int minAge = inputManager.getIntInput("Age Category: ");
        Product product = new Product(name, categoryId, brand, price, minAge);
        return product;
    }
    public int  askForProductId(){
        int id = inputManager.getIntInput("Please provide product ID: ");
        return id;
    }
}
