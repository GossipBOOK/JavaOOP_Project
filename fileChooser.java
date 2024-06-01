import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class fileChooser {
    public String chooseFile() {
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }
    }


