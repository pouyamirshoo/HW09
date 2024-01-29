package service;

import base.service.BaseServiceImpel;
import models.Products;
import repository.ProductRepository;

import java.sql.SQLException;

public class ProductServiceImpel extends BaseServiceImpel<Integer, Products, ProductRepository> implements ProductService{
    public ProductServiceImpel(ProductRepository repository) {
        super(repository);
    }

    @Override
    public Products[] showAllProducts() throws SQLException {
        return repository.showAllProducts();
    }
}
