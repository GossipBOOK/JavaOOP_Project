import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class forgotPassword extends JFrame implements ActionListener{

    JTextField emailField;
    JPasswordField passwordField,repasswordField;
    JButton resetButton;
    JLabel showPassword,hidePassword;
    ImageIcon i1,i2,i3,i4;
    String email;

    forgotPassword(String email){
        this.email = email;

        setLayout(null);

        setTitle("Reset Password");

        //setting show password icon as JLabel
        i1 = new ImageIcon("img//show1.png");
        Image show = i1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        i2 = new ImageIcon(show);
        showPassword = new JLabel(i2);
        showPassword.setBounds(440,340,60,50);
        showPassword.setBackground(new Color(29, 27, 38));
        showPassword.setFocusable(false);
        add(showPassword);

        // setting hide password icon as JLabel
        i3 = new ImageIcon("img//hide1.png");
        Image hide = i3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        i4 = new ImageIcon(hide);
        hidePassword = new JLabel(i4);

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
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        add(resetButton);


        setBounds(450,100,600,600);
        setResizable(false);
        getContentPane().setBackground(new Color(22,22,29));
        setVisible(true);

    }

    public static void main(String[] args) {
        new forgotPassword("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try{

                String newpass= passwordField.getText();
                String repass = repasswordField.getText();
               
               if(!newpass.equals(repass)){
                   JOptionPane.showMessageDialog(null,"Entered passwords don't match");
                   return;
                }

                if(newpass.equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter new Password");
                    return;
                }
                
                if(repass.equals("")){
                    JOptionPane.showMessageDialog(null,"Please re-enter new Password");
                    return;
                }

                if(newpass.length()<8){
                    JOptionPane.showMessageDialog(null, "Password should contain at least 8 characters");
                    return;
                }

            conn conn = new conn();

            String query = "update credentials set password='"+newpass+"' where email= '"+email+"'";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Successfully updated");
            setVisible(false);
            new login().setVisible(true);


        }catch(Exception e1){
            System.out.println(e1);
        }
    }

}
