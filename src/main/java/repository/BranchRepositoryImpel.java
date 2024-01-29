package repository;

import base.repository.BaseRepositoryImpel;
import models.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRepositoryImpel extends BaseRepositoryImpel<Integer, Branch> implements BranchRepository{
    public BranchRepositoryImpel(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "branch";
    }

    @Override
    public String getColumnsName() {
        return "(branch_name)";
    }

    @Override
    public String getEditNameColumn() {
        return "branch_name";
    }

    @Override
    public String getIdColumnName() {
        return "branch_id";
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return "(?)";
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Branch branch, boolean isCountOnly) throws SQLException {
        preparedStatement.setString(1,branch.getBranchName());
    }


    @Override
    public Branch mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Branch branch = new Branch();

        branch.setId(resultSet.getInt("branch_id"));
        branch.setBranchName(resultSet.getString("branch_name"));
        return branch;
    }

    @Override
    public String getUpdateQueryParams() {
        return "branch_name";
    }

    @Override
    public Branch[] showAllBranches() throws SQLException {
        Branch [] branches = new Branch[numOfArray()];
        int i = 0;

        String sql = "SELECT * FROM " + getTableName();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Branch branch = mapResultSetToEntity(resultSet);
                branches[i] = branch;
                i ++ ;
            }
        }
        return branches;
    }
}
