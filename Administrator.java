

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Login screen
public class Administrator extends JFrame{
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    public Administrator(){
        // Setting configuration for Administrator window
        super("Dawn's Grocery Inventory Management System - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(getToolkit().getScreenSize());
        setResizable(true);

        // Creating panel for admin components
        JPanel adminPnl = new JPanel();

        // Adding username and password fields
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField(20);
        adminPnl.add(usernameLabel);
        adminPnl.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(20);
        adminPnl.add(passwordLabel);
        adminPnl.add(passwordField);

     
        JButton adminLoginBtn = new JButton("Login");
        adminLoginBtn.setBounds(300, 430, 200, 68);
        adminLoginBtn.addActionListener(new AdminLoginBtnListener());
        adminPnl.add(adminLoginBtn);

        add(adminPnl);
    }

    // When the user clicks the login button the username and password will be checked
    private class AdminLoginBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // When the user successfully logs in, they will have access to the menu options
            if(usernameField.getText().equals("admin") && (String.valueOf(passwordField.getPassword()).equals("password"))){
                Menu menuFrame = new Menu();
                menuFrame.pack();
                menuFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password.");
            }

        }
    }

}
