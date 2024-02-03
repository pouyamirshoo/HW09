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
    public int editProductPrice(String name, float newPrice) throws SQLException {
        return repository.editProductPrice(name,newPrice);
    }

    @Override
    public int editProductNumber(String name, int newNumber) throws SQLException {
        return repository.editProductNumber(name,newNumber);
    }

    @Override
    public int editProductSubBranch(int id, String name) throws SQLException {
        return repository.editProductSubBranch(id,name);
    }

    @Override
    public Products[] showOneSubBranchProducts(int id) throws SQLException {
        return repository.showOneSubBranchProducts(id);
    }

    @Override
    public int deleteFromInnerTable(int id) throws SQLException {
        return repository.deleteFromInnerTable(id);
    }
}
