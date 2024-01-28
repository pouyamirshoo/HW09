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
    public int save(TYPE entity) throws SQLException {
       return repository.save(entity);
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public int editName(String oldName, String newName) throws SQLException {
       return repository.editName(oldName, newName);
    }

    @Override
    public int delete(ID id) throws SQLException {
       return repository.delete(id);
    }
}
