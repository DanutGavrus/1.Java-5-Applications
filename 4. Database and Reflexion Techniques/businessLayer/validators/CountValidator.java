package businessLayer.validators;

import model.product;

public class CountValidator implements Validator<product> {
    private static final int MIN_COUNT = 5;
    private static final int MAX_COUNT = 99;

    public void validate(product t) {
        if (t.getProduct_count() < MIN_COUNT || t.getProduct_count() > MAX_COUNT) {
            throw new IllegalArgumentException("The Count must be between 5 and 99!");
        }
    }
}
