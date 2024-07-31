import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatWindow extends JFrame implements ActionListener {
    private String chatName;
    private JTextField text;
    private static JPanel chatPanel;
    private static Box vertical = Box.createVerticalBox();
    private static DataOutputStream dout;

    public ChatWindow(String chatName) {
        this.chatName = chatName;

        setTitle(chatName);
        setSize(450, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(new Color(9, 41, 48));
        header.setBounds(0, 0, 450, 70);
        header.setLayout(null);
        add(header);

        JLabel nameLabel = new JLabel(chatName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        nameLabel.setBounds(40, 15, 100, 18);
        header.add(nameLabel);

        chatPanel = new JPanel();
        chatPanel.setBounds(5, 75, 440, 570);
        add(chatPanel);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setFocusable(false);
        send.setBackground(new Color(9, 41, 48));
        send.addActionListener(this);
        add(send);

        // Establish the connection and set up input/output streams
        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                chatPanel.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                chatPanel.add(vertical, BorderLayout.PAGE_START);

                validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static JPanel formatLabel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style= \"width: 120px\">" + text + "</p></html>");
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        output.setBackground(new Color(29, 27, 38));
        output.setOpaque(true);
        output.setForeground(Color.white);
        output.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
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

            chatPanel.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            chatPanel.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            text.setText("");

            repaint();
            invalidate();
            validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
