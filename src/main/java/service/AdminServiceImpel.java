package service;

import base.service.BaseServiceImpel;
import models.Admins;
import repository.AdminRepository;

import java.sql.SQLException;

public class AdminServiceImpel extends BaseServiceImpel<Integer, Admins, AdminRepository> implements AdminService {
    public AdminServiceImpel(AdminRepository repository) {
        super(repository);
    }

    @Override
    public Admins logIn(String username, String password) throws SQLException {
        return repository.login(username,password);
    }
}
