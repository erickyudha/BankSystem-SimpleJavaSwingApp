package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainForm extends JFrame {
    public JPanel MainPanel;
    private JTextField unameField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;
    private BankDatabase db;

    public MainForm(JFrame frame, BankDatabase db) {
        this.db = db;

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = unameField.getText();
                String password = String.valueOf(passField.getPassword());
                if (username.length() < 1 || password.length() < 1) {
                    JOptionPane.showMessageDialog(MainPanel, "Invalid Input", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (!db.isUsernameExist(username)) {
                        JOptionPane.showMessageDialog(MainPanel, "Username Doesn't Exist");
                    } else {
                        BankAccount user = new BankAccount(db.exportData(username));
                        if (!user.verifyAcc(password)) {
                            JOptionPane.showMessageDialog(MainPanel, "Wrong Password");
                        } else {
                            frame.setContentPane(new UserDashboardForm(frame, db, user).MainPanel);
                        }
                    }
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new RegisterForm(frame, db).MainPanel);
            }
        });
    }
}
