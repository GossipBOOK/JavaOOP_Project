import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ChatItemRenderer extends JPanel implements ListCellRenderer<ChatItem> {
    private JLabel nameLabel;
    private JLabel messageLabel;
    private JLabel dateLabel;
    private JLabel profilePicLabel;

    public ChatItemRenderer() {
        setLayout(new BorderLayout());
        setBackground(new Color(18, 18, 18));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        profilePicLabel = new JLabel();
        nameLabel = new JLabel();
        messageLabel = new JLabel();
        dateLabel = new JLabel();

        nameLabel.setForeground(Color.WHITE);
        messageLabel.setForeground(Color.GRAY);
        dateLabel.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.setBackground(new Color(18, 18, 18));
        textPanel.add(nameLabel);
        textPanel.add(messageLabel);

        add(profilePicLabel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(dateLabel, BorderLayout.EAST);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends ChatItem> list, ChatItem value, int index, boolean isSelected, boolean cellHasFocus) {
        nameLabel.setText(value.getName());
        messageLabel.setText(value.getMessage());
        dateLabel.setText(value.getDate());

        if (value.getImage_data() != null) {
            try {
                InputStream in = new ByteArrayInputStream(value.getImage_data());
                BufferedImage profilePic = ImageIO.read(in);
                Image scaledImage = profilePic.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                profilePicLabel.setIcon(new ImageIcon(scaledImage));
            } catch (IOException e) {
                e.printStackTrace();
                profilePicLabel.setIcon(null);
            }
        } else {
            profilePicLabel.setIcon(null);
        }

        if (isSelected) {
            setBackground(new Color(29, 29, 29));
        } else {
            setBackground(new Color(18, 18, 18));
        }

        return this;
    }
}
