package components;

import javax.swing.*;

import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class Virus extends JLabel {
    private int score;
    private int x;
    private int y;
    public Virus(String type){
        if (type.equals("red")){
            ImageIcon redVirusIcon = resizeImage(basePath + "./virus/1.png", 40, 40);
            this.setIcon(redVirusIcon);
            this.setSize(40,40);
            this.score = 10;
            this.x = 20;
            this.y = 20;
        }
        else if (type.equals("orange")){
            ImageIcon orangeVirusIcon = resizeImage(basePath + "./virus/2.png", 30, 30);
            this.setIcon(orangeVirusIcon);
            this.setSize(30,30);
            this.score = 15;
            this.x = 10;
            this.y = 10;
        }
        else if (type.equals("yellow")){
            ImageIcon yellowVirusIcon = resizeImage(basePath + "./virus/3.png", 20, 20);
            this.setIcon(yellowVirusIcon);
            this.setSize(20,20);
            this.score = 40;
            this.x = 5;
            this.y = 5;
        }
    }
    public int getScore() {
        return score;
    }
}
