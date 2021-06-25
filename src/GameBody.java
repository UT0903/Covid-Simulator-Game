import javax.swing.*;
import java.awt.*;

public class GameBody extends JPanel {
    public GameBody() {
        setLayout(new FlowLayout());
        ImageIcon backgroundPicture = new ImageIcon("../pic/map.png");
        JLabel picLabel = new JLabel(backgroundPicture);
        add(picLabel);
    }
}