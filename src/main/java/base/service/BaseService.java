package base.service;

import base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseService<ID extends Serializable, Type extends BaseEntity<ID>> {

    int save(Type type) throws SQLException;

    Type findById(ID id) throws SQLException;

    int update(Type type) throws SQLException;

    int delete(ID id) throws SQLException;
}
