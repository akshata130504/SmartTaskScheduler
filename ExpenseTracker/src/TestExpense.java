import java.util.List;

public class TestExpense {

    public static void main(String[] args) {

        ExpenseDAO dao = new ExpenseDAO();

        Expense e1 = new Expense("Lunch", "Food", 150, "2026-03-12");
        dao.addExpense(e1);

        Expense e2 = new Expense("Bus Ticket", "Travel", 50, "2026-03-12");
        dao.addExpense(e2);

        List<Expense> expenses = dao.getAllExpenses();

        for (Expense e : expenses) {
            System.out.println(e.getId() + " " +
                               e.getTitle() + " " +
                               e.getCategory() + " " +
                               e.getAmount() + " " +
                               e.getDate());
        }
    }
}