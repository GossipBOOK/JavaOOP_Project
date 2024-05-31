import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class settings extends JFrame{

    JButton settings,profile,security;

    settings(){
        setLayout(null);
        setTitle("User Settings");


        settings = new JButton("Account Settings");   
        settings.setBounds(60,240,200,40); 
        settings.setFont(new Font("Sans",Font.PLAIN,18));
        settings.setForeground(Color.WHITE);
        settings.setBackground(new Color(17,8,62));
        settings.setFocusable(false);
        add(settings);

        profile = new JButton("My profile");   
        profile.setBounds(60,320,200,40); 
        profile.setFont(new Font("Sans",Font.PLAIN,18));
        profile.setForeground(Color.WHITE);
        profile.setBackground(new Color(21,54,169));
        profile.setFocusable(false);
        add(profile);

        security = new JButton("Security");   
        security.setBounds(60,400,200,40); 
        security.setFont(new Font("Sans",Font.PLAIN,18));
        security.setForeground(Color.WHITE);
        security.setBackground(new Color(17,8,62));
        security.setFocusable(false);
        add(security);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(29,27,38));
        rightPanel.setBounds(300,0,500,600);
        add(rightPanel);

        setBounds(350,100,800,600);
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(new Color(17,8,62));
    }

    public static void main(String[] args) {
        
        new settings();
    }
    
}
