package components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Area {
    private static int curG = -1;
    public static List<JLabel> grids = new ArrayList<>();
    public static Integer[][] gs = {
            {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300, 1350, 1400, 1450},
            {159, 208, 257, 306, 355, 405, 455, 505, 555, 606, 657, 708, 759, 811, 862, 912, 963, 1013, 1063, 1113, 1164, 1214}
    };
    public static Integer[][] ge = {
            {9, 59, 109, 158, 207, 256, 305, 354, 404, 454, 504, 554, 605, 656, 707, 758, 810, 861, 911, 962, 1012, 1062, 1112, 1163, 1213, 1258, 1305, 1353, 1401, 1450},
            {160, 212, 264, 317, 368, 419, 470, 521, 571, 621, 672, 722, 771, 821, 871, 920, 970, 1019, 1069, 1118, 1167, 1216}
    };
    public static final Color defaultColor = new Color(255, 0, 0, 0);
    public static final Color highlightColor = new Color(255, 0, 0, 100);
    public static void changeGroup(int groupId){
        if(curG != -1){
            setColor(curG, false);
        }
        setColor(groupId, true);
        curG = groupId;
    }

    public static void setColor(int groupId, boolean color){
        for (int i = 0; i < gs[groupId].length; i++) {
            Multithreading object
                    = new Multithreading(groupId, i, color);
            object.start();
        }
    }
    static class Multithreading extends Thread {
        int groupId, i;
        boolean color;
        public Multithreading(int groupId, int i, boolean color){
            this.groupId = groupId;
            this.i = i;
            this.color = color;
        }
        public void run()
        {
            for (int j = gs[groupId][i]; j <= ge[groupId][i]; j++) {
                //grids.get(j).setBackground(color);
                grids.get(j).setOpaque(color);
                grids.get(j).repaint();
                grids.get(j).revalidate();
            }
        }
    }
}
