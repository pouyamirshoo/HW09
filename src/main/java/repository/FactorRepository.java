package repository;

import base.repository.BaseRepository;
import models.Factor;

import java.sql.SQLException;

public interface FactorRepository extends BaseRepository<Integer, Factor> {
    int [] numOfFactor(int id) throws SQLException;
    int saveFactorInnerTable(int idF,int idP) throws SQLException;
    int [] productsOfOneFactor(int id) throws SQLException;
}
