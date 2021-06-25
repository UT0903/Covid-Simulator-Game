import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFlow {
    private JPanel mapPanel, toolbarPanel, messagePanel, infoPanel, detailPanel;
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
        messagePanel = new JPanel();
        infoPanel = new JPanel();
        detailPanel = new JPanel();
        windowFrame = new JFrame();
    }
    public void start(){
        windowFrame.getContentPane().setLayout(null);
        windowFrame.setSize(960, 640);
        messagePanel.setSize(800, 40);
        mapPanel.setSize(800, 600);
        infoPanel.setSize(160, 100);
        toolbarPanel.setSize(160, 400);
        detailPanel.setSize(160, 140);
        mapPanel.setLayout(new BorderLayout());
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "map.png"));
        mapPanel.add(bgPic);

        // Adding color just for testing
        messagePanel.setBackground(Color.green);
        infoPanel.setBackground(Color.black);
        toolbarPanel.setBackground(Color.yellow);
        detailPanel.setBackground(Color.red);

        windowFrame.add(messagePanel);
        messagePanel.setLocation(0, 0);
        windowFrame.add(infoPanel);
        infoPanel.setLocation(800, 0);
        windowFrame.add(mapPanel);
        mapPanel.setLocation(0, 40);
        windowFrame.add(toolbarPanel);
        toolbarPanel.setLocation(800, 100);
        windowFrame.add(detailPanel);
        detailPanel.setLocation(800, 500);
        windowFrame.setVisible(true);
        //map.setTitle("Virus Game");
        //map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //GameBody gameBody = new GameBody();
        //frame.add(gameBody, BorderLayout.CENTER);
        //frame.setVisible(true);
    }
}