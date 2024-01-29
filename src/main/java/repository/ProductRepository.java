package repository;

import base.repository.BaseRepository;
import models.Products;

import java.sql.SQLException;

public interface ProductRepository extends BaseRepository<Integer, Products> {

    Products [] showAllProducts() throws SQLException;
    int editProductPrice(String name, float newPrice) throws SQLException;
    int editProductNumber(String name, int newNumber) throws SQLException;
    int editProductSubBranch(int id, String name) throws SQLException;
}
