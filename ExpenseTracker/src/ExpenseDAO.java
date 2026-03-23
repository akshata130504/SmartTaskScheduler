import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ExpenseDAO {

    // Insert expense
    public void addExpense(Expense expense) {

        String sql = "INSERT INTO expenses(title, category, amount, date) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, expense.getTitle());
            pstmt.setString(2, expense.getCategory());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setString(4, expense.getDate());

            pstmt.executeUpdate();
            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all expenses
    public List<Expense> getAllExpenses() {

        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Expense exp = new Expense(
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")
                );

                exp.setId(rs.getInt("id"));
                list.add(exp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete expense
    public void deleteExpense(int id) {

        String sql = "DELETE FROM expenses WHERE id=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Expense deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, Double> getCategorySummary(){

        Map<String, Double> map = new HashMap<>();

        String sql = "SELECT category, SUM(amount) as total FROM expenses GROUP BY category";

        try(Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                map.put(rs.getString("category"), rs.getDouble("total"));
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }
    
    public void exportToCSV(String filePath) {

        String sql = "SELECT * FROM expenses";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             java.io.FileWriter writer = new java.io.FileWriter(filePath)) {

            writer.append("ID,Title,Category,Amount,Date\n");

            while (rs.next()) {

                writer.append(rs.getInt("id") + ",");
                writer.append(rs.getString("title") + ",");
                writer.append(rs.getString("category") + ",");
                writer.append(rs.getDouble("amount") + ",");
                writer.append(rs.getString("date") + "\n");

            }

            writer.flush();
            writer.close();

            System.out.println("CSV Exported Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}