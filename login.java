import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;

public class login extends JFrame implements ActionListener{

    JPanel loginPanel;
    JButton loginButton,ForgotPassword,create;
    JTextField emailField;
    JPasswordField passwordField;
    JLabel showPassword,hidePassword;
    ImageIcon i1,i2;

    login(){

        setLayout(null);

        loginPanel = new JPanel();
        loginPanel.setBounds(400,60,320,360);
        loginPanel.setBackground(new Color(16, 16, 62));
        add(loginPanel);
        
        loginPanel.setLayout(null);

        i1 = new ImageIcon("img//show.png");
        showPassword = new JLabel(i1);
        showPassword.setBounds(270,200,60,50);
        showPassword.setBackground(new Color(29, 27, 38));
        showPassword.setFocusable(false);
        loginPanel.add(showPassword);

        i2 = new ImageIcon("img//hide.png");
        hidePassword = new JLabel(i2);
        hidePassword.setBounds(270,200,60,50);
        hidePassword.setBackground(new Color(29, 27, 38));
        hidePassword.setFocusable(false);
        loginPanel.add(hidePassword);
        hidePassword.setVisible(false);

        showPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
                showPassword.setVisible(false);
                hidePassword.setVisible(true);
            }
        });

        hidePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar('\u2022');
                showPassword.setVisible(true);
                hidePassword.setVisible(false);
            }
        });

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

        JLabel head = new JLabel("LOGIN");
        head.setFont(new Font("Serif",Font.PLAIN,25));
        head.setBounds(110,20,100,50);
        head.setForeground(Color.WHITE);
        loginPanel.add(head);

        emailField = new JTextField("Enter your email address");
        emailField.setFont(new Font("Inria Serif",Font.BOLD,15));
        emailField.setBounds(40,90,240,40);
        emailField.setForeground(Color.gray);
        emailField.setBackground(new Color(29, 27, 38));
        loginPanel.add(emailField);
        
        emailField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (emailField.getText().equals("Enter your email address")) {
                    emailField.setText("");
                    emailField.setForeground(Color.white);
                    }
            }
        });

        passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Inria Serif",Font.BOLD,18));
        passwordField.setBounds(40,150,240,40);
        passwordField.setForeground(Color.gray);
        passwordField.setBackground(new Color(29, 27, 38));
        loginPanel.add(passwordField);

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.white);
                    }
            }
        });

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Inria Serif",Font.BOLD,15));
        loginButton.setBounds(40,210,240,40);
        loginButton.setForeground(Color.white);
        loginButton.setBackground(new Color(29, 27, 38));
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        ForgotPassword = new JButton("Forgot Password?");
        ForgotPassword.setFont(new Font("Inria Serif",Font.BOLD,15));
        ForgotPassword.setBounds(40,260,240,30);
        ForgotPassword.setBorderPainted(false);
        ForgotPassword.setForeground(Color.white);
        ForgotPassword.setBackground(getForeground());
        ForgotPassword.setFocusable(false);
        ForgotPassword.addActionListener(this);
        loginPanel.add(ForgotPassword);

        create = new JButton("Create New Account");
        create.setFont(new Font("Inria Serif",Font.BOLD,15));
        create.setBounds(40,310,240,30);
        create.setForeground(Color.white);
        create.setBackground(getForeground());
        create.setFocusable(false);
        create.addActionListener(this);
        loginPanel.add(create);

        setTitle("Login");
        setVisible(true);
        setSize(800,500);
        setLocation(400, 200);
        getContentPane().setBackground(new Color(17, 8, 62));

    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==create){
            setVisible(false);
            new signupOne().setVisible(true);;
    
        }

        else if(ae.getSource()==ForgotPassword){
            setVisible(false);
            new email().setVisible(true);;
        }

        else if(ae.getSource()==loginButton){
            conn conn = new conn();

            String email = emailField.getText();
            String password = passwordField.getText();
            String query = "select * from credentials where email='"+email+"' and password ='"+password+"'";

            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "You are logged in");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect credentials");
                }

            }catch(Exception e){
                System.out.println(e);

            }

        }
    }
    
    public static void main(String[] args) {
        new login();
    }

 
}