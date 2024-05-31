import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class securityquestion extends JFrame implements ActionListener{
    String securityQuestion;
    JButton continuee;
    JTextField Input;
    String email;
    securityquestion(String securityQuestion,String email){

        this.securityQuestion = securityQuestion;
        this.email = email;

        setLayout(null);
        JLabel title = new JLabel("Reset Your Password");
        title.setBounds(40,40,400,40);
        title.setFont(new Font("Inria Serif",Font.PLAIN,40));
        title.setForeground(new Color(9,6,19));
        add(title);

        JLabel label = new JLabel("Answer a simple security question:");
        label.setBounds(40,100,700,50);
        label.setFont(new Font("Inria Serif",Font.PLAIN,40));
        label.setForeground(new Color(9,6,19));
        add(label);

        // JLabel SecurityQuestionLabel = new JLabel("<html>Security Question:<br>What is your favourite food?</html>");
        JLabel SecurityQuestionLabel = new JLabel(securityQuestion);
        SecurityQuestionLabel.setBounds(40,180,700,100);
        SecurityQuestionLabel.setFont(new Font("Inria Serif",Font.PLAIN,30));
        SecurityQuestionLabel.setForeground(new Color(9,6,19));
        add(SecurityQuestionLabel);

        Input = new JTextField("");
        Input.setBounds(40,300,460,60);
        Input.setFont(new Font("Inria Serif",Font.PLAIN,35));
        Input.setForeground(new Color(9,6,19));
        add(Input);

        continuee = new JButton("Continue");
        continuee.setBounds(660,370,170,50);
        continuee.setFont(new Font("Inria Serif",Font.PLAIN,30));
        continuee.setForeground(Color.WHITE);
        continuee.setBackground(new Color(55,55,149));
        continuee.setFocusable(false);
        continuee.addActionListener(this);
        add(continuee);
        
        setResizable(false);
        setBounds(400,250,900,500);

        getContentPane().setBackground(new Color(131,131,149));
        setVisible(true);
    }

    public static void main(String[] args) {
        
        new securityquestion("","").setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{

            String answer;//Answer from database

            String userInput = Input.getText();//User's answer
            
            
            conn conn = new conn();
            String query = "select * from security where security_question='"+securityQuestion+"'";
            
            ResultSet rs = conn.s.executeQuery(query);

            if(rs.next()){

                answer = rs.getString("answer");

                if(answer.equals(userInput)){
                    setVisible(false);
                    new forgotPassword(email).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect answer");
                }
            }

        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }

}


