package no.kristiania.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDao {
    private List<String> products = new ArrayList<>();

    public void insertProduct(String product) {
        products.add(product);
    }

    public List<String> listAll() {
       return products;
    }
}
