package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static Connection connection = null;

    private DBConfig(){}

    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "12913133");
            } catch (SQLException e) {
                System.out.println("not found url");
            }
        }
        return connection;
    }
    }


