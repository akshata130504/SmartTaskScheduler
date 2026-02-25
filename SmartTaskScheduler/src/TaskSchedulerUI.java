import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TaskSchedulerUI {

    private TaskManager manager = new TaskManager();
    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField titleField;
    private JTextField descField;
    private JTextField priorityField;
    private JTextField deadlineField;

    public TaskSchedulerUI() {

        JFrame frame = new JFrame("Smart Task Scheduler");
        frame.setSize(900, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("SMART TASK SCHEDULER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(40, 90, 170));
        frame.add(titleLabel, BorderLayout.NORTH);

        String[] columns = {"Title", "Priority", "Deadline", "Completed"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add / Edit Task"));

        titleField = new JTextField();
        descField = new JTextField();
        priorityField = new JTextField();
        deadlineField = new JTextField("2026-02-25");

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Priority (1-High,2-Med,3-Low):"));
        inputPanel.add(priorityField);
        inputPanel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        inputPanel.add(deadlineField);

        JButton addButton = new JButton("Add Task");
        JButton editButton = new JButton("Edit Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton completeButton = new JButton("Mark Completed");

        inputPanel.add(addButton);
        inputPanel.add(editButton);

        frame.add(inputPanel, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel();
        JButton todayButton = new JButton("Today's Tasks");
        JButton highButton = new JButton("High Priority");
        JButton showAllButton = new JButton("Show All");

        bottomPanel.add(deleteButton);
        bottomPanel.add(completeButton);
        bottomPanel.add(todayButton);
        bottomPanel.add(highButton);
        bottomPanel.add(showAllButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        loadTable(manager.getAllTasks());

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                titleField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                priorityField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                deadlineField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            }
        });

        addButton.addActionListener(e -> {
            try {
                Task task = new Task(
                        titleField.getText(),
                        descField.getText(),
                        Integer.parseInt(priorityField.getText()),
                        LocalDate.parse(deadlineField.getText())
                );
                manager.addTask(task);
                loadTable(manager.getAllTasks());
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String oldTitle = tableModel.getValueAt(selectedRow, 0).toString();
                    manager.deleteTask(oldTitle);

                    Task updatedTask = new Task(
                            titleField.getText(),
                            descField.getText(),
                            Integer.parseInt(priorityField.getText()),
                            LocalDate.parse(deadlineField.getText())
                    );

                    manager.addTask(updatedTask);
                    loadTable(manager.getAllTasks());
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Input!");
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String title = tableModel.getValueAt(selectedRow, 0).toString();
                manager.deleteTask(title);
                loadTable(manager.getAllTasks());
                clearFields();
            }
        });

        completeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String title = tableModel.getValueAt(selectedRow, 0).toString();
                manager.markTaskCompleted(title);
                loadTable(manager.getAllTasks());
                clearFields();
            }
        });

        todayButton.addActionListener(e -> loadTable(manager.getTodayTasks()));
        highButton.addActionListener(e -> loadTable(manager.getHighPriorityTasks()));
        showAllButton.addActionListener(e -> loadTable(manager.getAllTasks()));

        manager.startReminder();
        frame.setVisible(true);
    }

    private void loadTable(List<Task> tasks) {
        tableModel.setRowCount(0);
        for (Task task : tasks) {
            tableModel.addRow(new Object[]{
                    task.getTitle(),
                    task.getPriority(),
                    task.getDeadline(),
                    task.isCompleted()
            });
        }
    }

    private void clearFields() {
        titleField.setText("");
        descField.setText("");
        priorityField.setText("");
        deadlineField.setText("");
    }

    public static void main(String[] args) {
        new TaskSchedulerUI();
    }
}