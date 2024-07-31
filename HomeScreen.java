import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeScreen extends JFrame implements ActionListener {
    private JList<ChatItem> chatList;
    private DefaultListModel<ChatItem> chatModel;
    static String email;

    public HomeScreen(String email) {
        this.email = email;
        // Set up the main frame
        setTitle("GossipBook");
        setSize(400, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set up the top panel with the WhatsApp logo
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 400, 60);
        topPanel.setBackground(new Color(18, 18, 18));
        topPanel.setLayout(null);

        // Add WhatsApp title
        JLabel titleLabel = new JLabel("GossipBook");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(20, 15, 200, 30);
        topPanel.add(titleLabel);

        add(topPanel);

        // Search Icon
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Search.png"));
        Image searchImage = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon = new ImageIcon(searchImage);
        JLabel searchLabel = new JLabel(scaledSearchIcon);
        searchLabel.setBounds(240, 20, 25, 25);
        topPanel.add(searchLabel);

        // Camera Icon
        ImageIcon cameraIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Camera.png"));
        Image cameraImage = cameraIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledCameraIcon = new ImageIcon(cameraImage);
        JLabel cameraLabel = new JLabel(scaledCameraIcon);
        cameraLabel.setBounds(300, 20, 25, 25); // Adjusted position to avoid overlap
        topPanel.add(cameraLabel);

        // 3 Icon
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image icon3Image = icon3.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon3 = new ImageIcon(icon3Image);
        JLabel icon3Label = new JLabel(scaledIcon3);
        icon3Label.setBounds(360, 20, 10, 25); // Adjusted position to avoid overlap
        topPanel.add(icon3Label);

        // Add tabs for All, Unread, Favourites, Groups
        JPanel tabPanel = new JPanel();
        tabPanel.setBounds(0, 60, 400, 60);
        tabPanel.setBackground(new Color(18, 18, 18));
        tabPanel.setLayout(new GridLayout(1, 4));

        String[] tabNames = {"All", "Unread", "Favourites", "Groups"};
        for (String tabName : tabNames) {
            JButton tabButton = new JButton(tabName);
            tabButton.setForeground(Color.WHITE);
            tabButton.setBackground(new Color(18, 18, 18));
            tabPanel.add(tabButton);
            tabButton.setFocusable(false);
        }

        add(tabPanel);

        // Set up the main chat list panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18, 18, 18));
        panel.setBounds(0, 120, 400, 480);

        // Create the chat list model and JList
        chatModel = new DefaultListModel<>();
        chatList = new JList<>(chatModel);
        chatList.setCellRenderer(new ChatItemRenderer());
        chatList.setBackground(new Color(18, 18, 18));
        chatList.setSelectionBackground(new Color(18, 18, 18));

        // Add a mouse listener to handle clicks
        chatList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected, open the chat window
                    int index = list.locationToIndex(evt.getPoint());
                    ChatItem item = (ChatItem) list.getModel().getElementAt(index);
                    new Client(item.getName(), item.getImage_data()).showClient();
                    setVisible(false);
                }
            }
        });

        // Fetch data from the database
        fetchChatData();

        // Add the chat list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(chatList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // Add bottom navigation panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 600, 400, 100);
        bottomPanel.setBackground(new Color(18, 18, 18));
        bottomPanel.setLayout(new GridLayout(1, 5));

        String[] navIconsPaths = {
            "chats.png",      // Path to Chats icon
            "Updates.png",    // Path to Updates icon
            "Settings.png", // Path to Communities icon
            "phone.png"       // Path to Calls icon
        };

        String[] navIcons = {"Chats", "Updates", "Settings", "Calls"};

        for (int i = 0; i < navIcons.length; i++) {
            try {
                BufferedImage iconImage = ImageIO.read(new File(navIconsPaths[i]));
                // Resize the icon
                Image resizedImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                JButton navButton = new JButton(navIcons[i], new ImageIcon(resizedImage));
                navButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                navButton.setHorizontalTextPosition(SwingConstants.CENTER);
                navButton.setForeground(Color.WHITE);
                navButton.setBackground(new Color(18, 18, 18));
                navButton.setFocusable(false);

                if (navIcons[i].equals("Settings")) {
                    navButton.addActionListener(this); // Add ActionListener only to Settings button
                }
                
                bottomPanel.add(navButton);

             
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        add(bottomPanel);
        // setUndecorated(true);
        // Make the frame visible
        setVisible(true);

    }

    private void fetchChatData() {
        String url = "jdbc:mysql://localhost:3306/chat";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, lastMessage, date, profileImage FROM chats")) {

            while (rs.next()) {
                String name = rs.getString("name");
                String lastMessage = rs.getString("lastMessage");
                String date = rs.getString("date");
                byte[] imageData = rs.getBytes("profileImage");
                chatModel.addElement(new ChatItem(name, lastMessage, date, imageData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeScreen(email));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new settings(email);
    }


}
