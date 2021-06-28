package components;

import Game.MapStateListener;
import Game.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Area {
    private static int curG = -1;
    public static List<JLabel> grids = new ArrayList<>();
    public static Integer[][] gs = {
            {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300, 1350, 1400, 1450},
            {159, 208, 257, 306, 355, 405, 455, 505, 555, 606, 657, 708, 759, 811, 862, 912, 963, 1013, 1063, 1113, 1164, 1214},
            {10, 60, 110, 161, 213, 265, 318, 369, 420, 471, 522, 572, 622},
            {33, 84, 134, 184, 234, 284, 333, 382, 432, 481, 531, 580, 630, 679, 741, 745, 796, 845, 895, 945, 996, 1046, 1096, 1147},
            {1259, 1306, 1354, 1402, 1451, 1500, 1550, 1600, 1650, 1700, 1750, 1800, 1850, 1900, 1950, 2000, 2050, 2100, 2150, 2200, 2250, 2300, 2350, 2400, 2450},
            {1863, 1913, 1962, 2011, 2061, 2111, 2161, 2211, 2261, 2310, 2360, 2410, 2461, 1772, 1821, 1870, 1919},
            {1549, 1597, 1645, 1693, 1742, 1791, 1840, 1889, 1938, 1987, 2036, 2084, 2132, 2182, 2232, 2283, 2332, 2382, 2431, 2481},
            {1020, 1070, 1119, 1168, 1217, 1264, 1314, 1363, 1413, 1463, 1513, 1564, 1614, 1664, 1714, 1764, 1813, 1864, 1915},
            {1425, 1475, 1525, 1575, 1625, 1675, 1725, 1775, 1826, 1877, 1928, 1979, 2030, 2081},
            {1189, 1239, 1289, 1339, 1389, 1439, 1489, 1539, 1589, 1639, 1689, 1740},
            {729, 743, 780, 832, 883, 934, 985, 1036, 1087, 1137},
            {673, 723, 772, 822, 872, 921, 971, 1021, 1072, 1123, 1174, 1225, 1275, 1325, 1375}
    };
    public static Integer[][] ge = {
            {9, 59, 109, 158, 207, 256, 305, 354, 404, 454, 504, 554, 605, 656, 707, 758, 810, 861, 911, 962, 1012, 1062, 1112, 1163, 1213, 1258, 1305, 1353, 1401, 1450},
            {160, 212, 264, 317, 368, 419, 470, 521, 571, 621, 672, 722, 771, 821, 871, 920, 970, 1019, 1069, 1118, 1167, 1216},
            {32, 83, 133, 183, 233, 283, 332, 381, 431, 480, 530, 579, 629},
            {49, 99, 149, 199, 249, 299, 349, 399, 449, 499, 549, 599, 649, 699, 742, 749, 799, 849, 899, 949, 999, 1049, 1099, 1149},
            {1263, 1313, 1362, 1412, 1462, 1512, 1563, 1613, 1663, 1713, 1763, 1812, 1862, 1912, 1961, 2010, 2060, 2110, 2160, 2210, 2260, 2309, 2359, 2409, 2460},
            {1863, 1914, 1978, 2029, 2080, 2131, 2181, 2231, 2282, 2331, 2381, 2430, 2480, 1774, 1825, 1876, 1927},
            {1549, 1599, 1649, 1699, 1749, 1799, 1849, 1899, 1949, 1999, 2049, 2099, 2149, 2199, 2249, 2299, 2349, 2399, 2449, 2499},
            {1020, 1071, 1122, 1173, 1224, 1274, 1324, 1374, 1424, 1474, 1524, 1574, 1624, 1674, 1724, 1771, 1820, 1869, 1918},
            {1438, 1488, 1538, 1588, 1638, 1688, 1739, 1790, 1839, 1888, 1937, 1986, 2035, 2083},
            {1199, 1249, 1299, 1349, 1399, 1449, 1499, 1548, 1596, 1644, 1692, 1741},
            {740, 744, 795, 844, 894, 944, 995, 1045, 1095, 1146},
            {678, 728, 779, 831, 882, 933, 984, 1035, 1086, 1136, 1188, 1238, 1288, 1338, 1388}
    };
    public static final Color highlightColor = new Color(255, 0, 0, 100);
    public static HashMap<Integer, Integer> gridToGroup = new HashMap<>();
    public static void InitGroup(){
        //System.out.println("init group");
        for(int groupId = 0; groupId < gs.length; groupId++){
            for(int j = 0; j < gs[groupId].length; j++){
                //System.out.println(j);
                for(int k = gs[groupId][j]; k <= ge[groupId][j]; k++){
                    if(gridToGroup.get(k) == null){
                        gridToGroup.put(k, groupId);
                    }
                    else{
                        System.out.printf("invalid: %d\n", k);
                        assert (true== false);
                    }
                }
            }
        }
    }
    public static int getGroupByPixel(int x, int y){
        int gridId = x / 15 + (y / 12)*50;
        int groupId = Area.gridToGroup.get(gridId);
        return groupId;
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
