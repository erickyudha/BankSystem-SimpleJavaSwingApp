package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterForm {
    public JPanel MainPanel;
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField unameField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField depositField;
    private JButton registerButton;
    private JButton backButton;
    private JFrame frame;
    private BankDatabase db;

    public RegisterForm(JFrame frame, BankDatabase db) {
        this.frame = frame;
        this.db = db;

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        backButton.addActionListener(new ActionListener() {
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
        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = fnameField.getText();
                String lname = lnameField.getText();
                String username = unameField.getText();
                String pass1 = String.valueOf(passwordField1.getPassword());
                String pass2 = String.valueOf(passwordField2.getPassword());

                try {
                    long deposit = Long.parseLong(depositField.getText());

                    if (fname.length() < 1 || lname.length() < 1 || username.length() < 1 ||
                    pass1.length() < 1 || pass2.length() < 1) {
                        JOptionPane.showMessageDialog(MainPanel, "Check Input Again");
                    } else {
                        if (pass1.equals(pass2)) {
                            String id = BankAccount.generateNewId(db);
                            String newAccData = id + "-" + username + "-" + fname + "-" + lname +
                                    "-" + pass1 + "-" + deposit;
                            db.addNewAccount(newAccData);

                            JOptionPane.showMessageDialog(MainPanel, "Registration Success", "", JOptionPane.PLAIN_MESSAGE);

                            frame.setContentPane(new MainForm(frame, db).MainPanel);

                        } else {
                            JOptionPane.showMessageDialog(MainPanel, "Check Password Again");
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainPanel, "Deposit Format Wrong");
                }

            }
        });
    }
}
