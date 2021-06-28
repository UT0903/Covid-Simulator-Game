package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static utils.Utils.basePath;
import static utils.Utils.resizeImage;

public class MenuPanel extends JLayeredPane {
//    private JButton startButton = new JButton("Start game!");
    public MenuPanel(ActionListener startListener){
        setName("MenuPanel");
        setPreferredSize(new Dimension(1080, 640));
        JLabel bgPic = new JLabel(resizeImage(basePath + "./start.png", 1080, 640)); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(1080, 640);
        add(bgPic, Integer.valueOf(0));
        JButton startButton = new JButton("Start game!");
        startButton.setSize(120, 30);
        startButton.setLocation(650, 200);
        startButton.addActionListener(startListener);
        add(startButton, Integer.valueOf(1));
    }

//    public JButton getStartButton() {
//        return startButton;
//    }
}
