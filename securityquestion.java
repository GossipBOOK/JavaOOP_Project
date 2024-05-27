import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class securityquestion extends JFrame{
    
    JButton continuee;
    
    securityquestion(){
        setLayout(null);
        JLabel title = new JLabel("Reset Your Password");
        title.setBounds(40,40,400,40);
        title.setFont(new Font("Inria Serif",Font.PLAIN,40));
        title.setForeground(new Color(9,6,19));
        add(title);

        JLabel label = new JLabel("Answer a simple security question:");
        label.setBounds(40,100,700,40);
        label.setFont(new Font("Inria Serif",Font.PLAIN,40));
        label.setForeground(new Color(9,6,19));
        add(label);

        JLabel SecurityQuestionLabel = new JLabel("<html>Security Question:<br>What is your favourite food?</html>");
        SecurityQuestionLabel.setBounds(40,180,600,100);
        SecurityQuestionLabel.setFont(new Font("Inria Serif",Font.PLAIN,40));
        SecurityQuestionLabel.setForeground(new Color(9,6,19));
        add(SecurityQuestionLabel);

        JTextField answer = new JTextField("Answer");
        answer.setBounds(40,300,460,60);
        answer.setFont(new Font("Inria Serif",Font.PLAIN,40));
        answer.setForeground(new Color(9,6,19));
        add(answer);

        continuee = new JButton("Continue");
        continuee.setBounds(660,370,170,50);
        continuee.setFont(new Font("Inria Serif",Font.PLAIN,30));
        continuee.setForeground(Color.WHITE);
        continuee.setBackground(new Color(55,55,149));
        continuee.setFocusable(false);
        add(continuee);
        


        setResizable(false);
        setBounds(400,250,900,500);

        getContentPane().setBackground(new Color(131,131,149));
        setVisible(true);
    }

    public static void main(String[] args) {
        
        new securityquestion().setVisible(true);
    }
}


