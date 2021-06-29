package Game;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;

import components.Virus;
import frame.WindowFrame;
import panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

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
    private int elapsedTime = 0;

    public GameFlow() {
        messagePanel = new MessagePanel(60);
        messagePanel.addString("你的城市看起來很健康！");
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        StateManager.addGameStateListener(this);
        StateManager.addItemStateListener(detailPanel);
        StateManager.addMapStateListener(detailPanel);
        StateManager.addMapStateListener(mapPanel);
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel(StateManager.getGold());
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625, "lsp");
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0, "rbsp");
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2, "rsp");
        sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0, "sl");
        menuPanel = new MenuPanel();
        windowFrame = new WindowFrame();
        windowFrame.add(menuPanel);
        windowFrame.setVisible(true);
        StateManager.initGame();
    }

    private JSplitPane makeSpiltPane(int orientation, Component a, Component b, double ratio, String name){
        JSplitPane sp = new JSplitPane(orientation, a, b);
        sp.setName(name);
        sp.setEnabled(false);
        sp.setResizeWeight(ratio);
        sp.setDividerSize(2);
        return sp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(oneSecTimer)) {
            StateManager.updateGold();
            this.infoPanel.updateDate();
            this.infoPanel.updateGold(StateManager.getGold());
            Virus virus = StateManager.addVirus();
            this.mapPanel.addVirus(virus);
            this.mapPanel.addVirus(StateManager.spreadVirus());
            this.elapsedTime++;
            if (this.elapsedTime == 3) {
                this.elapsedTime = 0;
                boolean flag = false;
                for (int i = 0; i < StateManager.getVirus().size(); i++) {
                    if (StateManager.getAreaPercentage(i) > 5) {
                        String message = StateManager.areaNames[i] + "區疫情嚴重，請立刻協助防疫！";
                        this.messagePanel.addString(message);
                        this.messagePanel.removeString("你的城市看起來很健康！");
                        flag = true;
                    }
                }
                if (!flag)
                    this.messagePanel.addString("你的城市看起來很健康！");
            }
        } else if (e.getSource().equals(incomeTimer)) {
            StateManager.updateIncome();
        } else if (e.getSource().equals(msTimer)){
            this.infoPanel.updateVirusAmount(StateManager.getAmount(), StateManager.getPercentage());
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
}