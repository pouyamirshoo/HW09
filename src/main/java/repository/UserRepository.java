package repository;

import base.repository.BaseRepository;
import models.Users;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, Users> {

    Users findByUsername(String username) throws SQLException;
}
