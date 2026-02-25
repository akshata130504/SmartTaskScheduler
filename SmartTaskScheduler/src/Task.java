import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {

    private String title;
    private String description;
    private int priority; // 1 = High, 2 = Medium, 3 = Low
    private LocalDate deadline;
    private boolean completed;

    public Task(String title, String description, int priority, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public LocalDate getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }
        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public String toString() {
        return title +
                " | Priority: " + priority +
                " | Deadline: " + deadline +
                " | Completed: " + completed;
    }
}