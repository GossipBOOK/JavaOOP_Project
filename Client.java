import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.*;

public class Client implements ActionListener {
    JTextField text;
    JPanel a1;
    JLabel back;
    static Box vertical = Box.createVerticalBox();
    JFrame f = new JFrame();
    static DataOutputStream dout;
    static DataInputStream din;
    String name;
    byte[] imageData;
    JScrollPane scrollPane;

    public Client(String name, byte[] imageData) {
        this.name = name;
        this.imageData = imageData;

        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(9, 41, 48));
        p1.setBounds(0, 0, 450, 70);
        f.add(p1);
        p1.setLayout(null);

        // Back button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                new HomeScreen("").setVisible(true);
                f.setVisible(false);
            }
        });

        // Profile image
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

        // Username label
        JLabel username = new JLabel();
        username.setBounds(110, 15, 100, 18);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        username.setText(name);
        p1.add(username);

        // Status label
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        // Main chat panel
        a1 = new JPanel();
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS));
        // a1.setOpaque(false);
        a1.setBorder(null);
        
        // Add a JScrollPane
        scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Optional: set scrolling speed
        f.add(scrollPane);

        // Make the scrollbar invisible
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0, 0, 0, 0); // Transparent thumb color
                this.trackColor = new Color(0, 0, 0, 0); // Transparent track color
            }
        });

        // Text field and send button
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setFocusable(false);
        send.setBackground(new Color(9, 41, 48));
        f.add(send);

        f.setResizable(false);
        f.setSize(460, 740);
        f.setLocation(200, 100);
        // f.setUndecorated(true);
        // f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Connect to the server
        try {
            Socket socket = new Socket("127.0.0.1", 6001);
            System.out.println("Connected to server");
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            // Start a thread to listen for incoming messages
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = din.readUTF();
                        JPanel panel = formatLabel(msg);

                        JPanel left = new JPanel(new BorderLayout());
                        left.add(panel, BorderLayout.LINE_START);
                        vertical.add(left);

                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);

                        SwingUtilities.invokeLater(() -> {
                            JScrollBar vertical = scrollPane.getVerticalScrollBar();
                            vertical.setValue(vertical.getMaximum());
                        });

                        f.validate();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style= \"width: 120px\">" + text + "</p></html>");
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        output.setBackground(new Color(29, 27, 38));

        output.setOpaque(true);
        output.setForeground(Color.white);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
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

            SwingUtilities.invokeLater(() -> {
                JScrollBar vertical = scrollPane.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            });

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client("Example Name", null).showClient();
    }

    public void showClient() {
        f.setVisible(true);
    }
}
