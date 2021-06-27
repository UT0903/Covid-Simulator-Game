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
    private MenuPanel menuPanel;
    private JSplitPane sl;
    private WindowFrame windowFrame;
    private Timer goldTimer = new Timer(1000, this);
    private Timer incomeTimer = new Timer(10000, this);
    private StateManager stateManager;

    private JSplitPane makeSpiltPane(int orientation, Component a, Component b, double ratio, String name){
        JSplitPane sp = new JSplitPane(orientation, a, b);
        sp.setName(name);
        sp.setEnabled(false);
        sp.setResizeWeight(ratio);
        sp.setDividerSize(2);
        return sp;
    }
    public GameFlow() {
        this.stateManager = new StateManager();
        messagePanel = new MessagePanel(105);
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57, stateManager.getGold(), stateManager.getScore());
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625, "lsp");
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0.5, "rbsp");
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2, "rsp");
        sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0, "sl");
        StartListener startListener = new StartListener();
        menuPanel = new MenuPanel(startListener);
        windowFrame = new WindowFrame();
        windowFrame.add(menuPanel);
        windowFrame.setVisible(true);
    }


    public void start() {
        this.goldTimer.start();
        this.incomeTimer.start();
        showMessage();
        showTime();
        showVirus();
    }

    private void showMessage() {
        messagePanel.addString("Your city is safe now.");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        messagePanel.start();
    }

    private void showTime() { this.infoPanel.timeStart(); }
    private void showVirus() { mapPanel.start(); }

    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            windowFrame.getContentPane().remove(menuPanel);
            windowFrame.getContentPane().add(sl);
            windowFrame.getContentPane().revalidate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(goldTimer)) {
            this.stateManager.updateGold();
            this.infoPanel.updateGold(this.stateManager.getGold());
            this.infoPanel.updateScore(this.stateManager.getGold());
        } else if (e.getSource().equals(incomeTimer)) {
            this.stateManager.updateIncome();
        }
    }
}