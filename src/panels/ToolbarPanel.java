package panels;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class ToolbarPanel extends JPanel {
    public List<JLabel> picLabels = new ArrayList<JLabel>();
    public static final String[] tools = {
            //"airplane.gif",
            "ambulance.gif",
            "canopy.png",
            "hospital.png",
            "mask.gif",
            "spray.gif",
            "syringe.gif"
    };
    public ToolbarPanel(){
        super();
        setLayout(new GridLayout(4, 2));
        for(String tool : tools){
            picLabels.add(makeLabel(Utils.resizeImage(Utils.basePath + tool, 80, 60), tool));
        }
        for(JLabel pl: picLabels){
            add(pl);
        }
        setBackground(Color.PINK);
    }
    private JLabel makeLabel(ImageIcon icon, String name){
        JLabel picLabel = new JLabel();
        picLabel.setName(name);
        picLabel.setIcon(icon);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        picLabel.setOpaque(true);
        picLabel.setBackground(Color.gray);
        DragListener drag = new DragListener();
        picLabel.addMouseListener(drag);
        picLabel.addMouseMotionListener( drag );
        return picLabel;
    }
    public class DragListener extends MouseInputAdapter
    {
        Point location;
        MouseEvent pressed;

        public void mousePressed(MouseEvent me)
        {
            pressed = me;
            System.out.println(pressed);
        }

        public void mouseDragged(MouseEvent me)
        {
            System.out.println(me.getComponent().getName());
            Component component = me.getComponent();
            location = component.getLocationOnScreen();
            System.out.println(location);
            int x = location.x - pressed.getXOnScreen() + me.getXOnScreen();
            int y = location.y - pressed.getYOnScreen() + me.getYOnScreen();
            component.setLocation(x, y);
        }
    }
}
