import javax.swing.*;

import frame.WindowFrame;
import panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFlow implements ActionListener  {
    private DetailPanel detailPanel;
    private ToolbarPanel toolbarPanel;
    private MapPanel mapPanel;
    private MessagePanel messagePanel;
    private InfoPanel infoPanel;
    private WindowFrame windowFrame;
    private Timer goldTimer = new Timer(1000, this);
    private Timer incomeTimer = new Timer(10000, this);
    private int gold;
    private int incomePerHour;

    private JSplitPane makeSpiltPane(int orientation, Component a, Component b, double ratio, String name){
        JSplitPane sp = new JSplitPane(orientation, a, b);
        sp.setName(name);
        sp.setEnabled(false);
        sp.setResizeWeight(ratio);
        sp.setDividerSize(2);
        return sp;
    }

    public GameFlow() {
        this.gold = 1000;
        this.incomePerHour = 100;
        messagePanel = new MessagePanel(65);
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57, this.gold);
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625, "lsp");
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0.5, "rbsp");
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2, "rsp");
        JSplitPane sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0, "sl");
        windowFrame = new WindowFrame();
        windowFrame.add(sl);
        windowFrame.setVisible(true);
    }


    public void start() {
        this.goldTimer.start();
        this.incomeTimer.start();
        // showMessage();
        showTime();
        // showVirus();
        // showScore();
    }

    private void showMessage() {
        messagePanel.addString("Your city is safe now.");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        messagePanel.start();
    }
    private void showTime() {
        infoPanel.timeStart();
    }

    private void showVirus(){ mapPanel.start(); }

    private void showScore(){
        infoPanel.scoreStart(mapPanel.getViruses());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(goldTimer)) {
            this.gold += this.incomePerHour;
            infoPanel.updateGold(this.gold);
        }
        else if (e.getSource().equals(incomeTimer)) {
            this.incomePerHour++;
        }
    }
}