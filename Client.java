import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// For socket
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
// new
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Client implements ActionListener{
    JButton send_btn;
    JTextField client_input;
    JPanel msg1;

    static JFrame f = new JFrame();

    static Box vertical = Box.createVerticalBox();
    Client(){
        f.setLayout(null);
        
        JPanel headPanel = new JPanel();
        headPanel.setBounds(310,20,460,70);
        headPanel.setBackground(new Color(29, 27, 38));
        f.add(headPanel);
        headPanel.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0,0,280,500);
        leftPanel.setBackground(new Color(29, 27, 38));
        f.add(leftPanel);
        leftPanel.setLayout(null);

        msg1 = new JPanel();
        msg1.setBounds(310,100,460,270);
        msg1.setBackground(new Color(29, 27, 38));
        f.add(msg1);

        //new
        JPanel message_pane = new JPanel();
        message_pane.setBounds(310,380,460,60);
        message_pane.setBackground(new Color(29, 27, 38));
        f.add(message_pane);

        client_input = new JTextField("Enter your message here ...       ");
        client_input.setFont(new Font("Sans Serif",Font.BOLD,15));
        client_input.setBounds(10,10,400,40);
        client_input.setForeground(Color.gray);
        client_input.setBackground(new Color(29, 27, 38));
        message_pane.add(client_input);

        client_input.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (client_input.getText().equals("Enter your message here ...       ")) {
                    client_input.setText("");
                    client_input.setForeground(Color.white);
                    }
            }
        });

        send_btn = new JButton("Send");
        send_btn.setFont(new Font("Inria Serif",Font.BOLD,15));
        send_btn.setBounds(40,210,240,40);
        send_btn.setForeground(Color.white);
        send_btn.setBackground(new Color(29, 27, 38));
        send_btn.setFocusable(false);
        send_btn.addActionListener(this);
        message_pane.add(send_btn);


        //new

        ImageIcon search = new ImageIcon("img//search.png");
        Image i1 = search.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i1);
        JLabel searchIcon = new JLabel(image);
        searchIcon.setBounds(0,0,81,77);
        leftPanel.add(searchIcon);

        ImageIcon call  = new ImageIcon("img//call.png");
        Image i2 = call.getImage().getScaledInstance(30, 30, f.DO_NOTHING_ON_CLOSE);
        ImageIcon callResized = new ImageIcon(i2);
        JLabel callIcon = new JLabel(callResized);
        callIcon.setBounds(280,0,81,77);
        headPanel.add(callIcon);

        ImageIcon videocall = new ImageIcon("img//videocall.png");
        Image i3 = videocall.getImage().getScaledInstance(30, 30, f.DO_NOTHING_ON_CLOSE);
        ImageIcon videocallResized = new ImageIcon(i3);
        JLabel videocallIcon = new JLabel(videocallResized);
        videocallIcon.setBounds(320,0,81,77);
        headPanel.add(videocallIcon);

        ImageIcon history = new ImageIcon("img//history.png");
        Image i4 = history.getImage().getScaledInstance(30, 30, f.DO_NOTHING_ON_CLOSE);
        ImageIcon historyResized = new ImageIcon(i4);
        JLabel historyIcon = new JLabel(historyResized);
        historyIcon.setBounds(370,0,81,77);
        headPanel.add(historyIcon);

        ImageIcon active = new ImageIcon("img//active.png");
        Image i5 = active.getImage().getScaledInstance(20, 20, f.DO_NOTHING_ON_CLOSE);
        ImageIcon activeResized = new ImageIcon(i5);
        JLabel activestatus = new JLabel(activeResized);
        activestatus.setBounds(400,0,81,77);
        headPanel.add(activestatus);

        JLabel messages = new JLabel("Messages");
        messages.setFont(new Font("Inter",Font.BOLD,30));
        messages.setForeground(Color.WHITE);
        messages.setBounds(70,10,160,50);
        leftPanel.add(messages);

        f.setTitle("CHAT");
        f.setVisible(true);
        f.setSize(800,500);
        f.setLocation(100, 100);
        f.getContentPane().setBackground(new Color(4,4,30));
   
    }

    public JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // creates box layout and stack items vertically

        JLabel output = new JLabel();
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        output.setForeground(new Color(255,255,255));
        panel.add(output);

        // For the time of message
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    

    public static void main(String[] args) {
        new Client();

        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String out = client_input.getText();
        JPanel p1 = formatLabel(out); // also adds time to message

        msg1.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p1, BorderLayout.LINE_END);

        // Creates box to stack messages vertically
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(10));

        msg1.add(vertical, BorderLayout.PAGE_START);

        // now after pasting in the client screen to send the msg
        

        client_input.setText("");

        f.repaint();
        f.invalidate();
        f.validate();



    }
}
