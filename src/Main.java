import Classes.BankDatabase;
import Forms.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BankDatabase db = new BankDatabase();

        JFrame frame = new JFrame("MyBank");
        frame.setContentPane(new MainForm(frame, db).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
