package base.repository;

import base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseRepository<ID extends Serializable , TYPE extends BaseEntity<ID>> {
    int save (TYPE type) throws SQLException;
    TYPE findById(ID id) throws SQLException;
    int editName(int id , String newName) throws SQLException;
    int delete(ID id) throws SQLException;

}
