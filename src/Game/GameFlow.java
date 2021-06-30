package Game;

import javax.swing.*;

import frame.WindowFrame;
import panels.*;
import panels.DetailPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static utils.Utils.basePath;

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
    private Timer msTimer = new Timer(100, this);
    private int elapsedTime = 0;

    public GameFlow() {
        messagePanel = new MessagePanel(60);
        messagePanel.addString("你的城市看起來很健康！");
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel();
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625, "lsp");
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0, "rbsp");
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2, "rsp");
        sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0, "sl");
        menuPanel = new MenuPanel();
        windowFrame = new WindowFrame();
        windowFrame.add(menuPanel);
        windowFrame.setVisible(true);
        StateManager.initGame(mapPanel.initVirusLabel());
        StateManager.addGameStateListener(this);

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
            StateManager.updateDate();
            // StateManager.updateGold();
            StateManager.addVirus();
            StateManager.spreadVirus();
            this.elapsedTime++;
            if (this.elapsedTime % 3 == 0) {
                boolean flag = false;
                for (int i = 0; i < StateManager.getVirus().size(); i++) {
                    if (StateManager.getAreaPercentage(i) > 0.05) {
                        String message = StateManager.areaNames[i] + "區疫情嚴重，請立刻協助防疫！";
                        this.messagePanel.addString(message);
                        this.messagePanel.removeString("你的城市看起來很健康！");
                        flag = true;
                    }
                }
                if (!flag)
                    this.messagePanel.addString("你的城市看起來很健康！");
            }
            if (this.elapsedTime % 5 == 0) {
                StateManager.updateAreaRecoveredNum();
                StateManager.updateAreaPeopleDeadNum();
                StateManager.updateGold();
            }
            if (this.elapsedTime % 10 == 0) {
                StateManager.updateAreaDeadProbability();
            }
            if (this.elapsedTime % 60 == 0) {
                StateManager.updateItems();
            }
            if (this.elapsedTime % 300 == 0) {
                StateManager.updateStatus();
            }
            this.infoPanel.updateVirusAmount(StateManager.getAmount(), StateManager.getPercentage());
            if (this.elapsedTime == 100000000) this.elapsedTime = 0;

        } else if (e.getSource().equals(msTimer)){
            StateManager.checkCondition();
        }
    }

    @Override
    public void onGameStateChanged(GameState prevState, GameState curState) {
        if (prevState == GameState.INIT && curState == GameState.INGAME) {
            windowFrame.getContentPane().remove(menuPanel);
            windowFrame.getContentPane().add(sl);
            windowFrame.getContentPane().revalidate();
            windowFrame.getContentPane().repaint();
            this.oneSecTimer.start();
            this.msTimer.start();
        }
        else if (prevState == GameState.INGAME && curState == GameState.PAUSE) {
            this.oneSecTimer.stop();
            this.msTimer.stop();
        }
        else if (prevState == GameState.PAUSE && curState == GameState.INGAME) {
            this.oneSecTimer.start();
            this.msTimer.start();
        }
        else if (curState == GameState.LOSE) {
            this.oneSecTimer.stop();
            this.msTimer.stop();
            windowFrame.getContentPane().remove(sl);
            JLabel loseLabel = new JLabel(new ImageIcon(basePath + "./lose.png"));
            loseLabel.setSize(1080,640);
            windowFrame.getContentPane().add(loseLabel);
            windowFrame.getContentPane().revalidate();
        }
        else if (curState == GameState.WIN) {
            this.oneSecTimer.stop();
            this.msTimer.stop();
            windowFrame.getContentPane().remove(sl);
            JLabel winLabel = new JLabel(new ImageIcon(basePath + "./win.png"));
            winLabel.setSize(1080,640);
            windowFrame.getContentPane().add(winLabel);
            windowFrame.getContentPane().revalidate();
        }
    }
}