package service;

import base.service.BaseService;
import models.Products;

import java.sql.SQLException;

public interface ProductService extends BaseService<Integer, Products> {
    Products[] showAllProducts() throws SQLException;
}
