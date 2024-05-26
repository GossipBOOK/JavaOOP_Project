import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame{
    login(){
        setLayout(null);
        JLabel GossipBook = new JLabel("Gossip Book");
        GossipBook.setFont(new Font("Inter",Font.PLAIN,30));
        GossipBook.setBounds(50,120,200,40);
        GossipBook.setForeground(Color.WHITE);
        add(GossipBook);

        JLabel text = new JLabel("<html>Connect with friend and the world <br>around you on Gossip Book.<html>");
        text.setFont(new Font("Inter",Font.PLAIN,16));
        text.setBounds(50,160,400,100);
        text.setForeground(Color.WHITE);
        add(text);

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(400,60,320,360);
        loginPanel.setBackground(new Color(16, 16, 62));
        add(loginPanel);
        
        loginPanel.setLayout(null);

        JLabel head = new JLabel("LOGIN");
        head.setFont(new Font("Serif",Font.PLAIN,25));
        head.setBounds(110,20,100,50);
        head.setForeground(Color.WHITE);
        loginPanel.add(head);


        JTextField email = new JTextField("Enter your email address");
        email.setFont(new Font("Inter",Font.BOLD,15));
        email.setBounds(40,90,240,40);
        email.setForeground(Color.white);
        email.setBackground(new Color(29, 27, 38));
        loginPanel.add(email);

        JPasswordField passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Inter",Font.BOLD,15));
        passwordField.setBounds(40,150,240,40);
        passwordField.setForeground(Color.white);
        passwordField.setBackground(new Color(29, 27, 38));
        loginPanel.add(passwordField);

        JButton login = new JButton("Login");
        login.setFont(new Font("Inter",Font.BOLD,15));
        login.setBounds(40,210,240,40);
        login.setForeground(Color.white);
        login.setBackground(new Color(29, 27, 38));
        login.setFocusable(false);

        loginPanel.add(login);

        JButton ForgotPassword = new JButton("Forgot Password?");
        ForgotPassword.setFont(new Font("Inter",Font.BOLD,15));
        ForgotPassword.setBounds(40,260,240,30);
        ForgotPassword.setBorderPainted(false);
        ForgotPassword.setForeground(Color.white);
        ForgotPassword.setBackground(getForeground());
        ForgotPassword.setFocusable(false);
        loginPanel.add(ForgotPassword);

        JButton create = new JButton("Create New Account");
        create.setFont(new Font("Inter",Font.BOLD,15));
        create.setBounds(40,310,240,30);
        create.setForeground(Color.white);
        create.setBackground(getForeground());
        create.setFocusable(false);
        loginPanel.add(create);


        setTitle("Login");
        setVisible(true);
        setSize(800,500);
        setLocation(100, 100);
        getContentPane().setBackground(new Color(17, 8, 62));

    }
    
    public static void main(String[] args) {
        new login();
    }
}