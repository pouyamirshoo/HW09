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

    @Override
    public int editProductPrice(int id, float newPrice) throws SQLException {
        return repository.editProductPrice(id,newPrice);
    }

    @Override
    public int editProductNumber(int id, int newNumber) throws SQLException {
        return repository.editProductNumber(id,newNumber);
    }

    @Override
    public int editProductSubBranch(int id, String name) throws SQLException {
        return repository.editProductSubBranch(id,name);
    }
}
