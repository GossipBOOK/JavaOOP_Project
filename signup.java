import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class signup extends JFrame implements ActionListener{
    JTextField email;
    JPasswordField passwordField;
    JPasswordField repasswordField ;
    JButton signup,login;
    signup(){
        
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

        JPanel signupPanel = new JPanel();
        signupPanel.setBounds(400,60,320,360);
        signupPanel.setBackground(new Color(16, 16, 62));
        add(signupPanel);
        
        signupPanel.setLayout(null);

        JLabel head = new JLabel("SIGN UP");
        head.setFont(new Font("Serif",Font.PLAIN,25));
        head.setBounds(110,20,100,50);
        head.setForeground(Color.WHITE);
        signupPanel.add(head);


        email = new JTextField("Enter your email address");
        email.setFont(new Font("Inter",Font.BOLD,15));
        email.setBounds(40,90,240,40);
        email.setForeground(Color.white);
        email.setBackground(new Color(29, 27, 38));
        signupPanel.add(email);

        passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Inter",Font.BOLD,20));
        passwordField.setBounds(40,150,240,40);
        passwordField.setForeground(Color.white);
        passwordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(passwordField);

        
        repasswordField = new JPasswordField("Password");
        repasswordField.setFont(new Font("Inter",Font.BOLD,20));
        repasswordField.setBounds(40,200,240,40);
        repasswordField.setForeground(Color.white);
        repasswordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(repasswordField);

        signup = new JButton("Signup");
        signup.setFont(new Font("Inter",Font.BOLD,15));
        signup.setBounds(40,250,240,40);
        signup.setForeground(Color.white);
        signup.setBackground(new Color(29, 27, 38));
        signup.setFocusable(false);

        signupPanel.add(signup);

        login = new JButton("Login");
        login.setFont(new Font("Inter",Font.BOLD,15));
        login.setBounds(40,310,240,30);
        login.setForeground(Color.white);
        login.setBackground(getForeground());
        login.setFocusable(false);
        login.addActionListener(this);
        signupPanel.add(login);

        setTitle("signup");
        setVisible(true);
        setSize(800,500);
        setLocation(100, 100);
        getContentPane().setBackground(new Color(17, 8, 62));

    }


    public void actionPerformed(ActionEvent ae){


        //password fields validation
        if(ae.getSource()==signup){
            email.setText("");
        
        }
    }
    
    public static void main(String[] args) {
        new signup();
    }
}