import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFlow {
    private JPanel mapPanel, toolbarPanel;
    private JFrame windowFrame;
    private static final String basePath = "../pic/";
    private static final String[] tools = {
            basePath + "airplane.gif",
            basePath + "ambulance.gif",
            basePath + "canopy.gif",
            basePath + "hospital.gif"
    };
    public GameFlow(){
        mapPanel = new JPanel();
        toolbarPanel = new JPanel();
        windowFrame = new JFrame();
    }
    public void start(){
        windowFrame.setSize(960, 640);
        mapPanel.setSize(960, 640);
        mapPanel.setLayout(new BorderLayout());
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "map.png"));
        mapPanel.add(bgPic);



        windowFrame.add(mapPanel);
        windowFrame.setVisible(true);
        //map.setTitle("Virus Game");
        //map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //GameBody gameBody = new GameBody();
        //frame.add(gameBody, BorderLayout.CENTER);
        //frame.setVisible(true);
    }
}