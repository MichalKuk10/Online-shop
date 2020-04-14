package controllers;

import basic.basket.Basket;
import basic.product.Product;
import basic.product.ProductDAOClient;
import basic.product.ProductsControllerView;
import input_manager.InputManager;
import java.sql.SQLException;
import static basic.product.ProductsControllerView.printProduct;

public class ProductsController{
    private InputManager inputManager = new InputManager();
    private ProductDAOClient productDAOClient = new ProductDAOClient();
    private Basket basket = new Basket();
    private BasketController basketController = new BasketController(basket);
    private String[] menuOptions = {"Show All Products", "Select Products by category",
            "Choose product by ID", "Back to Main Menu", "Go to my basket"};
    private ProductsControllerView view = new ProductsControllerView();


    public void run() throws SQLException {
        boolean running = true;
        while (running) {
            int userChoice = inputManager.askForMenuOption(menuOptions, "Products Menu");
            reactToUserChoice(userChoice);
        }
    }
    public void reactToUserChoice(int choice) throws SQLException {
        if(choice == 1){
            printProduct(productDAOClient.getAllProducts());
            addToBasket();
        }
        else if(choice == 2){
            int userInput = inputManager.getIntInput("Please provide me with product ID: ");
            printProduct(productDAOClient.getProductsByCategory(userInput));
        }
        else if(choice == 3){
            int userInput = inputManager.getIntInput("Please provide me with products category ID: ");
            printProduct(productDAOClient.getProductById(userInput));
        }
        else if(choice == 4){
            view.clearScreen();
            view.printMenu(menuOptions, "Menu Options: ");//its a place for main menu
        }
        else if(choice == 5){
           basketController.run();
        }
    }
    public void backtoMenu() throws SQLException {
        run();
    }
    public void addToBasket() throws SQLException {
        int userDecistion = inputManager.getIntInput("Please type 1 to add a product to your basket or 2 to go back to Main Menu");
        if(userDecistion == 1){
            int prodID = inputManager.getIntInput("Please provide product ID");
            int quantity = inputManager.getIntInput("How many?");
            Product product = productDAOClient.getProductById(prodID);
            System.out.println(product);
           basket.addProduct(product, quantity);
        }
        else if(userDecistion == 2){
            backtoMenu();
        }
    }
}
