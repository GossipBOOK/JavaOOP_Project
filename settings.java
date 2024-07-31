import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class settings {
    String name;
    static String email;
    byte[] imageData;
    static JFrame f = new JFrame();

// Constructor 
    public settings(String email) {
        this.email = email;
        fetchData();

        f.setLayout(null);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.setBackground(new Color(16, 29, 112));
        p1.setBounds(0,0,450,600);
        f.add(p1);

        p2.setBackground(new Color( 33, 35, 54));
        p2.setBounds(450,0,450,600);
        f.add(p2);


     // Fetching and Placing image data
        if (imageData != null) {
            try {
                InputStream in = new ByteArrayInputStream(imageData);
                BufferedImage img = ImageIO.read(in);
    
                // Create a circular version of the image
                int diameter = 150;
                BufferedImage circularImg = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = circularImg.createGraphics();
    
                // Enable anti-aliasing
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
                // Draw the circular clip
                Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);
                g2.setClip(clip);
    
                // Draw the image inside the circular clip
                g2.drawImage(img, 0, 0, diameter, diameter, null);
    
                // Dispose the graphics context
                g2.dispose();
    
                ImageIcon imageIcon = new ImageIcon(circularImg);
                JLabel profile = new JLabel(imageIcon);
                profile.setBounds(150, 20, diameter, diameter);
                p1.add(profile);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Buttons
        p1.setLayout(null);

        JButton accountSettingBtn = new JButton("Account Setting");
        accountSettingBtn.setBounds(80, 220, 290, 60);
        accountSettingBtn.setForeground(Color.WHITE);
        // accountSettingBtn.addActionListener(this);
        accountSettingBtn.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        accountSettingBtn.setFocusable(false);
        accountSettingBtn.setBackground(new Color(9,4,48));
        p1.add(accountSettingBtn);

        JButton profileBtn = new JButton("Profile");
        profileBtn.setBounds(80, 300, 290, 60);
        profileBtn.setBackground(new Color(7, 109, 242));
        profileBtn.setForeground(Color.WHITE);
        // profileBtn.addActionListener(this);
        profileBtn.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        profileBtn.setFocusable(false);
        p1.add(profileBtn);

        JButton securityBtn = new JButton("Security");
        securityBtn.setBounds(80, 380, 290, 60);
        // securityBtn.setBackground(new Color(7, 94, 84));
        securityBtn.setForeground(Color.WHITE);
        // securityBtn.addActionListener(this);
        securityBtn.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        securityBtn.setFocusable(false);
        securityBtn.setBackground(new Color(9,4,48));
        p1.add(securityBtn);


    // Adding Profile details to the right Pane
        p2.setLayout(null);

        JLabel emailLabel = new JLabel();
        emailLabel.setBounds(110, 100, 500, 18);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        emailLabel.setText("Email: "+email);
        p2.add(emailLabel);

        JLabel username = new JLabel();
        username.setBounds(110, 150, 500, 18);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        username.setText("Name: "+name);
        p2.add(username);

// Main window geometry
        f.setResizable(false);
        f.setSize(900,600);
        f.setLocation(800,100);
        f.setUndecorated(false);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

        
    }

// Db part to fetch required data and assigning to instance variables
    void fetchData() {
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
    }


    public static void main(String[] args) {
        new settings(email);
    }
}
