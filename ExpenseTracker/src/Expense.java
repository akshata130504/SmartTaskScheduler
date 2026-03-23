public class Expense {

    private int id;
    private String title;
    private String category;
    private double amount;
    private String date;

    public Expense(String title, String category, double amount, String date) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    public void setId(int id) { this.id = id; }
}