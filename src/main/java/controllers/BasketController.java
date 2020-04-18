package controllers;

import basic.basket.Basket;
import basic.product.Product;
import view.BasketControllerView;
import input_manager.InputManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasketController implements RunnableController {

    private final Basket basket;
    private final List<String> menuOptions = new ArrayList<>(Arrays.asList("Change product quantity", "Delete product from basket", "Clear basket", "Proceed to payment", "Go back"));
    private final BasketControllerView view = new BasketControllerView();
    private final InputManager inputManger = new InputManager();
    private final PurchaseController purchaseController;
    private boolean isRunning = true;

    public BasketController(Basket basket, PurchaseController purchaseController){
        this.basket = basket;
        this.purchaseController = purchaseController;
        purchaseController.setBasket(basket);
    }

    @Override
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
        view.showBasket(basket);
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