package panels;

import components.Area;
import components.Virus;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;


import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class MapPanel extends JLayeredPane {
    private int timerCount = 0;

    public MapPanel() {
        setPreferredSize(new Dimension(750, 600));

//        setLocation(0, 40);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        setArea();
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));

    }

    public void addVirus(Virus virus){
        add(virus, Integer.valueOf(1));
        virus.setLocation(virus.getLocation());
    }


//    private void removeViruses(ArrayList<Virus> removeList){
//        for (int i = 0; i < removeList.size(); i++){
//            remove(removeList.get(i));
//        }
//    }
    private void setArea(){
        JPanel panel = new JPanel(new GridLayout(50, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        for (int i =0; i<(50*50); i++){
            final JLabel label = new JLabel();
            label.setName(String.format("%d", i));
            label.setBackground(Area.highlightColor);
            Area.grids.add(label);
            /*if(i < 150){
                label.setBackground(new Color(255, 0, 0, 100));
            }*/
            label.setPreferredSize(new Dimension(15, 11));
            MapMouseListener mouse = new MapMouseListener();
            label.addMouseListener(mouse);
            label.addMouseMotionListener(mouse);
            //label.setBorder(BorderFactory.createLineBorder(Color.red));
            label.setOpaque(false);
            panel.add(label);
        }
        panel.setOpaque(false);
        panel.setSize(750, 550);
        //MapMouseListener mouse = new MapMouseListener();
        //panel.addMouseListener(mouse);
        //panel.addMouseMotionListener(mouse);
        Area.InitGroup();
        add(panel, Integer.valueOf(2));
    }
    public class MapMouseListener extends MouseInputAdapter {
        Point location;
        MouseEvent pressed;
        int curNum = 0;
        public void mouseClicked (MouseEvent e) {
            //pressed = e;
            int gridId = Integer.parseInt(e.getComponent().getName());
            Integer groupId = Area.gridToGroup.get(gridId);
            System.out.printf("group: %d\n", groupId);
            //System.out.println(pressed);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            //System.out.println(e.getComponent().getName());
            Area.changeGroup(e.getComponent());
//            e.getComponent().setBackground(Color.darkGray);
//            for (Component c : e.getComponent().getParent().getParent().getComponents()) {
//                if (c.getName() != null && c.getName().equals("DetailPanel")) {
//                    DetailPanel dc = (DetailPanel) c;
//                    dc.setComponents(((ToolbarPanel.ItemLabel) e.getComponent()).getDetailed());
//                    break;
//                }
//            }
        }
        /*@Override
        public void mouseMoved(MouseEvent e) {
            super.mouseEntered(e);
//            e.getComponent().setBackground(Color.darkGray);
//            for (Component c : e.getComponent().getParent().getParent().getComponents()) {
//                if (c.getName() != null && c.getName().equals("DetailPanel")) {
//                    DetailPanel dc = (DetailPanel) c;
//                    dc.setComponents(((ToolbarPanel.ItemLabel) e.getComponent()).getDetailed());
//                    break;
//                }
//            }
            System.out.printf("%d %d\n", e.getX(), e.getY());
        }
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
//            e.getComponent().setBackground(Color.gray);
//            for (Component c : e.getComponent().getParent().getParent().getComponents()) {
//                if (c.getName() != null && c.getName().equals("DetailPanel")) {
//                    DetailPanel dc = (DetailPanel) c;
//                    dc.setComponents("init");
//                    break;
//                }
//            }
        }*/
    }

}



