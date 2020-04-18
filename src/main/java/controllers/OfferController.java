package controllers;

import exceptions.MyExceptions;
import basic.product.Product;
import basic.product.ProductJDBCDAOAdmin;
import view.ProductsControllerView;
import input_manager.InputManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static view.ProductsControllerView.printProduct;

public class OfferController implements RunnableController {
    private final List<String> menuOptions = new ArrayList<>(Arrays.asList("Show all products", "Find product by ID", "Find product by category ID",
            "Insert new product", "Delete product", "Modify product", "Go to Main Menu"));
    private final InputManager inputManager = new InputManager();
    private final ProductJDBCDAOAdmin productDAOAdmin;
    private final ProductsControllerView printProduct = new ProductsControllerView();
    private boolean isRunning = true;

    public OfferController(ProductJDBCDAOAdmin productJDBCDAOAdmin) {
        this.productDAOAdmin = productJDBCDAOAdmin;
    }

    @Override
    public void run() throws SQLException {
        isRunning = true;
        while (isRunning) {
            int userDecision = presentMenuOptions();
            reactToUserChoice(userDecision);
        }
    }

    public int presentMenuOptions() {
        return inputManager.askForMenuOption(menuOptions, "Admin Menu");
    }

    public void reactToUserChoice(int userDecision) throws SQLException {
        if (userDecision == 1) {
            printProduct(productDAOAdmin.getAllProducts());
        } else if (userDecision == 2) {
            int id = askForProductId();
            printProduct(productDAOAdmin.getProductById(id));
        } else if (userDecision == 3) {
            int id = askForProductId();
            try {
                printProduct(productDAOAdmin.getProductsByCategory(id));
            } catch (MyExceptions e) {
                e.MyException();
            }
        } else if (userDecision == 4) {
            Product product = askForProductDetails();
            productDAOAdmin.insertNewProduct(product);
        } else if (userDecision == 5) {
            int id = askForProductId();
            productDAOAdmin.deleteProductById(id);
        } else if (userDecision == 6) {
            int id = askForProductId();
            Product product = askForProductDetails();
            productDAOAdmin.modifyProduct(product, id);
        } else if (userDecision == 7) {
            isRunning = false;
        }
    }

    public Product askForProductDetails() {
        String name = inputManager.getStringInput("Product name: ");
        int categoryId = inputManager.getIntInput("Category ID: ");
        String brand = inputManager.getStringInput("Brand: ");
        float price = inputManager.getFloatInput("Price: ");
        int minAge = inputManager.getIntInput("Age Category: ");

        return new Product(name, categoryId, brand, price, minAge);
    }

    public int askForProductId() {
        return inputManager.getIntInput("Please provide product ID: ");
    }
}
