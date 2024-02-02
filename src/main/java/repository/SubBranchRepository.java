package repository;

import base.repository.BaseRepository;
import models.SubBranch;

import java.sql.SQLException;

public interface SubBranchRepository extends BaseRepository<Integer, SubBranch> {
    SubBranch [] showAllSubBranches() throws SQLException;
    int editBranchFk(int id,String name) throws SQLException;
    int deleteFromInnerTable(int id) throws SQLException;
    SubBranch [] showOneBranchSubBranches(int id) throws SQLException;
}
