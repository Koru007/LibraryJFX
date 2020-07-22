package sample.dbConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {

    public Connection dbConnection;

    public Connection getDbConnection() {
        String connectionName = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname + "?useUnicode=true&characterEncoding=utf8&useSSL=false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbConnection = DriverManager.getConnection(connectionName, Configs.dbuser, Configs.dbpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbConnection;
    }

}
