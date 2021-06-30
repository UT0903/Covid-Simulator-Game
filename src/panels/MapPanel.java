package panels;

import Game.MapStateListener;
import Game.VirusListener;
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
import java.util.List;

import static utils.Utils.basePath;

public class MapPanel extends JLayeredPane implements MapStateListener, VirusListener {
    private int timerCount = 0;
    private JPanel virusPanel;
    public MapPanel() {
        setPreferredSize(new Dimension(750, 600));

//        setLocation(0, 40);
        JLabel bgPic = new JLabel(new ImageIcon(basePath + "./map.png")); //Add background

        bgPic.setOpaque(true);
        setArea();
        bgPic.setSize(750, 600);
        add(bgPic, Integer.valueOf(0));
        virusPanel = new JPanel();
        virusPanel.setLayout(new GridLayout(150, 110));

        add(virusPanel, Integer.valueOf(1));
        StateManager.addMapStateListener(this);
        StateManager.addVirusListener(this);
    }
    public List<Virus> initVirusLabel(){
        List <Virus> virusLabels = new ArrayList<Virus>();
        for(int i = 0; i < 150; i++){
            for(int j = 0; j < 110; j++){
                Virus gridVirusLabel = new Virus(new Point(i*5, j*5));
                virusPanel.add(gridVirusLabel);
                virusLabels.add(gridVirusLabel);
            }
        }
        return virusLabels;
    }
    public void removeViruses(List<Virus> removeList){
        for (Virus virus: removeList) {
            virus.setOpaque(false);
            virus.revalidate();
            virus.repaint();
        }
    }

    public void addVirus(Virus virus){
        virus.setOpaque(true);
        virus.revalidate();
        virus.repaint();
        //add(virus, Integer.valueOf(1));
        //virus.setLocation(virus.getLocation());
    }

    public void addVirus(List<Virus> virusList){
        for (int i = 0; i < virusList.size(); i++){
            Virus virus = virusList.get(i);
            add(new JPanel(), Integer.valueOf(1));
            add(virus, Integer.valueOf(1));
            virus.setLocation(virus.getLocation());
        }
    }

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
            label.setPreferredSize(new Dimension(15, 12));
            MapMouseListener mouse = new MapMouseListener();
            label.addMouseListener(mouse);
            label.addMouseMotionListener(mouse);
            //label.setBorder(BorderFactory.createLineBorder(Color.red));
            label.setOpaque(false);
            panel.add(label);
        }
        panel.setOpaque(false);
        panel.setSize(750, 600);
        //MapMouseListener mouse = new MapMouseListener();
        //panel.addMouseListener(mouse);
        //panel.addMouseMotionListener(mouse);
        Area.InitGroup();
        add(panel, Integer.valueOf(5));
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

    @Override
    public void onVirusIncreased(List<Virus> increasedVirus) {
        for (Virus virus: increasedVirus)
            this.addVirus(virus);
    }

    @Override
    public void onVirusDecreased(List<Virus> decreasedVirus) {
        this.removeViruses(decreasedVirus);
    }

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
            //System.out.printf("Area: %d\n", groupId);
            StateManager.setMapClickId(groupId);
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



