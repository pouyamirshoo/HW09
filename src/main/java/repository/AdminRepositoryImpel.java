package repository;

import base.repository.BaseRepositoryImpel;
import models.Admins;
import models.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpel extends BaseRepositoryImpel<Integer, Admins> implements AdminRepository {

    public AdminRepositoryImpel(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "admins";
    }

    @Override
    public String getColumnsName() {
        return null;
    }

    @Override
    public String getEditNameColumn() {
        return null;
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return null;
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Admins admins, boolean isCountOnly) throws SQLException {

    }

    @Override
    public Admins mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        Admins admin = new Admins();
        admin.setId(resultSet.getInt("admin_id"));
        admin.setName(resultSet.getString("admin_name"));
        admin.setUsername(resultSet.getString("admin_username"));
        admin.setPassword(resultSet.getString("admin_password"));
        return admin;
    }

    @Override
    public String getUpdateQueryParams() {
        return null;
    }

    @Override
    public Admins login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE admin_username = ? AND admin_password = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return mapResultSetToEntity(resultSet);
        }
        return null;
    }
    }
