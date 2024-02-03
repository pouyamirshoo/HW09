package service;

import base.service.BaseService;
import models.Factor;

import java.sql.SQLException;

public interface FactorService  extends BaseService<Integer, Factor> {
    int [] numOfFactor(int id) throws SQLException;
    int saveFactorInnerTable(int idF,int idP) throws SQLException;
    int [] productsOfOneFactor(int id) throws SQLException;
}
