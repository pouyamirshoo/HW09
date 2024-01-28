package repository;


import base.repository.BaseRepositoryImpel;
import models.Branch;
import models.SubBranch;
import service.BranchService;
import utility.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubBranchRepositoryImpel extends BaseRepositoryImpel<Integer, SubBranch> implements SubBranchRepository{
    public SubBranchRepositoryImpel(Connection connection) {
        super(connection);
    }
    BranchService branchService = ApplicationContext.getBranchServiceImpel();

    @Override
    public String getTableName() {
        return "subbranch";
    }

    @Override
    public String getColumnsName() {
        return "(subbranch_name , branch_id_fk)";
    }

    @Override
    public String getEditNameColumn() {
        return "subbranch_name";
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return "(? , ?)";
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, SubBranch subBranch, boolean isCountOnly) throws SQLException {
        preparedStatement.setString(1,subBranch.getSubBranchName());
        Branch branch = subBranch.getBranch();
        preparedStatement.setInt(2,branch.getId());
    }

    @Override
    public SubBranch mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        SubBranch subBranch = new SubBranch();

        subBranch.setId(resultSet.getInt("subbranch_id"));
        subBranch.setSubBranchName(resultSet.getString("subbranch_name"));
        int branchId = resultSet.getInt("branch_id_fk");
        subBranch.setBranch(branchService.findById(branchId));
        return subBranch;
    }

    @Override
    public String getUpdateQueryParams() {
        return "subbranch_name";
    }
    public int editBranchFk(int id,String name) throws SQLException {

        String sql = "UPDATE subbranch SET branch_id_fk = ? WHERE subbranch_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public SubBranch[] showAllSubBranches() throws SQLException {
        SubBranch [] subBranches = new SubBranch[numOfArray()];

        int i = 0;

        String sql = "SELECT * FROM " + getTableName();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SubBranch subBranch = mapResultSetToEntity(resultSet);
                subBranches[i] = subBranch;
                i ++ ;
            }
        }
        return subBranches;
    }
}
