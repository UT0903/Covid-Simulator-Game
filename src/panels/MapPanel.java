package panels;

import Game.MapStateListener;
import Game.StateManager;
import components.Area;
import components.Virus;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import static utils.Utils.basePath;

public class MapPanel extends JLayeredPane implements ActionListener, MapStateListener {
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
        BackgroundMouseListener background = new BackgroundMouseListener();
        panel.addMouseListener(background);
        panel.addMouseMotionListener(background);
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
    @Override
    public void onAreaHoverChanged(int prevId, int newId) {
        if(newId != -1){
            Area.setColor(newId, true);
        }
        if(prevId!= -1){
            Area.setColor(prevId, false);
        }
    }

    @Override
    public void onAreaClickChanged(int prevId, int newId) {}
    public class BackgroundMouseListener extends MouseInputAdapter{
        public void mouseExited(MouseEvent e){
            System.out.println("exit background");
        }
    }
    public class MapMouseListener extends MouseInputAdapter {
        public void mouseClicked(MouseEvent e) {
            //pressed = e;
            int gridId = Integer.parseInt(e.getComponent().getName());
            Integer groupId = Area.gridToGroup.get(gridId);
            System.out.printf("group: %d\n", groupId);
            //System.out.println(pressed);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            int gridId = Integer.parseInt(e.getComponent().getName());
            Integer groupId = Area.gridToGroup.get(gridId);
            if (groupId == null) {
                System.out.println("should not be null in MapPanel");
                return;
            }
            StateManager.setMapHoverId(groupId);
        }
//        public void mouseExited(MouseEvent e){
//            super.mouseExited(e);
//            int id1 = StateManager.getCurMapHoverId();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//            if(StateManager.getCurMapHoverId() == id1){
//                StateManager.setMapHoverId(-1);
//            }
//        }
    }
}



