package base.repository;

import base.model.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<ID extends Serializable , TYPE extends BaseEntity<ID>> {
    void save (TYPE type);
    TYPE findById(ID id);
    void update(TYPE type);
    void delete(ID id);
}
