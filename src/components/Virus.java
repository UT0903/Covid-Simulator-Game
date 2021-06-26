package components;

import javax.swing.*;

import static panels.Utils.basePath;
import static panels.Utils.resizeImage;

public class Virus extends JLabel {
    private int score;
    public Virus(String type){
        if (type.equals("red")){
            ImageIcon redVirusIcon = resizeImage(basePath + "./virus/1.png", 20, 20);
            this.setIcon(redVirusIcon);
            this.setSize(20,20);
            this.score = 10;
        }
        else if (type.equals("orange")){
            ImageIcon orangeVirusIcon = resizeImage(basePath + "./virus/2.png", 20, 20);
            this.setIcon(orangeVirusIcon);
            this.setSize(20,20);
            this.score = 20;
        }
        else if (type.equals("yellow")){
            ImageIcon yellowVirusIcon = resizeImage(basePath + "./virus/3.png", 20, 20);
            this.setIcon(yellowVirusIcon);
            this.setSize(20,20);
            this.score = 40;
        }
    }
    public int getScore() {
        return score;
    }
}
