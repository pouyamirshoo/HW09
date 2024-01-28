package service;

import base.service.BaseService;
import models.Admins;

import java.sql.SQLException;

public interface AdminService extends BaseService<Integer, Admins> {
    Admins logIn(String username, String password) throws SQLException;
}
