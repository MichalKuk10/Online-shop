package controllers;

import basic.basket.Basket;
import basic.order.OrderDAO;
import basic.user.User;


public class PurchaseController {
    private final User user;
    private final Basket basket;
    private final OrderDAO orderDAO;

    public PurchaseController(User user, Basket basket, OrderDAO orderDAO) {
        this.user = user;
        this.basket = basket;
        this.orderDAO = orderDAO;
    }

    public void run() {

    }
}
