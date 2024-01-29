package repository;

import base.repository.BaseRepository;
import models.Products;

import java.sql.SQLException;

public interface ProductRepository extends BaseRepository<Integer, Products> {

    Products [] showAllProducts() throws SQLException;
}
