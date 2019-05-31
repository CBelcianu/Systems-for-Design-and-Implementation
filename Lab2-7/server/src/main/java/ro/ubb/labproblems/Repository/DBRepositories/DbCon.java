package ro.ubb.labproblems.Repository.DBRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    private Connection conn;

    //"jdbc:sqlite:C:\\Users\\Catalin\\Desktop\\#WORK\\anul2\\MAP\\labDB"
    public DbCon(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:./RepositoryData/Database/labDB");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConn() {
        return conn;
    }

    public void closeConn(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
