package components;


import javax.swing.*;

import java.awt.*;

public class Virus extends JLabel {
    private Point location;
    private int groupID;
    private boolean chosen;
    public Virus(Point point){
        this.setSize(5,5);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.location = point;
        System.out.printf("%d, %d\n", point.x, point.y);
        this.groupID = Area.getGroupByPixel(point.x , point.y);
        this.chosen = false;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public boolean getChosen() {
        return chosen;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
