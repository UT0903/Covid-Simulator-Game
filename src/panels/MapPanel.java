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

public class MapPanel extends JLayeredPane implements ActionListener {
    private static final int delay = 1000;
    private final Timer timer = new Timer(delay, this);
    private static final String basePath = "./pic/";


    public void start() {timer.start();}
    public void stop() { timer.stop(); }
    private void addRedDot(int x , int y){
        ImageIcon redDotIcon = resizeImage(basePath + "./virus/1.png", 20, 20);
        JLabel redDot = new JLabel(redDotIcon);
        redDot.setSize(20,20);
        add(redDot, Integer.valueOf(1));
        redDot.setLocation(x,y);
    }
    private ImageIcon resizeImage(String path , int width, int height){
        /*ImageIcon img = new ImageIcon(path);
        img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return img;*/
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            return imageIcon;
        } catch (IOException e) {}
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = (int) (Math.random() * 750);
        int y = (int) (Math.random() * 600);
        addRedDot(x, y);
    }


}


