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
    private ArrayList<JLabel> viruses = new ArrayList<JLabel>();
    public MapPanel() {
        setPreferredSize(new Dimension(750, 600));
//        setLocation(0, 40);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));
    }
    public void start() {timer.start();}
    public void stop() { timer.stop(); }


    @Override
    public void actionPerformed(ActionEvent e) {
        int x = (int) (Math.random() * 730) + 10;
        int y = (int) (Math.random() * 580) + 10;
        addRedVirus(x, y);
    }
    private void addRedVirus(int x , int y){
        ImageIcon redVirusIcon = resizeImage(basePath + "./virus/1.png", 20, 20);
        JLabel redVirus = new JLabel(redVirusIcon);
        redVirus.setSize(20,20);
        add(redVirus, Integer.valueOf(1));
        redVirus.setLocation(x,y);
        viruses.add(redVirus);
    }

    private void removeViruses(ArrayList<JLabel> removeList){
        for (int i = 0; i < removeList.size(); i++){
            this.remove(removeList.get(i));
        }
        viruses.removeAll(removeList);
    }

}


