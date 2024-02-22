package finance.manager;

public class FinanceRecord {
    private final int id;
    private final double amount;

    public FinanceRecord(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
      return amount;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Amount: " + amount;
    }
}
