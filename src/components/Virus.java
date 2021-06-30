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
        setOpaque(false);
        this.location = point;
        this.groupID = Area.getGroupByPixel(point.x , point.y);
        this.chosen = false;
    }
    public void setLocation(int r, int c){
        this.location = new Point(r*5, c*5);
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
