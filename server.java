import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class server extends JFrame{
    server(){
        setLayout(null);
        
        JPanel headPanel = new JPanel();
        headPanel.setBounds(310,20,460,120);
        headPanel.setBackground(new Color(29, 27, 38));
        add(headPanel);
        headPanel.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,280,500);
        leftPanel.setBackground(new Color(29, 27, 38));
        add(leftPanel);
        leftPanel.setLayout(null);

        ImageIcon search = new ImageIcon("img//search.png");
        Image i1 = search.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(i1);
        JLabel searchIcon = new JLabel(image);
        searchIcon.setBounds(0,0,81,77);
        leftPanel.add(searchIcon);

        ImageIcon call  = new ImageIcon("img//call.png");
        Image i2 = call.getImage().getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE);
        ImageIcon callResized = new ImageIcon(i2);
        JLabel callIcon = new JLabel(callResized);
        callIcon.setBounds(280,0,81,77);
        headPanel.add(callIcon);

        ImageIcon videocall = new ImageIcon("img//videocall.png");
        Image i3 = videocall.getImage().getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE);
        ImageIcon videocallResized = new ImageIcon(i3);
        JLabel videocallIcon = new JLabel(videocallResized);
        videocallIcon.setBounds(320,0,81,77);
        headPanel.add(videocallIcon);

        ImageIcon history = new ImageIcon("img//history.png");
        Image i4 = history.getImage().getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE);
        ImageIcon historyResized = new ImageIcon(i4);
        JLabel historyIcon = new JLabel(historyResized);
        historyIcon.setBounds(370,0,81,77);
        headPanel.add(historyIcon);

        ImageIcon active = new ImageIcon("img//active.png");
        Image i5 = active.getImage().getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE);
        ImageIcon activeResized = new ImageIcon(i5);
        JLabel activestatus = new JLabel(activeResized);
        activestatus.setBounds(400,0,81,77);
        headPanel.add(activestatus);

        JLabel messages = new JLabel("Messages");
        messages.setFont(new Font("Inter",Font.BOLD,30));
        messages.setForeground(Color.WHITE);
        messages.setBounds(70,10,160,50);
        leftPanel.add(messages);

        setTitle("CHAT");
        setResizable(false);
        setVisible(true);
        setSize(800,500);
        setLocation(350, 100);
        getContentPane().setBackground(new Color(4,4,30));
    }
    public static void main(String[] args) {
        new server();
    }
}