package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class MapPanel extends JLayeredPane implements ActionListener {
    private static final int delay = 1000;
    private final Timer timer = new Timer(delay, this);

    public MapPanel() {
        setSize(750, 600);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));
    }
    public void start() {timer.start();}
    public void stop() { timer.stop(); }
    private void addRedDot(int x , int y){
        ImageIcon redDotIcon = resizeImage(basePath + "./virus/1.png", 20, 20);
        JLabel redDot = new JLabel(redDotIcon);
        redDot.setSize(20,20);
        add(redDot, Integer.valueOf(1));
        redDot.setLocation(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = (int) (Math.random() * 750);
        int y = (int) (Math.random() * 600);
        addRedDot(x, y);
    }

}


