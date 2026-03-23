import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ExpenseTrackerUI {

    private ExpenseDAO dao = new ExpenseDAO();
    private DefaultTableModel tableModel;
    private JTable table;

    public ExpenseTrackerUI() {

        JFrame frame = new JFrame("Expense Tracker");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Expense Tracker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        frame.add(title, BorderLayout.NORTH);

        String[] columns = {"ID", "Title", "Category", "Amount", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(5,2,5,5));

        JTextField titleField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField dateField = new JTextField(LocalDate.now().toString());

        panel.add(new JLabel("Title"));
        panel.add(titleField);

        panel.add(new JLabel("Category"));
        panel.add(categoryField);

        panel.add(new JLabel("Amount"));
        panel.add(amountField);

        panel.add(new JLabel("Date"));
        panel.add(dateField);

        JButton addBtn = new JButton("Add Expense");
        JButton deleteBtn = new JButton("Delete Expense");
        JButton refreshBtn = new JButton("Refresh");
        JButton chartBtn = new JButton("Show Analytics");
        JButton exportBtn = new JButton("Export CSV");
        
        panel.add(addBtn);
        panel.add(deleteBtn);

        frame.add(panel, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel();

        bottomPanel.add(refreshBtn);
        bottomPanel.add(chartBtn);
        bottomPanel.add(exportBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        loadExpenses();

        addBtn.addActionListener(e -> {

            try {

                String titleText = titleField.getText();
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                Expense exp = new Expense(titleText, category, amount, date);
                dao.addExpense(exp);

                loadExpenses();

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input");
            }

        });

        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                int id = (int)tableModel.getValueAt(row,0);
                dao.deleteExpense(id);

                loadExpenses();
            }

        });

        refreshBtn.addActionListener(e -> loadExpenses());

        chartBtn.addActionListener(e -> {
            ExpenseChart.showChart();
        });

        
        exportBtn.addActionListener(e -> {

            dao.exportToCSV("expenses.csv");
            JOptionPane.showMessageDialog(null, "Expenses exported to expenses.csv");

        });
        
        frame.setVisible(true);
    }

    private void loadExpenses(){

        tableModel.setRowCount(0);

        List<Expense> list = dao.getAllExpenses();

        for(Expense e : list){

            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getTitle(),
                    e.getCategory(),
                    e.getAmount(),
                    e.getDate()
            });

        }

    }

    public static void main(String[] args) {

        new ExpenseTrackerUI();

    }

}