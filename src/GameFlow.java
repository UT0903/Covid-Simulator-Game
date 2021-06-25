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
        //mapInit();
        //toolbarInit();
        //messageInit();
        //infoInit();
        //detailInit();
//        windowFrame.add(messagePanel);
//        messagePanel.setLocation(0, 0);
//
//        mapPanel.setLocation(0, 40);
//        windowFrame.add(infoPanel);
//        infoPanel.setLocation(750, 0);
//        windowFrame.add(toolbarPanel);
//        toolbarPanel.setLocation(750, 150);
//        windowFrame.add(detailPanel);
//        detailPanel.setLocation(750, 470);
        windowFrame.setVisible(true);
    }

    private void windowInit(){
        windowFrame.setSize(960, 640);
        windowFrame.setLayout(new GridBagLayout());

        windowFrame.setTitle("Virus Game");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //windowFrame.setResizable(false);
        //windowFrame.setPreferredSize(new Dimension(960, 640));
        //System.out.print(windowFrame.getPreferredSize());
        messagePanel.setBackground(Color.green);
        windowFrame.add(messagePanel, setGbc(0, 0, 75, 4, 0.8, 0.1));

        mapPanel.setLayout(new FlowLayout());
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "map.png")); //Add background
        mapPanel.setPreferredSize(new Dimension(500, 300));
        mapPanel.add(bgPic);
        mapPanel.setBackground(Color.BLUE);
        windowFrame.add(mapPanel, setGbc(0, 4, 75, 60, 0.8, 0.9));

        //infoPanel.setBackground(Color.yellow);
        windowFrame.add(infoPanel, setGbc(75, 0, 21, 64, 0.2, 1.0));

    }
    private GridBagConstraints setGbc(int x, int y, int w, int h, double wx, double wy){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = wx;
        gbc.weighty = wy;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }
//    private void mapInit(){
//        GridBagConstraints gbc = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 40;
//        c.fill = GridBagConstraints.BOTH;
//        //mapPanel.setSize(750, 600);
//        //mapPanel.setLayout(new BorderLayout());
//        //JLabel bgPic = new JLabel(new ImageIcon(basePath + "map.png")); //Add background
//        //mapPanel.add(bgPic);
//        mapPanel.setBackground(Color.BLUE);
//        windowFrame.add(mapPanel, c);
//    }
//
//    private void toolbarInit() {
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 750;
//        c.gridy = 150;
//        //c.gridwidth = 210;
//        //c.gridheight = 320;
//        c.fill = GridBagConstraints.BOTH;
//        //toolbarPanel.setSize(210, 320);
//        toolbarPanel.setBackground(Color.yellow);
//        windowFrame.add(toolbarPanel, c);
//    }
//
//    private void messageInit() {
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        //c.gridwidth = 12;
//        //c.gridheight = 2;
//        c.fill = GridBagConstraints.BOTH;
//        messagePanel.setSize(750, 40);
//        messagePanel.setBackground(Color.green);
//    }
//
//    private void infoInit() {
//        infoPanel.setSize(210, 150);
//        infoPanel.setBackground(Color.black);
//    }
//
//    private void detailInit() {
//        detailPanel.setSize(210, 170);
//        detailPanel.setBackground(Color.red);
//
//    }
}