package Forms;

import Classes.BankAccount;
import Classes.BankDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseAccountForm {
    private JButton yesButton;
    private JButton cancelButton;
    public JPanel MainPanel;
    private JFrame frame;
    private BankAccount user;
    private BankDatabase db;

    public CloseAccountForm(JFrame frame, BankDatabase db, BankAccount user) {
        this.frame = frame;
        this.db = db;
        this.user = user;

        frame.setContentPane(this.MainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        cancelButton.addActionListener(new ActionListener() {
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

        yesButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delAccount(user.getId());
                frame.setContentPane(new MainForm(frame, db).MainPanel);
            }
        });
    }
}
