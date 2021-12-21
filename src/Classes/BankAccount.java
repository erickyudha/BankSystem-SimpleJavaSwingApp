package Classes;

import java.util.Random;

public class BankAccount {
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private long balance;

    public BankAccount(String importedData) {
        String[] data = importedData.split("-");
        this.id = data[0];
        this.username = data[1];
        this.firstName = data[2];
        this.lastName = data[3];
        this.password = data[4];
        this.balance = Long.parseLong(data[5]);
    }

    public static String generateNewId(BankDatabase db) {
        Random rand = new Random();
        int numId = rand.nextInt(8999999) + 1000000;
        while (db.isIdExist(Integer.toString(numId))) {
            numId = rand.nextInt(8999999) + 1000000;
        }
        return Integer.toString(numId);
    }

    public void transfer(BankDatabase db, String id, long amount) {
        balance -= amount;
        BankAccount targetAcc = new BankAccount(db.exportData(id));
        targetAcc.balance += amount;

        db.delAccount(id);
        db.addNewAccount(targetAcc.export());
        db.delAccount(this.id);
        db.addNewAccount(export());
    }

    public void withdraw(BankDatabase db, Long amount) {
        balance -= amount;
        db.delAccount(id);
        db.addNewAccount(export());
    }

    public void deposit(BankDatabase db, Long amount) {
        balance += amount;

        db.delAccount(id);
        db.addNewAccount(export());
    }

    public boolean verifyAcc(String password) {
        return this.password.equals(password);
    }

    public String export() {
        return this.id +
                "-" + this.username +
                "-" + this.firstName +
                "-" + this.lastName +
                "-" + this.password +
                "-" + this.balance;
    }

    public String getId() {
        return id;
    }

    public String getName() { return firstName + " " + lastName; }

    public long getBalance() {
        return balance;
    }
}
