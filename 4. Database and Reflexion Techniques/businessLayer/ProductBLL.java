package businessLayer;

import java.util.ArrayList;
import java.util.List;

import businessLayer.validators.CountValidator;
import businessLayer.validators.PriceValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ProductDAO;
import model.product;

public class ProductBLL {
    private List<Validator<product>> validators;

    public ProductBLL() {
        validators = new ArrayList<Validator<product>>();
        validators.add(new CountValidator());
        validators.add(new PriceValidator());
    }

    public int insertProduct(product product) {
        for (Validator<product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insert(product);
    }
}
