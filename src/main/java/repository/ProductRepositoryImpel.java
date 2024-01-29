package repository;

import base.repository.BaseRepositoryImpel;
import models.Products;
import models.SubBranch;
import service.SubBranchService;
import utility.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepositoryImpel extends BaseRepositoryImpel<Integer, Products> implements ProductRepository{
    public ProductRepositoryImpel(Connection connection) {
        super(connection);
    }
    SubBranchService subBranchService = ApplicationContext.getSubBranchServiceImpel();

    @Override
    public String getTableName() {
        return "products";
    }

    @Override
    public String getColumnsName() {
        return "(products_name , products_price , products_number , subbranch_id_fk)";
    }

    @Override
    public String getEditNameColumn() {
        return "products_name";
    }

    @Override
    public String getIdColumnName() {
        return "products_id";
    }

    @Override
    public String getCountOfQuestionMarkParams() {
        return "(? , ? , ? , ?)";
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Products product, boolean isCountOnly) throws SQLException {
        preparedStatement.setString(1,product.getProductName());
        preparedStatement.setFloat(2,product.getPrice());
        preparedStatement.setInt(3,product.getNumber());
        SubBranch subBranch = product.getSubBranch();
        preparedStatement.setInt(4,subBranch.getId());
    }

    @Override
    public Products mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Products product = new Products();

        product.setId(resultSet.getInt("products_id"));
        product.setProductName(resultSet.getString("products_name"));
        product.setPrice(resultSet.getFloat("products_price"));
        product.setNumber(resultSet.getInt("products_number"));
        product.setSubBranch(subBranchService.findById(resultSet.getInt("subbranch_id_fk")));
        return product;
    }

    @Override
    public String getUpdateQueryParams() {
        return "products_name";
    }

    @Override
    public Products[] showAllProducts() throws SQLException {
        Products [] products = new Products[numOfArray()];
        int i = 0;

        String sql = "SELECT * FROM " + getTableName();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Products product = mapResultSetToEntity(resultSet);
                products[i] = product;
                i ++ ;
            }
        }
        return products;
    }
}
