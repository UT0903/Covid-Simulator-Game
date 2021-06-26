package panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JLayeredPane {
    private static final String basePath = "../pic/";

    public MapPanel() {
        setSize(750, 600);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));
        addRedDot(100, 200);
        addRedDot(100, 300);
        addRedDot(200, 300);
        addRedDot(300, 400);
    }

    private void addRedDot(int x, int y) {
        JLabel redDot = new JLabel();
        redDot.setOpaque(true);
        redDot.setBackground(Color.RED);
        redDot.setSize(10, 10);
        add(redDot, Integer.valueOf(1));
        redDot.setLocation(x, y);
    }
}


