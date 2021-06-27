package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class MenuPanel extends JLayeredPane {
//    private JButton startButton = new JButton("Start game!");
    public MenuPanel(ActionListener startListener){
        setName("MenuPanel");
        setPreferredSize(new Dimension(960, 640));
        JLabel bgPic = new JLabel(resizeImage(basePath + "./start.png", 960, 640)); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(960, 640);
        add(bgPic, Integer.valueOf(0));
        JButton startButton = new JButton("Start game!");
        startButton.setSize(120, 30);
        startButton.setLocation(580, 200);
        startButton.addActionListener(startListener);
        add(startButton, Integer.valueOf(1));
    }

//    public JButton getStartButton() {
//        return startButton;
//    }
}
