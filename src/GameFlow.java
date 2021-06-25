import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import panels.MapPanel;
import panels.MarqueePanel;

public class GameFlow {
    private JPanel  toolbarPanel, infoPanel, detailPanel;
    private JLayeredPane mapPanel;
    private MarqueePanel messagePanel;
    private JFrame windowFrame;
    private static final String basePath = "./pic/";
    private static final String[] tools = {
            basePath + "airplane.gif",
            basePath + "ambulance.gif",
            basePath + "canopy.gif",
            basePath + "hospital.gif"
    };
    public GameFlow(){
        windowFrame = new JFrame();
        mapPanel = new MapPanel();
        toolbarPanel = new JPanel();
        messagePanel = new MarqueePanel(32);
        infoPanel = new JPanel();
        detailPanel = new JPanel();
    }

    public void start() {
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
        showMessage();
    }

    private void windowInit(){
        windowFrame.getContentPane().setLayout(null);
        windowFrame.setSize(960, 640);
        windowFrame.setTitle("Virus Game");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void mapInit(){
        mapPanel.setSize(750, 600);
    }

    private void toolbarInit() {
        toolbarPanel.setSize(210, 320);
        toolbarPanel.setBackground(Color.yellow);
    }

    private void messageInit() {
        messagePanel.setSize(750, 40);
        messagePanel.setBackground(Color.green);
        // messagePanel.setLayout(null);
    }

    private void infoInit() {
        infoPanel.setSize(210, 150);
        infoPanel.setBackground(Color.black);
    }

    private void detailInit() {
        detailPanel.setSize(210, 170);
        detailPanel.setBackground(Color.red);
    }

    private void showMessage() {
        messagePanel.addString("Test message 1!");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        messagePanel.start();
    }
//

}