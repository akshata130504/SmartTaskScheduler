import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:expenses.db";

    public static Connection connect() {

        try {
            Class.forName("org.sqlite.JDBC"); // load driver

            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Database Connected Successfully!");

            return conn;

        } catch (Exception e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}