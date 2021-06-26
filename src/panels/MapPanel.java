package panels;

import components.Virus;

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
    private int timerCount = 0;
    private ArrayList<Virus> viruses = new ArrayList<Virus>();
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
        addRedVirus((int) (Math.random() * 730) + 10, (int) (Math.random() * 580) + 10);
        if (timerCount > 10){
            if (timerCount % 2 == 0)
                addYellowVirus((int) (Math.random() * 730) + 10, (int) (Math.random() * 580) + 10);
        }
        if (timerCount > 20){
            if (timerCount % 5 == 0)
                addOrangeVirus((int) (Math.random() * 730) + 10, (int) (Math.random() * 580) + 10);
        }
        timerCount++;
    }
    private void addRedVirus(int x , int y){
        Virus redVirus = new Virus("red");
        add(redVirus, Integer.valueOf(1));
        redVirus.setLocation(x,y);
        viruses.add(redVirus);
    }
    private void addOrangeVirus(int x , int y){
        Virus orangeVirus = new Virus("orange");
        add(orangeVirus, Integer.valueOf(1));
        orangeVirus.setLocation(x,y);
        viruses.add(orangeVirus);
    }
    private void addYellowVirus(int x , int y){
        Virus yellowVirus = new Virus("yellow");
        add(yellowVirus, Integer.valueOf(1));
        yellowVirus.setLocation(x,y);
        viruses.add(yellowVirus);
    }

    private void removeViruses(ArrayList<JLabel> removeList){
        for (int i = 0; i < removeList.size(); i++){
            this.remove(removeList.get(i));
        }
        viruses.removeAll(removeList);
    }

}


