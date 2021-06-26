package panels;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class ToolbarPanel extends JPanel {
    public List<JLabel> picLabels = new ArrayList<>();
    public static final String[] tools = {
            //"airplane.gif",
            "ambulance.gif",
            "canopy.png",
            "hospital.png",
            "mask.gif",
            "spray.gif",
            "syringe.gif"
    };
    public static final String[] toolDetails = {
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
        setName("ToolbarPanel");
        setLayout(new GridLayout(4, 2));
        for(int i = 0; i < tools.length; i++){
            picLabels.add(
                    makeLabel(
                    Utils.resizeImage(Utils.basePath + tools[i], 80, 60),
                    tools[i], toolDetails[i]));
        }
        for(JLabel pl: picLabels){
            add(pl);
        }
        setBackground(Color.PINK);
    }
    private JLabel makeLabel(ImageIcon icon, String name, String detail){
        ItemLabel picLabel = new ItemLabel();
        picLabel.setName(name);
        picLabel.setDetailed(detail);
        picLabel.setIcon(icon);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        picLabel.setOpaque(true);
        picLabel.setBackground(Color.gray);
        DragListener drag = new DragListener();
        picLabel.addMouseListener(drag);
        picLabel.addMouseMotionListener( drag );
        return picLabel;
    }
    public class ItemLabel extends JLabel{
        String detailed;
        public void setDetailed(String detailed){
            this.detailed = detailed;
        }
        public String getDetailed(){
            return detailed;
        }
    }
    public class DragListener extends MouseInputAdapter{
        Point location;
        MouseEvent pressed;

        public void mousePressed(MouseEvent me)
        {
            pressed = me;
            //System.out.println(pressed);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            e.getComponent().setBackground(Color.darkGray);
            for(Component c:e.getComponent().getParent().getParent().getComponents()){
                if(c.getName() != null && c.getName().equals("DetailPanel")){
                    DetailPanel dc = (DetailPanel) c;
                    dc.setComponents(((ItemLabel)e.getComponent()).getDetailed());
                    break;
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            e.getComponent().setBackground(Color.gray);
            for(Component c:e.getComponent().getParent().getParent().getComponents()){
                if(c.getName() != null && c.getName().equals("DetailPanel")){
                    DetailPanel dc = (DetailPanel) c;
                    dc.setComponents("init");
                    break;
                }
            }
        }

        public void mouseDragged(MouseEvent me)
        {
            //System.out.println(me.getComponent().getParent().getParent().getName());

            Component component = me.getComponent();
            location = component.getLocation();
            int x = location.x - pressed.getX() + me.getX();
            int y = location.y - pressed.getY() + me.getY();
            component.setLocation(x, y);
        }
    }
}
