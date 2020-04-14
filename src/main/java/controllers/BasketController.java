package controllers;

import basic.basket.Basket;
import basic.product.Product;
import view.BasketControllerView;
import input_manager.InputManager;

public class BasketController {

    private Basket basket;
    private String[] menuOptions = {"Change product quantity", "Delete product from basket", "Clear basket", "Go to product site", "Proceed to payment", "Back to products"};
    private BasketControllerView basketcontrollerview = new BasketControllerView();
    InputManager inputManger = new InputManager();

    public BasketController(Basket basket){
        this.basket = basket;
    }

    public int run(){

        int selector = 0;
        while (selector == 0) {
            calculateBasketValue();
            showBasket();
            int userChoice = inputManger.askForMenuOption(menuOptions, "Basket Menu");
            selector = reactToUserChoice(userChoice);
        }
        if (selector == 1){
            return 1;
        }
        else if (selector == 2){
            return 2;
        }
        else return 3;
    }

    public void presentMenuOptions(){
        basketcontrollerview.printMenu(menuOptions, "Basket Menu");
    }

    public Integer reactToUserChoice(Integer userChoice){

        switch (userChoice) {
            case 1:
                changeProductQuantity();
                return 0;
            case 2:
                deleteProduct();
                return 0;
            case 3:
                clearBasket();
                return 0;
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
        }
        return 0;
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