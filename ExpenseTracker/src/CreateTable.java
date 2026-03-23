import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "category TEXT," +
                     "amount REAL," +
                     "date TEXT" +
                     ");";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Expenses table created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}