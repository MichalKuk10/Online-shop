package controllers;

import basic.basket.Basket;
import basic.product.Product;
import view.BasketControllerView;
import input_manager.InputManager;

import java.sql.SQLException;

public class BasketController {

    private Basket basket;
    private String[] menuOptions = {"Change product quantity", "Delete product from basket", "Clear basket", "Proceed to payment", "Go back"};
    private BasketControllerView basketcontrollerview = new BasketControllerView();
    private InputManager inputManger = new InputManager();
    private PurchaseController purchaseController;
    private boolean isRunning = true;

    public BasketController(PurchaseController purchaseController){
        this.basket = new Basket();
        this.purchaseController = purchaseController;
    }

    public void run() throws SQLException {
        isRunning = true;
        while(isRunning){
            calculateBasketValue();
            showBasket();
            int userChoice = inputManger.askForMenuOption(menuOptions, "Basket Menu");
            reactToUserChoice(userChoice);
        }
    }

    public void reactToUserChoice(Integer userChoice) throws SQLException {
        switch (userChoice) {
            case 1:
                changeProductQuantity();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                clearBasket();
                break;
            case 4:
                purchaseController.run();
                break;
            case 5:
                isRunning = false;
                break;
        }
    }

    public void showBasket(){
        basketcontrollerview.showBasket(basket);
    }
    public void addProduct(Product product, Integer quantity) {
        basket.addProduct(product, quantity);
    }

    public void deleteProduct(){
        int productIDtoDelete = inputManger.getIntInput("Enter the number of the product to delete: ");
        basket.deleteProduct(productIDtoDelete);
    }

    public void changeProductQuantity(){
        int productId = inputManger.getIntInput("Enter the number of the product: ");
        int newQuantity = inputManger.getIntInput("Enter new quantity: ");
        basket.changeProductQuantity(productId, newQuantity);
    }

    public void clearBasket(){
        basket.clear();
    }

    public void calculateBasketValue(){
        basket.calculateBasket();
    }
}