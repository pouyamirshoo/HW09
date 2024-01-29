package utility;

import connection.DBConfig;
import repository.*;
import service.*;

import java.sql.Connection;

public class ApplicationContext {

    private static final Connection CONNECTION;

    private static final UserRepository USER_REPOSITORY;
    private static final AdminRepository ADMIN_REPOSITORY;
    private static final BranchRepository BRANCH_REPOSITORY;
    private static final SubBranchRepository SUB_BRANCH_REPOSITORY;
    private static final ProductRepository PRODUCT_REPOSITORY;


    private static final UserService USER_SERVICE;
    private static final AdminService ADMIN_SERVICE;
    private static final BranchService BRANCH_SERVICE;
    private static final SubBranchService SUB_BRANCH_SERVICE;
    private static final ProductService PRODUCT_SERVICE;

    static {
        CONNECTION = DBConfig.getConnection();

        USER_REPOSITORY = new UserRepositoryImpel(CONNECTION);
        USER_SERVICE = new UserServiceImpel(USER_REPOSITORY);

        ADMIN_REPOSITORY = new AdminRepositoryImpel(CONNECTION);
        ADMIN_SERVICE = new AdminServiceImpel(ADMIN_REPOSITORY);

        BRANCH_REPOSITORY = new BranchRepositoryImpel(CONNECTION);
        BRANCH_SERVICE = new BranchServiceImpel(BRANCH_REPOSITORY);

        SUB_BRANCH_REPOSITORY = new SubBranchRepositoryImpel(CONNECTION);
        SUB_BRANCH_SERVICE = new SubBranchServiceImpel(SUB_BRANCH_REPOSITORY);

        PRODUCT_REPOSITORY = new ProductRepositoryImpel(CONNECTION);
        PRODUCT_SERVICE = new ProductServiceImpel(PRODUCT_REPOSITORY);
    }

    public static UserService getUserServiceImpel() {
        return USER_SERVICE;
    }
    public static AdminService getAdminServiceImpel(){
        return ADMIN_SERVICE;
    }
    public static BranchService getBranchServiceImpel(){
        return BRANCH_SERVICE;
    }
    public static SubBranchService getSubBranchServiceImpel(){
        return SUB_BRANCH_SERVICE;
    }
    public static ProductService getProductServiceIMpel(){
        return PRODUCT_SERVICE;
    }
}
