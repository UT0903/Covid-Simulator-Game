package panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JLayeredPane {
    private static final String basePath = "../pic/";
    ArrayList<Location> redDotsLocations = new ArrayList<Location>();
    public MapPanel(){
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        bgPic.setSize(750,600);
        add(bgPic, Integer.valueOf(0));
        addRedDot(100, 200);
        addRedDot(100, 300);
        addRedDot(200, 300);
        addRedDot(300, 400);
    }
    private void addRedDot(int x , int y){
        JLabel redDot = new JLabel();
        redDot.setOpaque(true);
        redDot.setBackground(Color.RED);
        redDot.setSize(10,10);
        add(redDot, Integer.valueOf(1));
        redDot.setLocation(x,y);
    }

//    @Override
//    public void paint(Graphics g) {
//        Graphics2D g2D = (Graphics2D) g;
//        for (int i = 0; i < redDotsLocations.size(); i++){
//            g2D.setPaint(Color.red);
//            g2D.drawOval(redDotsLocations.get(i).x, redDotsLocations.get(i).y, 10, 10);
//            g2D.fillOval(redDotsLocations.get(i).x, redDotsLocations.get(i).y, 10, 10);
//        }
//    }

}
class Location{
    public int x;
    public int y;
    Location(int x, int y){
        this.x = x;
        this.y = y;
    }
}


