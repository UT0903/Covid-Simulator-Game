package components;


import javax.swing.*;

import java.awt.*;

public class Virus extends JLabel {
    private Point location;
    private int groupID;
    public Virus(Point point){
        this.setSize(5,5);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.location = point;
        this.groupID = Area.getGroupByPixel(point.x , point.y);
    }

    @Override
    public Point getLocation() {
        return location;
    }
}
