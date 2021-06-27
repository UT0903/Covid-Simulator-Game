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

public class MapPanel extends JLayeredPane implements ActionListener {
    private static final int delay = 1000;
    private final Timer timer = new Timer(delay, this);
    private int timerCount = 0;
    private ArrayList<Virus> viruses = new ArrayList<Virus>();
    private ArrayList<Point> chosen = new ArrayList<Point>();
    private ArrayList<Point> notChosen = new ArrayList<Point>();
    public MapPanel() {
        setPreferredSize(new Dimension(750, 600));

//        setLocation(0, 40);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background
        bgPic.setOpaque(true);
        setArea();
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 110; j++){
                notChosen.add(new Point(i * 5 , j * 5));
            }
        }
    }
    public void start() {timer.start();}
    public void stop() { timer.stop(); }

    public ArrayList<Virus> getViruses() {
        return viruses;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addRedVirus();
//        if (timerCount > 10){
//            if (timerCount % 2 == 0)
//                addOrangeVirus((int) (Math.random() * 700), (int) (Math.random() * 550));
//        }
//        if (timerCount > 20){
//            addYellowVirus((int) (Math.random() * 700), (int) (Math.random() * 550));
//        }
        timerCount++;
    }
    private void addRedVirus(){
        int l = (int) Math.round(Math.random() * notChosen.size());
        Point location = notChosen.get(l);
        System.out.printf("%d, %d\n", location.x, location.y);
        Virus redVirus = new Virus(location);
        add(redVirus, Integer.valueOf(1));
        redVirus.setLocation(location);
        viruses.add(redVirus);
        chosen.add(location);
        notChosen.remove(location);

    }

//    private void removeViruses(ArrayList<JLabel> removeList){
//        for (int i = 0; i < removeList.size(); i++){
//            this.remove(removeList.get(i));
//        }
//        viruses.removeAll(removeList);
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



