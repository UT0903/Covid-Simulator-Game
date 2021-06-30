package panels;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Game.*;
import utils.Date;
import utils.Utils;

public class InfoPanel extends JPanel implements ActionListener, GoldListener, DateListener {
    private final JLabel dateLabel = Utils.newLabelString("Date: ", 14);
    private final JLabel dateMes = Utils.newLabelString("", 14);
    private final JLabel virusesLabel = Utils.newLabelString("Virus Amount: ", 14);
    private final JLabel virusesMes = Utils.newLabelString("0(0%)", 14);
    private final JLabel goldLabel = Utils.newLabelString("Gold: ", 14);
    private final JLabel goldMes = Utils.newLabelString("", 14);
    private final JButton pauseButton = Utils.getButton("Pause", 20, 10);

    public InfoPanel() {
        super();
        setName("InfoPanel");
        setSize(330, 120);
        this.setLayout(new GridLayout(0,2));
        this.add(this.dateLabel);
        this.dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.dateMes);
        this.dateMes.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(this.virusesLabel);
        this.virusesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.virusesMes);
        this.virusesMes.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(this.goldLabel);
        this.goldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.goldMes);
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
        StateManager.addGoldListener(this);
        StateManager.addDateListeners(this);
    }

    public void setDate(Date date) { this.dateMes.setText(date.toString()); }
    public void updateVirusAmount(int amount, int percentage) { this.virusesMes.setText(amount+ "(" + percentage + "%" + ")"); }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.pauseButton) && this.pauseButton.getText().equals("Pause")) {
            this.pauseButton.setText("Continue");
            StateManager.setGameState(GameState.PAUSE);
        }
        else if (e.getSource().equals(this.pauseButton) && this.pauseButton.getText().equals("Continue")) {
            this.pauseButton.setText("Pause");
            StateManager.setGameState(GameState.INGAME);
        }
    }

    @Override
    public void onGoldChanged(int newGold) { this.goldMes.setText(Integer.toString(newGold)); }

    @Override
    public void onDateChanged(Date date) { this.setDate(date); }
}
