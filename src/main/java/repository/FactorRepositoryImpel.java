package repository;

import base.repository.BaseRepositoryImpel;
import models.Factor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactorRepositoryImpel extends BaseRepositoryImpel<Integer, Factor> implements FactorRepository{

    public FactorRepositoryImpel(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "factor_product";
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
    public String getIdColumnName() {
        return null;
    }

    @Override
    public String getIdFkColumnName() {
        return "factor_id_fk";
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return null;
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Factor factor, boolean isCountOnly) throws SQLException {

    }

    @Override
    public Factor mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public String getUpdateQueryParams() {
        return null;
    }
    public int [] numOfFactor(int id) throws SQLException {

        int [] numOfFactor= new int[99999];
        int i = 0;

        String sql = "SELECT * FROM factor WHERE user_id_fk = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int num = resultSet.getInt("factor_id");
                numOfFactor[i] = num;
                i++;
            }
        }
        return numOfFactor;
    }
    public int saveFactorInnerTable(int idF,int idP) throws SQLException {

        String sql = "INSERT INTO factor_product(factor_id_fk,product_id_fk) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,idF);
            preparedStatement.setInt(2,idP);
            return preparedStatement.executeUpdate();
        }
    }
    public int [] productsOfOneFactor(int id) throws SQLException {

        int [] productsId = new int[numOfOneArray(id)];
        int  i = 0;

        String sql = "SELECT * FROM factor_product WHERE factor_id_fk = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int temp = resultSet.getInt("product_id_fk");
                productsId[i] = temp;
                i++;
            }
        }
        return productsId;
    }
}
