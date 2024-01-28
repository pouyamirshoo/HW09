package service;

import base.service.BaseServiceImpel;
import models.SubBranch;
import repository.SubBranchRepository;

import java.sql.SQLException;

public class SubBranchServiceImpel extends BaseServiceImpel<Integer, SubBranch, SubBranchRepository> implements SubBranchService{
    public SubBranchServiceImpel(SubBranchRepository repository) {
        super(repository);
    }

    @Override
    public SubBranch[] showAllSubBranches() throws SQLException {
        return repository.showAllSubBranches();
    }

    @Override
    public int editBranchFk(int id, String name) throws SQLException {
        return repository.editBranchFk(id,name);
    }
}
