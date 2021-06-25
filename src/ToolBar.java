import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    public static final String basePath = "../pic/";
    public static final String[] tools = {
            basePath + "airplane.gif",
            basePath + "ambulance.gif",
            basePath + "canopy.gif",
            basePath + "hospital.gif"
    };
    public ToolBar() {
        setLayout(new GridLayout(0, 2));
        JLabel picLabel = new JLabel(new ImageIcon("../pic/map.png"));
        add(picLabel);
    }
}
