import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class forgotPassword extends JFrame{

    JTextField emailField;
    JPasswordField passwordField,repasswordField;
    JButton resetButton;
    JLabel showPassword,hidePassword;
    ImageIcon i1,i2;

    forgotPassword(){
        setLayout(null);

        setTitle("Reset Password");

        //setting show password icon as JLabel
        i1 = new ImageIcon("img//show.png");
        showPassword = new JLabel(i1);
        showPassword.setBounds(440,340,60,50);
        showPassword.setBackground(new Color(29, 27, 38));
        showPassword.setFocusable(false);
        add(showPassword);

        // setting hide password icon as JLabel
        i2 = new ImageIcon("img//hide.png");
        hidePassword = new JLabel(i2);

        //showPassword and hidePassword have the same setBounds to overlap them
        hidePassword.setBounds(440,340,60,50);
        hidePassword.setBackground(new Color(29, 27, 38));
        hidePassword.setFocusable(false);
        add(hidePassword);

        //when they're overlapped the hidePassword icon is hidden
        hidePassword.setVisible(false);

        //mouse press on showPassword label will show the password
        showPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
                repasswordField.setEchoChar((char) 0);
                showPassword.setVisible(false);
                hidePassword.setVisible(true);
            }
        });

        
        //mouse press on hidePassword label will hide the password
        hidePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar('\u2022');
                repasswordField.setEchoChar('\u2022');
                showPassword.setVisible(true);
                hidePassword.setVisible(false);
            }
        });
    

        JLabel reset = new JLabel("Reset your password");
        reset.setFont(new Font("Inter",Font.TRUETYPE_FONT,30));
        reset.setBounds(140,80,500,40);
        reset.setForeground(Color.WHITE);
        add(reset);

        emailField = new JTextField("Enter your email");
        emailField.setFont(new Font("Inria Serif",Font.BOLD,18));
        emailField.setBounds(140,160,300,50);
        emailField.setForeground(Color.gray);
        emailField.setBackground(new Color(29, 27, 38));
        
        add(emailField);

        //MouseListener used on the TextFields to set placeholders
        //Mouse press on emailField sets the textField content to an empty string
        emailField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (emailField.getText().equals("Enter your email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.white);
                    }
            }
        });

        passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Inria Serif",Font.BOLD,18));
        passwordField.setBounds(140,250,300,50);
        passwordField.setForeground(Color.gray);
        passwordField.setBackground(new Color(29, 27, 38));

        add(passwordField);

        //Mouse press on passwordField sets the textField content to null
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
        repasswordField.setFont(new Font("Inria Serif",Font.BOLD,18));
        repasswordField.setBounds(140,340,300,50);
        repasswordField.setForeground(Color.gray);
        repasswordField.setBackground(new Color(29, 27, 38));
   
        add(repasswordField);

        //Mouse press on repasswordField sets the textField content to null
        repasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (repasswordField.getText().equals("Retype Password")) {
                    repasswordField.setText("");
                    repasswordField.setForeground(Color.white);
                    }
            }
        });

        resetButton = new JButton("Reset Password");
        resetButton.setFont(new Font("Inria Serif",Font.BOLD,18));
        resetButton.setBounds(200,420,200,50);
        resetButton.setForeground(Color.white);
        resetButton.setBackground(new Color(29, 27, 38));
        add(resetButton);


        setBounds(450,200,600,600);
        setResizable(false);
        getContentPane().setBackground(new Color(22,22,29));
        setVisible(true);

    }

    public static void main(String[] args) {
        new forgotPassword();
    }

}