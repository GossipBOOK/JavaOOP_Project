import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;

public class signupOne extends JFrame implements ActionListener {
    int randomUserID;
    JTextField emailField;
    JPasswordField passwordField, repasswordField;
    JButton signup, login;
    JLabel showPassword, hidePassword;
    ImageIcon i1, i2,i3,i4;

    signupOne() {

        setLayout(null);
        Random random = new Random();
        randomUserID = random.nextInt(4000);

        JPanel signupPanel = new JPanel();
        signupPanel.setBounds(400, 60, 320, 360);
        signupPanel.setBackground(new Color(16, 16, 62));
        add(signupPanel);

        signupPanel.setLayout(null);

       
        i1 = new ImageIcon("img//show1.png");
        Image show = i1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        i2 = new ImageIcon(show);
        showPassword = new JLabel(i2);
        showPassword.setBounds(270,200,60,50);
        showPassword.setFocusable(false);
        signupPanel.add(showPassword);

        i3 = new ImageIcon("img//hide1.png");
        Image hide = i3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        i4 = new ImageIcon(hide);
        hidePassword = new JLabel(i4);
        hidePassword.setBounds(270,200,60,50);
        hidePassword.setFocusable(false);
        signupPanel.add(hidePassword);
        hidePassword.setVisible(false);

        showPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
                repasswordField.setEchoChar((char) 0);
                showPassword.setVisible(false);
                hidePassword.setVisible(true);
            }
        });

        hidePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar('\u2022');
                repasswordField.setEchoChar('\u2022');
                showPassword.setVisible(true);
                hidePassword.setVisible(false);
            }
        });

        JLabel GossipBook = new JLabel("Gossip Book");
        GossipBook.setFont(new Font("Inter", Font.PLAIN, 30));
        GossipBook.setBounds(50, 120, 200, 40);
        GossipBook.setForeground(Color.WHITE);
        add(GossipBook);

        JLabel text = new JLabel("<html>Connect with friend and the world <br>around you on Gossip Book.<html>");
        text.setFont(new Font("Inter", Font.PLAIN, 16));
        text.setBounds(50, 160, 400, 100);
        text.setForeground(Color.WHITE);
        add(text);

        JLabel head = new JLabel("SIGN UP");
        head.setFont(new Font("Serif", Font.PLAIN, 25));
        head.setBounds(110, 20, 100, 50);
        head.setForeground(Color.WHITE);
        signupPanel.add(head);

        emailField = new JTextField("Enter your email address");
        emailField.setFont(new Font("Inria Serif", Font.BOLD, 15));
        emailField.setBounds(40, 90, 240, 40);
        emailField.setForeground(Color.gray);
        emailField.setBackground(new Color(29, 27, 38));
        signupPanel.add(emailField);

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
        passwordField.setFont(new Font("Inria Serif", Font.BOLD, 15));
        passwordField.setBounds(40, 150, 240, 40);
        passwordField.setForeground(Color.gray);
        passwordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(passwordField);

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.white);
                }
            }
        });

        repasswordField = new JPasswordField("Retype Password");
        repasswordField.setFont(new Font("Inria Serif", Font.BOLD, 15));
        repasswordField.setBounds(40, 200, 240, 40);
        repasswordField.setForeground(Color.gray);
        repasswordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(repasswordField);

        repasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (repasswordField.getText().equals("Retype Password")) {
                    repasswordField.setText("");
                    repasswordField.setForeground(Color.white);
                }
            }
        });

        signup = new JButton("Signup");
        signup.setFont(new Font("Inria Serif", Font.BOLD, 15));
        signup.setBounds(40, 250, 240, 40);
        signup.setForeground(Color.white);
        signup.setBackground(new Color(29, 27, 38));
        signup.setFocusable(false);
        signup.addActionListener(this);
        signupPanel.add(signup);

        login = new JButton("Login");
        login.setFont(new Font("Inria Serif", Font.BOLD, 15));
        login.setBounds(40, 310, 240, 30);
        login.setForeground(Color.white);
        login.setBackground(getForeground());
        login.setFocusable(false);
        login.addActionListener(this);
        signupPanel.add(login);

        setTitle("signup");
        setVisible(true);
        setSize(800, 500);
        setResizable(false);
        setLocation(360, 150);
        getContentPane().setBackground(new Color(17, 8, 62));
    }

    public void actionPerformed(ActionEvent ae) {

        String userID = "" + randomUserID;
        conn conn = new conn();
        String email = emailField.getText();
        ArrayList <String> mail= new ArrayList<>();
            mail.add("gmail");
            mail.add("hotmail");
            mail.add("outlook");
            mail.add("yahoo");

        String password = passwordField.getText();
        String confirmPassword = repasswordField.getText();

        if (ae.getSource() == login) {
            setVisible(false);
            new login().setVisible(true);
        }
        else if (ae.getSource() == signup) {

            String query2 = "Insert into credentials values('" + userID + "','" + email + "','" + password + "')";
            try {

                if (email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Email is required");
                }

                for(String domain:mail ){
                    if(!email.contains("@"+domain+".com")){
                        JOptionPane.showMessageDialog(null,"Invalid email format");
                    }
                    break;
    
                }
                
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password should be at least 8 characters long");
                } else if (password.equals(confirmPassword) && password.length() >= 8) {
                    conn.s.executeUpdate("USE chat");
                    String query = "select * from credentials where email = '" + email + "';";
                    ResultSet rs = conn.s.executeQuery(query);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "This email is already taken");
                    } else {

                        conn.s.executeUpdate(query2);

                        JOptionPane.showMessageDialog(null, "Your email is " + email);
                        setVisible(false);
                        new myGUI(email).setVisible(true);
                    }

                } 
                else {
                    JOptionPane.showMessageDialog(null, "The passwords don't match");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
        }
    }

    public static void main(String[] args) {
        new signupOne();

    }

}

// text prompt
