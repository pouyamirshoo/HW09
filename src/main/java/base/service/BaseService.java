package base.service;

import base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseService<ID extends Serializable, Type extends BaseEntity<ID>> {

    int save(Type type) throws SQLException;

    Type findById(ID id) throws SQLException;

    int editName(String oldName, String newName) throws SQLException;

    int delete(String name) throws SQLException;
    Type findByName(String name) throws SQLException;
}
