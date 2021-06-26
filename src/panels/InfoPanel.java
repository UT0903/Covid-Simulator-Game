package panels;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import utils.Date;

public class InfoPanel extends JLayeredPane implements ActionListener {
    private static final int RATE = 1;
    private final Timer timer = new Timer(1000 / RATE, this);
    private final JLabel mes = new JLabel();
    private Date date;

    public InfoPanel() {
        setLocation(750, 0);
        this.date = new Date();
        mes.setFont(new Font("Courier", Font.ITALIC, 16));
        this.add(mes);
        this.mes.setText(this.date.toString());
    }

    public InfoPanel(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.date = new Date(YY, MM, DD, hh, mm, ss);
        mes.setFont(new Font("Courier", Font.ITALIC, 16));
        this.date.check();
        this.add(mes);
        this.mes.setText(this.date.toString());
    }

    public void setDate(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.date = new Date(YY, MM, DD, hh, mm, ss);
    }

    public void start() { timer.start(); }
    public void stop() { timer.stop(); }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.date.update();
        this.mes.setText(this.date.toString());
    }
}
