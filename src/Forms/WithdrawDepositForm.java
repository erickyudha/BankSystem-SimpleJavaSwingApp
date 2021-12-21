package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawDepositForm {
    private JTextField amountField;
    private JButton confirmButton;
    public JPanel MainPanel;
    private JButton backButton;
    private JLabel menuTitle;
    private JLabel amountTitle;
    private JFrame frame;
    private BankDatabase db;
    private BankAccount user;
    private String type;

    public WithdrawDepositForm(JFrame frame, BankDatabase db, BankAccount user, String type) {
        this.frame = frame;
        this.db = db;
        this.user = user;
        this.type = type;

        menuTitle.setText(type + " Menu");
        amountTitle.setText(type + " Amount");

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long amount = Long.parseLong(amountField.getText());

                    try {
                        if (user.getBalance() < amount && type.equals("Withdraw")) {
                            JOptionPane.showMessageDialog(MainPanel, "Insufficient Balance");
                        } else {
                            if (type.equals("Withdraw")) {
                                user.withdraw(db, amount);
                            } else if (type.equals("Deposit")) {
                                user.deposit(db, amount);
                            }
                            JOptionPane.showMessageDialog(MainPanel, "Transaction Success", "", JOptionPane.PLAIN_MESSAGE);

                            frame.setContentPane(new UserDashboardForm(frame, db, user).MainPanel);
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex.toString());
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainPanel, "Invalid Amount");
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UserDashboardForm(frame, db, user).MainPanel);
            }
        });
    }
}
