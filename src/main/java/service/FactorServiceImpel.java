package service;

import base.repository.BaseRepositoryImpel;
import base.service.BaseServiceImpel;
import models.Factor;
import repository.FactorRepository;

import java.sql.SQLException;

public class FactorServiceImpel extends BaseServiceImpel<Integer, Factor, FactorRepository> implements FactorService {
    public FactorServiceImpel(FactorRepository repository) {
        super(repository);
    }

    @Override
    public int[] numOfFactor(int id) throws SQLException {
        return repository.numOfFactor(id);
    }

    @Override
    public int saveFactorInnerTable(int idF, int idP) throws SQLException {
        return repository.saveFactorInnerTable(idF,idP);
    }

    @Override
    public int[] productsOfOneFactor(int id) throws SQLException {
        return repository.productsOfOneFactor(id);
    }
}
