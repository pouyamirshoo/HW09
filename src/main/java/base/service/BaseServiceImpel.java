package base.service;

import base.model.BaseEntity;
import base.repository.BaseRepository;

import java.io.Serializable;
import java.sql.SQLException;

public class BaseServiceImpel<ID extends Serializable, TYPE extends BaseEntity<ID>, REPOSITOORY extends BaseRepository<ID, TYPE>>
        implements BaseService<ID, TYPE> {

    protected final REPOSITOORY repository;

    public BaseServiceImpel(REPOSITOORY repository) {
        this.repository = repository;
    }

    @Override
    public void save(TYPE entity) throws SQLException {
        repository.save(entity);
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        repository.update(entity);
    }

    @Override
    public void delete(ID id) throws SQLException {
        repository.delete(id);
    }
}
