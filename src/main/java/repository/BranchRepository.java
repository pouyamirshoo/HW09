package repository;

import base.repository.BaseRepository;
import models.Branch;

import java.sql.SQLException;


public interface BranchRepository extends BaseRepository<Integer, Branch> {

    Branch [] showAllBranches() throws SQLException;
}
