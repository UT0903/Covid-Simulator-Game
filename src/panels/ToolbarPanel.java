package panels;

import Game.StateManager;
import utils.Utils;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class ToolbarPanel extends JPanel {
    public List<JLabel> picLabels = new ArrayList<>();

    public ToolbarPanel(){
        super();
        setName("ToolbarPanel");
        setLayout(new GridLayout(3, 2));
        for(int i = 0; i < StateManager.labelNames.length; i++){
            picLabels.add(makeLabel(i));
        }
        for(JLabel pl: picLabels){
            add(pl);
        }
        setBackground(Color.PINK);
    }
    private JLabel makeLabel(int idx){
        myJLabel picLabel = new myJLabel();
        ImageIcon icon = Utils.resizeImage(Utils.basePath + StateManager.labelNames[idx], 80, 60);
        picLabel.setName(StateManager.labelNames[idx]);
        picLabel.id = idx;
//        picLabel.detailed = Constant.details[idx];
//        picLabel.remainNum = 0;
//        picLabel.cost = Constant.costs[idx];
        picLabel.setIcon(icon);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        picLabel.setOpaque(true);
        picLabel.setBackground(Color.gray);
        ToolbarPanel.DragListener drag = new ToolbarPanel.DragListener();
        picLabel.addMouseListener(drag);
        picLabel.addMouseMotionListener( drag );
        return picLabel;
    }
    public class myJLabel extends JLabel{
        public int id;
    }
    public class DragListener extends MouseInputAdapter{
//        Point location;
//        MouseEvent pressed;
//
        public void mouseClicked(MouseEvent e) {
            super.mouseEntered(e);
            int newId = ((myJLabel)e.getComponent()).id;
            if(StateManager.getCurClickItemId() == newId){
                StateManager.setItemClickId(-1);
            }
            else{
                StateManager.setItemClickId(newId);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            e.getComponent().setBackground(Color.darkGray);
            StateManager.setItemHoverId(((myJLabel)e.getComponent()).id);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            e.getComponent().setBackground(Color.gray);
            StateManager.setItemHoverId(-1);
        }

    }
}
