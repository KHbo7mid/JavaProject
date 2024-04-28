package db_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection(String url,String username,String password)
    {
        //driver
        String nomDriver="com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(nomDriver);
            System.out.println("driver charge");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur Driver"+e.getMessage());

        }
        //conn to database
        Connection con=null;
        try {
            con= DriverManager.getConnection(url,username,password);
            System.out.println("connected");
            return con;

        } catch (SQLException e) {
            System.out.println("erreur connection"+e.getMessage());


        }
        return null;

    }
}
