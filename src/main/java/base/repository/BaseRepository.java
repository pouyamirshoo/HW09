package base.repository;

import base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseRepository<ID extends Serializable , TYPE extends BaseEntity<ID>> {
    void save (TYPE type) throws SQLException;
    TYPE findById(ID id) throws SQLException;
    void update(TYPE type) throws SQLException;
    void delete(ID id) throws SQLException;

}
