package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ToolbarPanel extends JLayeredPane {
    public ToolbarPanel(){
        super();
        setSize(210, 320);
        setLocation(750, 150);
        setLayout(new GridLayout(5, 2));
        for(String tool : Utils.tools){
            String basePath;
            add(addIcon(Utils.resizeImage(Utils.basePath + tool, 90, 60)));
        }
        setBackground(Color.PINK);
    }
    private JLabel addIcon(ImageIcon icon){
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
                /*JLabel redDot = new JLabel();
                redDot.setOpaque(true);
                redDot.setBackground(Color.RED);
                redDot.setSize(20, 20);
                windowPanel.add(redDot, Integer.valueOf(2));
                redDot.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX() - 90, (int)MouseInfo.getPointerInfo().getLocation().getY() - 80);
                */
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                System.out.println("out");
                //picLabel.setBackground(Color.PINK);
            }
            @Override
            public void mouseDragged(MouseEvent e){
                System.out.println("drag");
                System.out.println(e);
            }
        });
        return picLabel;
    }

}
