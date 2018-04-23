package exec;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ExecSQL {
    public static void main(String[] args) throws IOException {
        try (Scanner in = args.length == 0 ? new Scanner(System.in)
                : new Scanner(Paths.get(args[0]), "UTF-8"))
        {
            try (Connection con = getConnection();
                    Statement stat = con.createStatement()) {
                while(true) {
                    if (args.length == 0) System.out.println("Enter command or EXIT to exit");

                    if (!in.hasNextLine()) return;

                    String line = in.nextLine().trim();
                    if (line.equalsIgnoreCase("EXIT")) return;
                    if (line.endsWith(";"))  // remove trailing semicolon
                    {
                        line = line.substring(0, line.length() -1);
                    }
                    try {
                        boolean isResult = stat.execute(line);
                        if (isResult) {
                            try (ResultSet rs = stat.getResultSet()) {
                                showResultSet(rs);
                            }
                        }
                        else {
                            int updateCount = stat.getUpdateCount();
                            System.out.println(updateCount + " rows updated");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets a connection from the properties specified in the file database.properties.
     * @return the database connection
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("dbconfig.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = props.getProperty("driverClassName");
        Class.forName(driver);

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url,username,password);
    }

    public static void showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(", ");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while(result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(", ");
                System.out.print(result.getString(i));
            }
            System.out.println();
        }
    }
}
