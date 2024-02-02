package service;

import base.service.BaseServiceImpel;
import models.Users;
import repository.UserRepository;

import java.sql.SQLException;

public class UserServiceImpel extends BaseServiceImpel<Integer, Users, UserRepository> implements UserService {

    public UserServiceImpel(UserRepository repository) {
        super(repository);
    }

    @Override
    public Users logIn(String username,String password) throws SQLException {
        return repository.logIn(username,password);
    }

    @Override
    public int saveUserInnerTable(int id) throws SQLException {
        return repository.saveUserInnerTable(id);
    }
}
