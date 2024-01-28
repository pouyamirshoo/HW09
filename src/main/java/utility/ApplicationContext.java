package utility;

import connection.DBConfig;
import repository.UserRepository;
import repository.UserRepositoryImpel;
import service.UserService;
import service.UserServiceImpel;

import java.sql.Connection;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final UserService USER_SERVICE;

    static {
        CONNECTION = DBConfig.getConnection();
        USER_REPOSITORY = new UserRepositoryImpel(CONNECTION);
        USER_SERVICE = new UserServiceImpel(USER_REPOSITORY);
    }

    public static UserService getUserServiceImpel() {
        return USER_SERVICE;
    }
}
