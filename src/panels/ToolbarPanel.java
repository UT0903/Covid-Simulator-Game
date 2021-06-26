package panels;

import javax.swing.*;
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
            picLabels.add(makeLabel(Utils.resizeImage(Utils.basePath + tool, 80, 60)));
        }
        for(JLabel pl: picLabels){
            add(pl);
        }
        setBackground(Color.PINK);
    }
    private JLabel makeLabel(ImageIcon icon){
        JLabel picLabel = new JLabel();
        picLabel.setIcon(icon);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        picLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("in");

            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                System.out.println("out");
                //picLabel.setBackground(Color.PINK);
            }
        });
        return picLabel;
    }

}
