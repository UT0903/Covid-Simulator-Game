import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

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
        windowFrame = new JFrame();
        mapPanel = new JPanel();
        toolbarPanel = new JPanel();
        messagePanel = new JPanel();
        infoPanel = new JPanel();
        detailPanel = new JPanel();
    }

    public void start(){
        windowInit();
        mapInit();
        toolbarInit();
        messageInit();
        infoInit();
        detailInit();
        windowFrame.add(messagePanel);
        messagePanel.setLocation(0, 0);
        windowFrame.add(mapPanel);
        mapPanel.setLocation(0, 40);
        windowFrame.add(infoPanel);
        infoPanel.setLocation(750, 0);
        windowFrame.add(toolbarPanel);
        toolbarPanel.setLocation(750, 150);
        windowFrame.add(detailPanel);
        detailPanel.setLocation(750, 470);
        windowFrame.setVisible(true);
    }

    private void windowInit(){
        windowFrame.getContentPane().setLayout(null);
        windowFrame.setSize(960, 640);
        windowFrame.setTitle("Virus Game");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void mapInit(){
        mapPanel.setSize(750, 600);
        mapPanel.setLayout(new BorderLayout());
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "map.png")); //Add background
        mapPanel.add(bgPic);
    }

    private void toolbarInit() {
        toolbarPanel.setSize(210, 320);
        toolbarPanel.setBackground(Color.yellow);
    }

    private void messageInit() {
        messagePanel.setSize(750, 40);
        messagePanel.setBackground(Color.green);
    }

    private void infoInit() {
        infoPanel.setSize(210, 150);
        infoPanel.setBackground(Color.black);
    }

    private void detailInit() {
        detailPanel.setSize(210, 170);
        detailPanel.setBackground(Color.red);

    }
}