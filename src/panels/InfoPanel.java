package panels;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import components.Virus;
import utils.Date;

public class InfoPanel extends JPanel implements ActionListener {
    private final Timer dateTimer = new Timer(1000, this);
    private final Timer scoreTimer = new Timer(50, this);
    private final JLabel mes = Utils.newLabelString("", 12);
    private final JLabel scoreMes = Utils.newLabelString("", 12);
    private final JLabel goldMes = Utils.newLabelString("", 12);
    private Date date;
    private ArrayList<Virus> viruses = new ArrayList<Virus>();
    private int score = 100;

//    public InfoPanel() {
//        //setLocation(750, 0);
//        setBackground(Color.red);
//        setOpaque(true);
//        this.date = new Date();
//        dateMes.setFont(new Font("Courier", Font.ITALIC, 16));
//        this.add(dateMes);
//        this.dateMes.setText(this.date.toString());
//        scoreMes.setFont(new Font("Courier", Font.ITALIC, 16));
//        this.add(scoreMes);
//        this.scoreMes.setText("Score:" + Integer.toString(score));
//    }

    public InfoPanel(int YY, int MM, int DD, int hh, int mm, int ss, int gold) {
        this.setLayout(new GridLayout(0,1));
        this.date = new Date(YY, MM, DD, hh, mm, ss);
        //this.mes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.date.check();
        this.add(this.mes);
        this.mes.setText(this.date.toString());
        //this.scoreMes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.add(this.scoreMes);
        this.scoreMes.setText("Virus amount: " + Integer.toString(score));
        //this.goldMes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.add(this.goldMes);
        this.goldMes.setText("Gold: " + Integer.toString(gold));
        this.mes.setHorizontalAlignment(SwingConstants.CENTER);
        this.scoreMes.setHorizontalAlignment(SwingConstants.CENTER);
        this.goldMes.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setDate(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.date = new Date(YY, MM, DD, hh, mm, ss);
    }

    public void timeStart() {
        dateTimer.start();
    }

    public void scoreStart(ArrayList<Virus> viruses){
        this.viruses = viruses;
        scoreTimer.start();
    }

    public void updateGold(int gold) {
        this.goldMes.setText("Gold: " + Integer.toString(gold));
    }

    public void stop() {
        dateTimer.stop();
        scoreTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(dateTimer)){
            this.date.update();
            this.mes.setText(this.date.toString());
        }
        else if (e.getSource().equals(scoreTimer)){
            this.scoreMes.setText("Virus amount: " + Integer.toString(viruses.size()));
        }
    }
}
