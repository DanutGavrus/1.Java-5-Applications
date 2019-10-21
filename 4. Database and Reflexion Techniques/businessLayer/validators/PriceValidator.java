package businessLayer.validators;

import model.product;

public class PriceValidator implements Validator<product> {
    private static final int MIN_PRICE = 1;

    public void validate(product t) {

        if (t.getProduct_price() < MIN_PRICE) {
            throw new IllegalArgumentException("The Product Price can`t be 0 or lower!");
        }

    }
}
