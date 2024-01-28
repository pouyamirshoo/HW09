package repository;

import base.repository.BaseRepository;
import models.Users;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, Users> {

    Users logIn(String username,String password) throws SQLException;
}
