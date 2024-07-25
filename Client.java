import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;
public class Client implements ActionListener{
    JTextField text;
    static JPanel a1;
    JLabel back;
    static Box vertical = Box.createVerticalBox();
    static String message;
    static DataOutputStream dout;
    static JFrame f = new JFrame();
        String email;
    String name;
    byte[] imageData;
    Client(String email){
        f.setLayout(null);

        JPanel p1 = new JPanel();
        // p1.setBackground(new Color(7,94,84));
        p1.setBackground(new Color(9,41,48));
        p1.setBounds(0,0,450,70);
        f.add(p1);
        p1.setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });


        try{
            String query = "SELECT name, image_data FROM userDetails WHERE email = ?";
            conn conn = new conn();
             PreparedStatement pstmt = conn.c.prepareStatement(query);
              pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve name
                    name = rs.getString("name");
                    
                    // Retrieve image data
                    imageData = rs.getBytes("image_data");
                    
                    // Pass data to the Server class
                } else {
                    System.out.println("No record found with the provided email.");
                }
            }

             
        }
         catch (SQLException e) {
            e.printStackTrace();
        }


        if (imageData != null) {
            try {
                InputStream in = new ByteArrayInputStream(imageData);
                BufferedImage img = ImageIO.read(in);
                ImageIcon imageIcon = new ImageIcon(img);
                
                // Scale the image if needed
                Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                JLabel profile = new JLabel(scaledIcon);
                profile.setBounds(40, 10, 50, 50);
                p1.add(profile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/asrim.png"));
        // Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        // ImageIcon i6 = new ImageIcon(i5);
        // JLabel profile = new JLabel(i6);
        // profile.setBounds(40, 10, 50, 50);
        // p1.add(profile);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        
        // JLabel name = new JLabel("BCDK");
        // name.setBounds(110, 15, 100, 18);
        // name.setForeground(Color.WHITE);
        // name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        // p1.add(name);

        JLabel username = new JLabel();
        username.setBounds(110, 15, 100, 18);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        username.setText(name);
        p1.add(username);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        // send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setFocusable(false);
        send.setBackground(new Color(9,4,48));
        f.add(send);

        f.setResizable(false);
        f.setSize(450,700);
        f.setLocation(800,100);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

        new Thread(() -> {
            try {
                Socket s = new Socket("127.0.0.1", 6001); // Connect to the server
                System.out.println("Connected to server");
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);

                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical, BorderLayout.PAGE_START);

                    f.validate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static JPanel formatLabel(String text){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style= \"width: 120px\">"+text+"</p></html>");
        output.setFont(new Font(Font.MONOSPACED,Font.PLAIN,20));
        output.setBackground(new Color(29, 27, 38));
        output.setOpaque(true);
        output.setForeground(Color.white);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();

        time.setText(sdf.format(cal.getTime()));
        panel.add(time);


        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    try {
        String out = text.getText();

        JPanel p2 = formatLabel(out);

        a1.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        a1.add(vertical, BorderLayout.PAGE_START);

        dout.writeUTF(out);

        text.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public static void main(String [] args){
        new Client("");


    }
    }


