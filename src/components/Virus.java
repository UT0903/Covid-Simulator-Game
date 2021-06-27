package components;


import javax.swing.*;

import java.awt.*;

import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class Virus extends JLabel {
    private int x;
    private int y;
    private int groupID;
    public Virus(Point point){
        this.setSize(5,5);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.x = point.x;
        this.y = point.y;
    }
}
