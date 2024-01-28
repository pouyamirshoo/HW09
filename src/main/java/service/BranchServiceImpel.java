package service;

import base.service.BaseServiceImpel;
import models.Branch;
import repository.BranchRepository;

import java.sql.SQLException;


public class BranchServiceImpel extends BaseServiceImpel<Integer, Branch, BranchRepository> implements BranchService{
    public BranchServiceImpel(BranchRepository repository) {
        super(repository);
    }

    @Override
    public Branch[] showAllBranches() throws SQLException {
        return repository.showAllBranches();
    }
}
