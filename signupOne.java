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




public class signupOne extends JFrame implements ActionListener{
    JTextField emailField;
    JPasswordField passwordField;
    JPasswordField repasswordField ;
    JButton signup,login;
    signupOne(){
        
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


        emailField = new JTextField("Enter your email address");
        emailField.setFont(new Font("Inria Serif",Font.BOLD,15));
        emailField.setBounds(40,90,240,40);
        emailField.setForeground(Color.white);
        emailField.setBackground(new Color(29, 27, 38));
        signupPanel.add(emailField);

        passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Inria Serif",Font.BOLD,20));
        passwordField.setBounds(40,150,240,40);
        passwordField.setForeground(Color.white);
        passwordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(passwordField);

        
        repasswordField = new JPasswordField("Password");
        repasswordField.setFont(new Font("Inria Serif",Font.BOLD,20));
        repasswordField.setBounds(40,200,240,40);
        repasswordField.setForeground(Color.white);
        repasswordField.setBackground(new Color(29, 27, 38));
        signupPanel.add(repasswordField);

        signup = new JButton("Signup");
        signup.setFont(new Font("Inria Serif",Font.BOLD,15));
        signup.setBounds(40,250,240,40);
        signup.setForeground(Color.white);
        signup.setBackground(new Color(29, 27, 38));
        signup.setFocusable(false);
        signup.addActionListener(this);
        signupPanel.add(signup);

        login = new JButton("Login");
        login.setFont(new Font("Inria Serif",Font.BOLD,15));
        login.setBounds(40,310,240,30);
        login.setForeground(Color.white);
        login.setBackground(getForeground());
        login.setFocusable(false);
        login.addActionListener(this);
        signupPanel.add(login);

        setTitle("signup");
        setVisible(true);
        setSize(800,500);
        setLocation(400,200);
        getContentPane().setBackground(new Color(17, 8, 62));
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==login){
            setVisible(false);
            new login().setVisible(true);
        }

        else if(ae.getSource()==signup){
            conn conn = new conn();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = repasswordField.getText();

            String query1 = "Create table if not exists credentials(email varchar(255),password varchar(255))";
            String query2 = "Insert into credentials values('"+email+"','"+password+"')";

            try{
                if(email.equals("")){
                    JOptionPane.showMessageDialog(null, "Email is required");
                }

                if(password.length()<8){
                    JOptionPane.showMessageDialog(null, "Password should be at least 8 characters long");
                }
                else if(password.equals(confirmPassword) && password.length()>=8){
                    
                    conn.s.executeUpdate("USE chat");

                    
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Your email is "+email);
                    setVisible(false);
                    new login().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "The passwords don't match");
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }
    }
    
    public static void main(String[] args) {
        new signupOne();
    }
}