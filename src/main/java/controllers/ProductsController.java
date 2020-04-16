package controllers;

import basic.product.Product;
import basic.product.ProductJDBCDAOClient;
import basic.product.ProductsControllerView;
import input_manager.InputManager;
import java.sql.SQLException;
import static basic.product.ProductsControllerView.printProduct;

public class ProductsController{
    private final String[] menuOptions = {"Show All Products", "Select Products by category",
            "Choose product by ID", "Back to Main Menu", "Go to my basket"};
    private InputManager inputManager = new InputManager();
    private ProductJDBCDAOClient productJDBCDAOClient = new ProductJDBCDAOClient();
    private BasketController basketController;
    private ProductsControllerView view = new ProductsControllerView();
    private boolean isRunning = true;

    public ProductsController(BasketController basketController) {
        this.basketController = basketController;
    }

    public void run() throws SQLException {
        isRunning = true;
        while (isRunning) {
            int userChoice = inputManager.askForMenuOption(menuOptions, "Products Menu");
            reactToUserChoice(userChoice);
        }
    }
    public void reactToUserChoice(int choice) throws SQLException {
        if(choice == 1){
            printProduct(productJDBCDAOClient.getAllProducts());
            addToBasket();
        }
        else if(choice == 2){
            int userInput = inputManager.getIntInput("Please provide me with product ID: ");
            printProduct(productJDBCDAOClient.getProductsByCategory(userInput));
        }
        else if(choice == 3){
            int userInput = inputManager.getIntInput("Please provide me with products category ID: ");
            printProduct(productJDBCDAOClient.getProductById(userInput));
        }
        else if(choice == 4){
            isRunning = false;
        }
        else if(choice == 5){
           basketController.run();
        }
    }

    public void addToBasket() throws SQLException {
        int userDecision = inputManager.getIntInput("Please type 1 to add a product to your basket or 2 to go back to Main Menu");
        if(userDecision == 1){
            int prodID = inputManager.getIntInput("Please provide product ID");
            int quantity = inputManager.getIntInput("How many?");
            Product product = productJDBCDAOClient.getProductById(prodID);
            System.out.println(product);
            basketController.addProduct(product, quantity);
        }
    }
}
