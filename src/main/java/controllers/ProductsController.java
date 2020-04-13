package controllers;

import basic.product.Product;
import basic.product.ProductDAOClient;

import java.util.ArrayList;


public class ProductsController {

    ArrayList<Product> productsList;
    ProductDAOClient productDAOClient = new ProductDAOClient();
    BasketController basketController = new BasketController();


    ProductsController(ProductDAOClient productDAOClient, BasketController basketController){

    }

    public void reactToUserChoice(int decision){

    }

    public Product buyProduct(int number){
        return null;
    }

    public Float askForProductAmount(){
        return null;
    }
    public void moveToBasket(Product product){

    }


}
