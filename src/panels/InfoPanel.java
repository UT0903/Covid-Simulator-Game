package panels;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Game.GameState;
import Game.GameStateListener;
import Game.StateManager;
import components.Virus;
import utils.Date;
import utils.Utils;

public class InfoPanel extends JPanel implements ActionListener, GameStateListener {
    private final Timer dateTimer = new Timer(1000, this);
    private final JLabel dateLabel = Utils.newLabelString("Date: ", 14);
    private final JLabel dateMes = Utils.newLabelString("", 14);
    private final JLabel virusesLabel = Utils.newLabelString("Virus Amount: ", 14);
    private final JLabel virusesMes = Utils.newLabelString("", 14);
    private final JLabel goldLabel = Utils.newLabelString("Gold: ", 14);
    private final JLabel goldMes = Utils.newLabelString("", 14);
    private JButton pauseButton = Utils.getButton("Pause", 20, 10);
    private JButton startButton = Utils.getButton("Start", 20, 10);
    private Date date;


    public InfoPanel(int YY, int MM, int DD, int hh, int mm, int ss, int gold) {
        super();
        setName("InfoPanel");
        setSize(330, 150);
        this.setLayout(new GridLayout(0,2));
        this.add(this.dateLabel);
        this.dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.date = new Date(YY, MM, DD, hh, mm, ss);
        this.date.check();
        this.add(this.dateMes);
        this.dateMes.setHorizontalAlignment(SwingConstants.LEFT);
        this.dateMes.setText(this.date.toString());
        this.add(this.virusesLabel);
        this.virusesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.virusesMes);
        this.virusesMes.setHorizontalAlignment(SwingConstants.LEFT);
        this.virusesMes.setText("0(0%)");
        this.add(this.goldLabel);
        this.goldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.goldMes);
        this.goldMes.setText(Integer.toString(gold));
        this.goldMes.setHorizontalAlignment(SwingConstants.LEFT);
        this.dateLabel.setSize(20,10);
        this.dateMes.setSize(20,10);
        this.virusesLabel.setSize(20,10);
        this.virusesMes.setSize(20,10);
        this.goldLabel.setSize(20,10);
        this.goldMes.setSize(20,10);
        this.add(pauseButton);
        this.pauseButton.addActionListener(this);
        this.pauseButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(startButton);
        this.startButton.addActionListener(this);
        this.startButton.setHorizontalAlignment(SwingConstants.CENTER);
        StateManager.addGameStateListener(this);
    }

    public void setDate(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.date = new Date(YY, MM, DD, hh, mm, ss);
    }

    public void timeStart() { this.dateTimer.start(); }
    public void timeStop() { this.dateTimer.stop(); }
    public void updateGold(int gold) { this.goldMes.setText(Integer.toString(gold)); }
    public void updateVirusAmount(int amount, int percentage) {
        this.virusesMes.setText(Integer.toString(amount)+ "(" + Integer.toString(percentage) + "%" + ")");
    }
    public JButton getPauseButton() { return this.pauseButton; }
    public JButton getStartButton() { return this.startButton; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.dateTimer)) {
            this.date.update();
            this.dateMes.setText(this.date.toString());
        }
        else if (e.getSource().equals(this.pauseButton))
            StateManager.setGameState(GameState.PAUSE);
        else if (e.getSource().equals(this.startButton))
            StateManager.setGameState(GameState.INGAME);
    }

    @Override
    public void onGameStateChanged(GameState prevState, GameState curState) {
        if (prevState == GameState.INIT && curState == GameState.INGAME)
            this.dateTimer.start();
        else if (prevState == GameState.INGAME && curState == GameState.PAUSE)
            this.dateTimer.stop();
        else if (prevState == GameState.PAUSE && curState == GameState.INGAME)
            this.dateTimer.start();
    }
}
