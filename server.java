import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


// new

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// new
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class server extends JFrame{
    JButton send_btn;
    JTextField client_input;
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

        //new
        JPanel message_pane = new JPanel();
        message_pane.setBounds(310,380,460,60);
        message_pane.setBackground(new Color(29, 27, 38));
        add(message_pane);

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
        message_pane.add(send_btn);

        //new

        ImageIcon search = new ImageIcon("img//search.png");
        Image i1 = search.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
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
        setVisible(true);
        setSize(800,500);
        setLocation(100, 100);
        getContentPane().setBackground(new Color(4,4,30));
    }

    void sendMsg() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            String str="", str1="";
            while(!str.equals("stop")){
                str = dis.readUTF();
                System.out.println("Client: "+str);
                str1=br.readLine();
                dout.writeUTF(str1);
                dout.flush();
            }

            dis.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new server();
    }
}