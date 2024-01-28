package repository;

import base.repository.BaseRepository;
import models.Admins;

import java.sql.SQLException;

public interface AdminRepository extends BaseRepository<Integer, Admins> {
    Admins login (String username,String password) throws SQLException;
}
