import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;


public class email extends JFrame implements ActionListener{

    JTextField emailField;
    JPasswordField passwordField,repasswordField;
    JButton Continue;
    JLabel showPassword,hidePassword;
    ImageIcon i1,i2;

    email(){
        setLayout(null);

        setTitle("Reset Password");

        JLabel reset = new JLabel("Reset your password");
        reset.setFont(new Font("Inter",Font.TRUETYPE_FONT,30));
        reset.setBounds(140,80,500,40);
        reset.setForeground(Color.WHITE);
        add(reset);

        emailField = new JTextField("Enter your email");
        emailField.setFont(new Font("Inria Serif",Font.BOLD,18));
        emailField.setBounds(140,240,300,50);
        emailField.setForeground(Color.gray);
        emailField.setBackground(new Color(29, 27, 38));
        
        add(emailField);

        // MouseListener used on the TextFields to set placeholders
        // Mouse press on emailField sets the textField content to an empty string
        emailField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (emailField.getText().equals("Enter your email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.white);
                    }
            }
        });

        Continue = new JButton("Continue");
        Continue.setFont(new Font("Inria Serif",Font.BOLD,18));
        Continue.setBounds(200,300,200,50);
        Continue.setForeground(Color.white);
        Continue.setBackground(new Color(29, 27, 38));
        Continue.setFocusable(false);
        Continue.addActionListener(this);
        add(Continue);


        setBounds(450,200,600,400);
        setResizable(false);
        getContentPane().setBackground(new Color(22,22,29));
        setVisible(true);
    }

    public static void main(String[] args) {
        new email();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try{

            String email = emailField.getText();
            String securityQuestion;
            conn conn = new conn();
            String query = "Select * from security where email='"+email+"'";
            ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    securityQuestion = rs.getString("security_question");
                    // JOptionPane.showMessageDialog(null,securityQuestion);
                    setVisible(false);
                    new securityquestion(securityQuestion,email).setVisible(true);
                }

                else{
                    System.out.println("Email does not exist.");
                }

        }
        catch(Exception e1){
            System.out.println(e1);
        }


    }

}


