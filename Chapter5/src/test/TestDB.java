package test;


import jdbcUtils.jdbcUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) {
        System.out.println(getConnection());
    }


    public static Connection getConnection() {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("dbconfig.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = props.getProperty("driverClassName");
        String url = props.getProperty("url");
        String user = props.getProperty("username");
        String pass = props.getProperty("password");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test1() throws SQLException {
        System.out.println(jdbcUtils.getConnection());
    }
}


