package controllers;

import basic.product.Product;
import basic.product.ProductDAO;
import basic.product.ProductsControllerView;
import input_manager.InputManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static basic.product.ProductsControllerView.printProduct;

public class ProductsController{
    private final List<String> menuOptions = new ArrayList<>(Arrays.asList("Show All Products", "Select Products by category",
            "Choose product by ID", "Back to Main Menu", "Go to my basket"));
    private final InputManager inputManager = new InputManager();
    private final ProductDAO productDAO;
    private final BasketController basketController;
    private final ProductsControllerView view = new ProductsControllerView();
    private boolean isRunning = true;

    public ProductsController(BasketController basketController, ProductDAO productDAO) {
        this.basketController = basketController;
        this.productDAO = productDAO;
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
            printProduct(productDAO.getAllProducts());
            addToBasket();
        }
        else if(choice == 2){
            int userInput = inputManager.getIntInput("Please provide me with product ID: ");
            printProduct(productDAO.getProductsByCategory(userInput));
        }
        else if(choice == 3){
            int userInput = inputManager.getIntInput("Please provide me with products category ID: ");
            printProduct(productDAO.getProductById(userInput));
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
            Product product = productDAO.getProductById(prodID);
            System.out.println(product);
            basketController.addProduct(product, quantity);
        }
    }
}
