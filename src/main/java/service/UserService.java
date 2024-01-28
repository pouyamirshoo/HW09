package service;

import base.service.BaseService;
import models.Users;

import java.sql.SQLException;

public interface UserService extends BaseService<Integer, Users> {
    Users logIn(String username,String password) throws SQLException;
}
