package components;


import javax.swing.*;

import java.awt.*;

public class Virus extends JLabel {
    private int groupID;
    private boolean chosen;
    public Virus(Point point){
        this.setSize(5,5);
        this.setBackground(Color.RED);
        this.setOpaque(false);
        this.groupID = Area.getGroupByPixel(point.x , point.y);
        this.chosen = false;
        this.setLocation(point);
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
