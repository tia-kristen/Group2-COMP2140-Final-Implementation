import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Driver class for Dawn’s Grocery Inventory Management System
 
public class GUI extends JFrame {
    public GUI(){
        // Setting configuration for GUI window
        super("Dawn's Grocery Inventory Management System - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        setResizable(true);
    }

    public static void main(String args[]) {
        // Creating instance of GUI which is the main window
        GUI window = new GUI();

        // Creating a panel for the home components
        JPanel homePnl = new JPanel();

        JLabel welcomeMsg = new JLabel("Dawn's Grocery Inventory Management System");
        welcomeMsg.setFont(new Font("Arial", Font.PLAIN, 20));
        homePnl.add(welcomeMsg);

        // Adding login button
        JButton loginBtn = new JButton("Go To Login");
        loginBtn.setSize(200, 68);
        loginBtn.addActionListener(window.new LoginBtnListener());
        homePnl.add(loginBtn); 
        

        // Adding panel to the GUI window
        window.add(homePnl);
        window.setVisible(true);
 
    }

    // When user clicks button, create a new instance of Administrator which is how the user will login
    private class LoginBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Administrator adminFrame = new Administrator();
            adminFrame.pack();
            adminFrame.setVisible(true);
        }
    }
}