package basic.discount_code;

public interface DiscountCodeDAO {

    boolean insertDiscountCode(String discountCode);

    boolean updateDiscountCode(int discountCodeId, String updatedDiscountCode);

    boolean checkIfDiscountCodeInDatabase(String discountCode);

}
