import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import panels.*;

public class GameFlow {
    private JLayeredPane toolbarPanel, windowPanel, detailPanel;
    private MapPanel mapPanel;
    private MarqueePanel messagePanel;
    private InfoPanel infoPanel;
    private JFrame windowFrame;
    public GameFlow() {
        windowInit();
        mapPanel = new MapPanel();
        toolbarPanel = new ToolbarPanel();
        messagePanel = new MarqueePanel(65);
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57);
        detailPanel = new DetailPanel();
    }

    public void start() {
        windowPanel.add(messagePanel);
        messagePanel.setLocation(0, 0);
        windowPanel.add(mapPanel, Integer.valueOf(0));
        mapPanel.setLocation(0, 40);
        windowPanel.add(infoPanel, Integer.valueOf(1));
        infoPanel.setLocation(750, 0);
        windowPanel.add(toolbarPanel, Integer.valueOf(1));
        toolbarPanel.setLocation(750, 150);
        windowPanel.add(detailPanel, Integer.valueOf(1));
        detailPanel.setLocation(750, 470);
        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        showMessage();
        showTime();
    }

    private void windowInit(){
        windowFrame = new JFrame();
        windowFrame.setSize(960, 640);
        windowFrame.setTitle("Virus Game");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowPanel = new JLayeredPane();
        windowPanel.setSize(960, 640);
        windowFrame.add(windowPanel);
    }

    private void showMessage() {
        messagePanel.addString("Test message 1!");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        messagePanel.start();
    }
    private void showTime() {
        infoPanel.start();
    }
}