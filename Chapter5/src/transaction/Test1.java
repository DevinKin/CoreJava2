package transaction;

import jdbcUtils.jdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class Test1 {
    @Test
    public void test1() throws SQLException {
        Connection conn = null;
        try {
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            Statement stat = conn.createStatement();

            String sql1 = "DELETE FROM Authors WHERE Fname = 'John'";
            String sql2 = "DELETE FROM Authors WHERE Fname = 'Andries'";
            stat.executeUpdate(sql1);
            Savepoint svpt = conn.setSavepoint();
            stat.executeUpdate(sql2);

            Scanner in = new Scanner(System.in);
            if (true)
                conn.rollback(svpt);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
