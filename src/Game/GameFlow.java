package Game;

import javax.swing.*;

import components.Virus;
import frame.WindowFrame;
import panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFlow implements ActionListener, GameStateListener  {
    private DetailPanel detailPanel;
    private ToolbarPanel toolbarPanel;
    private MapPanel mapPanel;
    private MessagePanel messagePanel;
    private InfoPanel infoPanel;
    private MenuPanel menuPanel;
    private JSplitPane sl;
    private WindowFrame windowFrame;
    private Timer oneSecTimer = new Timer(1000, this);
    private Timer incomeTimer = new Timer(10000, this);
    private Timer msTimer = new Timer(100, this);
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
        messagePanel = new MessagePanel(65);
        messagePanel.addString("Your city is safe now.");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        StateManager.addGameStateListener(this);
        StateManager.addItemStateListener(detailPanel);
        StateManager.addMapStateListener(detailPanel);
        StateManager.addMapStateListener(mapPanel);
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57, stateManager.getGold());
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625, "lsp");
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0, "rbsp");
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2, "rsp");
        sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0, "sl");
        menuPanel = new MenuPanel();
        windowFrame = new WindowFrame();
        windowFrame.add(menuPanel);
        windowFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(oneSecTimer)) {
            this.stateManager.updateGold();
            this.infoPanel.updateGold(this.stateManager.getGold());
            Virus virus = this.stateManager.addVirus();
            this.mapPanel.addVirus(virus);
        } else if (e.getSource().equals(incomeTimer)) {
            this.stateManager.updateIncome();
        } else if (e.getSource().equals(msTimer)){
            this.infoPanel.updateVirusAmount(this.stateManager.getViruses().size());
        }
    }

    @Override
    public void onGameStateChanged(GameState prevState, GameState curState) {
        if (prevState == GameState.INIT && curState == GameState.INGAME) {
            windowFrame.getContentPane().remove(menuPanel);
            windowFrame.getContentPane().add(sl);
            windowFrame.getContentPane().revalidate();
            this.oneSecTimer.start();
            this.msTimer.start();
            this.incomeTimer.start();
        }
        else if (prevState == GameState.INGAME && curState == GameState.PAUSE) {
            this.oneSecTimer.stop();
            this.msTimer.stop();
            this.incomeTimer.stop();
        }
        else if (prevState == GameState.PAUSE && curState == GameState.INGAME) {
            this.oneSecTimer.start();
            this.msTimer.start();
            this.incomeTimer.start();
        }
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}