package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboardForm {
    private JPanel headPanel;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton depositButton;
    private JButton closeAccountButton;
    private JButton logoutButton;
    public JPanel MainPanel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel balanceLabel;
    private JFrame frame;
    private BankDatabase db;
    private BankAccount user;

    public UserDashboardForm(JFrame frame, BankDatabase db, BankAccount user) {
        this.frame = frame;
        this.db = db;
        this.user = user;

        nameLabel.setText(user.getName());
        idLabel.setText(user.getId());
        balanceLabel.setText("Rp " + user.getBalance());

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        logoutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new MainForm(frame, db).MainPanel);
            }
        });

        closeAccountButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new CloseAccountForm(frame, db, user).MainPanel);
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new WithdrawDepositForm(frame, db, user, "Withdraw").MainPanel);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new WithdrawDepositForm(frame, db, user, "Deposit").MainPanel);
            }
        });
        transferButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new TransferForm(frame, db, user).MainPanel);
            }
        });
    }
}
