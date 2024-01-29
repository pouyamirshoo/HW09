package service;

import base.service.BaseService;
import models.Branch;

import java.sql.SQLException;


public interface BranchService extends BaseService<Integer, Branch> {
    Branch [] showAllBranches() throws SQLException;
    int deleteFromInnerTable(int id) throws SQLException;
}
