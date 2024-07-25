import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

public class userDetails extends JFrame {

    JLabel image;
    JTextField nameField;
    JButton next;
    String selectedFilePath;
    String email;

    userDetails(String email) {
        this.email = email;

        setTitle("Add a profile picture");
        setLayout(null);

        ImageIcon i1 = new ImageIcon("img//default.png");
        Image resize = i1.getImage().getScaledInstance(192, 192, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(resize);
        image = new JLabel(resizedImage);
        image.setBounds(170, 150, 230, 200);
        add(image);

        image.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // instantiating the fileChooser public class
                fileChooser fileChooser = new fileChooser(); // fileChooser Object

                selectedFilePath = fileChooser.chooseFile(); // Invoke the chooseFile() method of fileChooser class
                image.setVisible(false);
                ImageIcon i2 = new ImageIcon(selectedFilePath);
                Image resize = i2.getImage().getScaledInstance(192, 192, Image.SCALE_SMOOTH);
                ImageIcon resizedImage = new ImageIcon(resize);
                image = new JLabel(resizedImage);
                image.setBounds(170, 150, 230, 200);
                add(image);
            }
        });

        JLabel head = new JLabel("Add a profile picture");
        head.setBounds(190, 110, 400, 40);
        head.setFont(new Font("Roboto", Font.BOLD, 18));
        head.setForeground(Color.white);
        add(head);

        JLabel nameLabel = new JLabel("What should your friends call you?");
        nameLabel.setBounds(130, 400, 400, 40);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        nameLabel.setForeground(Color.white);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 440, 300, 40);
        nameField.setFont(new Font("Roboto", Font.ITALIC, 18));
        nameField.setForeground(Color.gray);
        nameField.setBackground(new Color(29, 27, 38));
        add(nameField);

        next = new JButton("Next");
        next.setBounds(460, 500, 100, 40);
        next.setFont(new Font("Roboto", Font.ITALIC, 18));
        next.setForeground(Color.gray);
        next.setBackground(new Color(29, 27, 38));
        next.setFocusable(false);
        add(next);

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub

                // To display this image in the chat app have to add this query
                // String query = "Select image_data from userDetails where image_id = ";

                String name = nameField.getText();
                String query = "create table if not exists userDetails( image_id int PRIMARY key AUTO_INCREMENT, email varchar(255), name varchar(255), image_data LONGBLOB not null, upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
                String query1 = "Insert into userDetails(email,name,image_data) VALUES (?,?,?)";
                try {

                    conn conn = new conn();
                    conn.s.executeUpdate(query);
                    
                    FileInputStream file = new FileInputStream(selectedFilePath);
                    byte[] imageData = new byte[file.available()];
                    file.read(imageData);
                    PreparedStatement pstmt = conn.c.prepareStatement(query1);
                    pstmt.setBytes(3, imageData);
                    pstmt.setString(1, email);
                    pstmt.setString(2, name);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Image inserted successfully");
                        setVisible(false);
                        new myGUI(email).setVisible(true);
                    } else {
                        System.out.println("Image insertion unsuccessful");
                    }

                    

                }

                catch (SQLException e1) {
                    System.out.println(e1);
                } catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(null,
                            e2 + " If you don't want to add a picture you can add it later in the app");
                } // pending feature

                catch (IOException e3) {
                    throw new RuntimeException(e3);
                }

            }

        });

        getContentPane().setBackground(new Color(29, 27, 38));
        setBounds(500, 160, 600, 600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new userDetails("");
    }

}
