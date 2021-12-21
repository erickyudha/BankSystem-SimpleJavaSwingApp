package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferForm {
    private JTextField idBox;
    private JTextField amountBox;
    private JButton OKButton;
    private JButton confirmButton;
    private JPanel summaryPanel;
    private JPanel inputPanel;
    public JPanel MainPanel;
    private JLabel fromIdLabel;
    private JLabel fromNameLabel;
    private JLabel toIdLabel;
    private JLabel toNameLabel;
    private JLabel amountLabel;
    private JLabel balanceAfterLabel;
    private JButton backButton;
    private JFrame frame;
    private BankDatabase db;
    private BankAccount user;
    private BankAccount targetUser;
    private long transferAmount;

    public TransferForm(JFrame frame, BankDatabase db, BankAccount user) {
        this.frame = frame;
        this.db = db;
        this.user = user;

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        summaryPanel.setVisible(false);



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
        OKButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetID = idBox.getText();
                String transferAmountStr = amountBox.getText();

                if (targetID.length() < 1 || transferAmountStr.length() < 1) {
                    summaryPanel.setVisible(false);
                    JOptionPane.showMessageDialog(MainPanel, "Check Input Again", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        transferAmount = Long.parseLong(transferAmountStr);

                        if (!db.isIdExist(targetID) || targetID.equals(user.getId())) {
                            summaryPanel.setVisible(false);
                            JOptionPane.showMessageDialog(MainPanel, "Check Target ID Again", "", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (transferAmount > user.getBalance()) {
                                summaryPanel.setVisible(false);
                                JOptionPane.showMessageDialog(MainPanel, "Insufficient Balance", "", JOptionPane.WARNING_MESSAGE);
                            } else {
                                targetUser = new BankAccount(db.exportDataById(targetID));

                                fromIdLabel.setText(user.getId());
                                fromNameLabel.setText(user.getName());

                                toIdLabel.setText(targetUser.getId());
                                toNameLabel.setText(targetUser.getName());

                                amountLabel.setText("Rp " + transferAmountStr);
                                balanceAfterLabel.setText("Rp " + (user.getBalance() - transferAmount));

                                idBox.setText("");
                                amountBox.setText("");

                                summaryPanel.setVisible(true);
                            }
                        }

                    } catch (Exception ex) {
                        summaryPanel.setVisible(false);
                        JOptionPane.showMessageDialog(MainPanel, "Invalid Amount Format");
                    }
                }
            }


        });


        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                user.withdraw(db, transferAmount);
                targetUser.deposit(db, transferAmount);

                JOptionPane.showMessageDialog(MainPanel, "Transfer Success");

                frame.setContentPane(new UserDashboardForm(frame, db, user).MainPanel);
            }
        });
    }
}
