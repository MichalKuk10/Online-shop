package basic.product;

import java.math.BigDecimal;

public class Product {

    private Integer productId;
    private String name;
    private int categoryId;
    private String brand;
    private BigDecimal price;
    private int minAge;

    public Product(String name, int categoryId, String brand, BigDecimal price, int minAge) {
        this.name = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.price = price;
        this.minAge = minAge;
    }
    public Product(int productId, String name, int categoryId, String brand, BigDecimal price, int minAge) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.price = price;
        this.minAge = minAge;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", minAge=" + minAge +
                '}';
    }
}
