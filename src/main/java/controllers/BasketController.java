package controllers;

import basic.basket.Basket;
import basic.product.Product;
import view.BasketControllerView;
import input_manager.InputManager;
import java.util.ArrayList;
import java.util.List;

public class BasketController {

    private Basket basket;
    private String[] menuOptions = {"1. Change product quantity", "2. Delete product", "3. Clear basket", "4. Go to product site", "5. Proceed to payment", "3. Back"};
    private BasketControllerView basketcontrollerview = new BasketControllerView;
    InputManager inputManger = new InputManager();

    BasketController(Basket basket){
        this.basket = basket;
    }

    public void run(){

        boolean running = true;
        while (running) {
            showBasket();
            int userChoice = inputManger.askForMenuOption(menuOptions, "Basket Menu")
            running = reactToUserChoice(userChoice);
        }
    }

    public void presentMenuOptions(){
        basketcontrollerview.printMenu(menuOptions, "Basket Menu");
    }

    public boolean reactToUserChoice(Integer userChoice){

        switch (userChoice){
            case 1:
                changeProductQuantity();
                return true;
            case 2:
                deleteProduct();
                return true;
            case 3:
                clearBasket();
                return true;
            case 4:
                return true;
        }

    }

    public void showBasket(){
        basketcontrollerview.showBasket(basket);
    }
    public void addProduct(Product product, Integer quantity){
        basket.addProduct(product, quantity);
    }

    public void deleteProduct(){
        String productName = inputManger.getStringInput("Enter the name of the product to delete: ");
        basket.deleteProduct(productName);
    }

    public void changeProductQuantity(Product product, Integer quantity){
        String productName = inputManger.getStringInput("Enter the name of the product: ");
        int newQuantity = inputManger.getIntInput("Enter new quantity: ");
        basket.changeProductQuantity(productName, newQuantity);
    }

    public void clearBasket(){
        basket.clear();
    }

    public void calculateBasketValue(){
        basket.calculateBasket();
    }
}