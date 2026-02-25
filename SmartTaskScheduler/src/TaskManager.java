import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TaskManager {

    private PriorityQueue<Task> taskQueue;
    private final String FILE_NAME = "tasks.dat";

    public TaskManager() {
        taskQueue = new PriorityQueue<>();
        loadTasksFromFile();
    }

    public void addTask(Task task) {
        taskQueue.add(task);
        saveTasksToFile();
    }

    public void deleteTask(String title) {
        Iterator<Task> iterator = taskQueue.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                saveTasksToFile();
                return;
            }
        }
    }

    public void markTaskCompleted(String title) {
        for (Task task : taskQueue) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markCompleted();
                saveTasksToFile();
                return;
            }
        }
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskQueue);
    }

    public List<Task> getTodayTasks() {
        List<Task> todayTasks = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Task task : taskQueue) {
            if (task.getDeadline().equals(today)) {
                todayTasks.add(task);
            }
        }
        return todayTasks;
    }

    public List<Task> getHighPriorityTasks() {
        List<Task> highTasks = new ArrayList<>();
        for (Task task : taskQueue) {
            if (task.getPriority() == 1) {
                highTasks.add(task);
            }
        }
        return highTasks;
    }

    // Reminder
    public void startReminder() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Task next = taskQueue.peek();
                if (next != null && !next.isCompleted()) {
                    System.out.println("🔔 Reminder: Complete task -> " + next.getTitle());
                }
            }
        }, 5000, 10000);
    }

    private void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new ArrayList<>(taskQueue));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Task> list = (List<Task>) ois.readObject();
            taskQueue.addAll(list);
        } catch (Exception e) {
            System.out.println("No previous tasks found.");
        }
    }
}