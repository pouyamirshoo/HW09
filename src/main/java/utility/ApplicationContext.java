package utility;

import connection.DBConfig;
import repository.AdminRepository;
import repository.AdminRepositoryImpel;
import repository.UserRepository;
import repository.UserRepositoryImpel;
import service.AdminService;
import service.AdminServiceImpel;
import service.UserService;
import service.UserServiceImpel;

import java.sql.Connection;

public class ApplicationContext {

    private static final Connection CONNECTION;

    private static final UserRepository USER_REPOSITORY;
    private static final AdminRepository ADMIN_REPOSITORY;


    private static final UserService USER_SERVICE;
    private static final AdminService ADMIN_SERVICE;
    static {
        CONNECTION = DBConfig.getConnection();

        USER_REPOSITORY = new UserRepositoryImpel(CONNECTION);
        USER_SERVICE = new UserServiceImpel(USER_REPOSITORY);

        ADMIN_REPOSITORY = new AdminRepositoryImpel(CONNECTION);
        ADMIN_SERVICE = new AdminServiceImpel(ADMIN_REPOSITORY);
    }

    public static UserService getUserServiceImpel() {
        return USER_SERVICE;
    }
    public static AdminService getAdminServiceImpel(){
        return ADMIN_SERVICE;
    }
}
