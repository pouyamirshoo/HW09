package service;

import base.service.BaseService;
import models.Products;

import java.sql.SQLException;

public interface ProductService extends BaseService<Integer, Products> {
    Products[] showAllProducts() throws SQLException;
    int editProductPrice(String name, float newPrice) throws SQLException;
    int editProductNumber(String name, int newNumber) throws SQLException;
    int editProductSubBranch(int id, String name) throws SQLException;
    Products[] showOneSubBranchProducts(int id) throws SQLException;
}
