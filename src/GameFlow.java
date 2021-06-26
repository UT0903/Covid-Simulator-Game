import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import frame.WindowFrame;
import panels.*;

import static java.util.Arrays.asList;

public class GameFlow {
    private DetailPanel detailPanel;
    private ToolbarPanel toolbarPanel;
    private MapPanel mapPanel;
    private MarqueePanel messagePanel;
    private InfoPanel infoPanel;
    private WindowFrame windowFrame;
    public GameFlow() {
        mapPanel = new MapPanel();
        toolbarPanel = new ToolbarPanel();
        messagePanel = new MarqueePanel(65);
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57);
        detailPanel = new DetailPanel();
        windowFrame = new WindowFrame(asList(new JLayeredPane[]{mapPanel, toolbarPanel, messagePanel, infoPanel, detailPanel}));
    }


    public void start() {
        showMessage();
        showTime();
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