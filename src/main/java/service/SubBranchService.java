package service;

import base.service.BaseService;
import models.SubBranch;

import java.sql.SQLException;

public interface SubBranchService extends BaseService<Integer, SubBranch> {
    SubBranch [] showAllSubBranches() throws SQLException;
    int editBranchFk(int id,String name) throws SQLException;
    int deleteFromInnerTable(int id) throws SQLException;
}
