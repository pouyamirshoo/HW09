package repository;

import base.repository.BaseRepositoryImpel;
import models.Branch;
import models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpel extends BaseRepositoryImpel<Integer, Users> implements UserRepository {

    public UserRepositoryImpel(Connection connection){
        super(connection);
    }

    @Override
    public int editName(Branch branch) throws SQLException {
        return 0;
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String getColumnsName() {
        return "(user_name, user_username, user_password, user_email)";
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return "(?, ?, ?, ?)";
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Users users, boolean isCountOnly) throws SQLException {
        preparedStatement.setString(1, users.getFullName());
        preparedStatement.setString(2, users.getUsername());
        preparedStatement.setString(3, users.getPassword());
        preparedStatement.setString(4, users.getEmail());
    }

    @Override
    public Users mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getInt("user_id"));
        user.setFullName(resultSet.getString("user_name"));
        user.setUsername(resultSet.getString("user_username"));
        user.setPassword(resultSet.getString("user_password"));
        user.setEmail(resultSet.getString("user_email"));
        return user;
    }

    @Override
    public String getUpdateQueryParams() {
        return "firstname = ?, lastname = ?, username = ?, password = ?";
    }

    @Override
    public Users logIn(String username,String password) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE user_username = ? AND user_password = ? ";
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
