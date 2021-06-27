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
    private final JLabel mes = new JLabel();
    private final JLabel virusesMes = new JLabel();
    private final JLabel goldMes = new JLabel();
    private Date date;

//    public InfoPanel() {
//        //setLocation(750, 0);
//        setBackground(Color.red);
//        setOpaque(true);
//        this.date = new Date();
//        dateMes.setFont(new Font("Courier", Font.ITALIC, 16));
//        this.add(dateMes);
//        this.dateMes.setText(this.date.toString());
//        viruses.setFont(new Font("Courier", Font.ITALIC, 16));
//        this.add(viruses);
//        this.viruses.setText("Score:" + Integer.toString(score));
//    }

    public InfoPanel(int YY, int MM, int DD, int hh, int mm, int ss, int gold) {
        this.setLayout(new GridLayout(0,1));
        this.date = new Date(YY, MM, DD, hh, mm, ss);
        this.mes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.date.check();
        this.add(this.mes);
        this.mes.setText(this.date.toString());
        this.virusesMes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.add(this.virusesMes);
        this.virusesMes.setText("Virus amount: " + Integer.toString(0));
        this.goldMes.setFont(new Font("Courier", Font.ITALIC, 12));
        this.add(this.goldMes);
        this.goldMes.setText("Gold: " + Integer.toString(gold));
        this.mes.setHorizontalAlignment(SwingConstants.CENTER);
        this.virusesMes.setHorizontalAlignment(SwingConstants.CENTER);
        this.goldMes.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setDate(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.date = new Date(YY, MM, DD, hh, mm, ss);
    }

    public void timeStart() { this.dateTimer.start(); }
    public void timeStop() { this.dateTimer.stop(); }
    public void updateGold(int gold) { this.goldMes.setText("Gold: " + Integer.toString(gold)); }
    public void updateVirusAmount(int amount) { this.virusesMes.setText("Virus amount: " + Integer.toString(amount)); }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.date.update();
        this.mes.setText(this.date.toString());
    }
}
