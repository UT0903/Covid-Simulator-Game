package panels.DetailPanelComponent;

import Game.MapStateListener;
import Game.StateManager;
import panels.DetailPanelComponent.Component.ChangeBtnAdapter;
import panels.DetailPanelComponent.Component.ModifyPanel;
import panels.DetailPanelComponent.Component.NumChangeBtn;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AreaModifyPanel extends JPanel {
    private JButton confirmBtn, cancelBtn;
    private List<JLabel> labels;
    private List<ModifyPanel> mps;
    private int curClickAreaId;

    public void update(int curClickAreaId) {
        this.curClickAreaId = curClickAreaId;
        for(ModifyPanel mp: mps) {
            mp.setCopy(curClickAreaId);
        }
        labels.get(0).setText("地區: " + StateManager.areaNames[curClickAreaId]);
        labels.get(1).setText("人口數: " + StateManager.areaPeopleNum[curClickAreaId]);
        labels.get(2).setText("感染人數: " + StateManager.areaPeopleInfectedNum[curClickAreaId]);
        labels.get(3).setText("死亡人數: " + StateManager.areaPeopleDeadNum[curClickAreaId]);
        System.out.println("update in amp");
    }

    public AreaModifyPanel(){
        setBounds(0, 0, 330, 320);

        mps = new ArrayList<>();
        labels = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            JLabel templ = Utils.newLabelString("", 18);
            templ.setOpaque(false);
            labels.add(templ); add(templ);
        }
        for(int i = 0; i < StateManager.itemNames.length; i++){
            ModifyPanel mp = new ModifyPanel(i);
            mp.setOpaque(false);
            mps.add(mp); add(mp);
        }
        confirmBtn = createBigBtn("confirm", new ChangeBtnAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for(ModifyPanel mp:mps){
                    StateManager.itemInAreaNum[mp.getItemId()][curClickAreaId] = mp.getCopyItemInAreaNum();
                    StateManager.itemLastNum[mp.getItemId()] = mp.getCopyItemLastNum();
                }
                StateManager.setMapClickId(-1);
            }
        });
        add(confirmBtn);
        cancelBtn = createBigBtn("cancel", new ChangeBtnAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                StateManager.setMapClickId(-1);
            }
        });
        add(cancelBtn);
        setVi(false);
    }
    private JButton createBigBtn(String text, ChangeBtnAdapter adapter){
        JButton btn = Utils.getButton(text, 20, 10);
        btn.addMouseListener(adapter);
        btn.addMouseMotionListener(adapter);
        btn.setOpaque(false);
        return btn;
    }

    public void setVi(boolean b){
        setOpaque(false);
        for(JLabel label: labels){
            label.setOpaque(b);
            label.revalidate();
            label.repaint();
        }
        for(ModifyPanel mp:mps){
            mp.setVi(b);
            mp.revalidate();
            mp.repaint();
        }
        confirmBtn.setVisible(b);
        confirmBtn.revalidate();
        confirmBtn.repaint();
        cancelBtn.setVisible(b);
        cancelBtn.revalidate();
        cancelBtn.repaint();
    }

    public boolean calEnoughGold(int itemId){
        //System.out.println(StateManager.getGold());
        int lastGold = StateManager.getGold();
        for(ModifyPanel mp: mps){
            lastGold -= StateManager.itemCosts[itemId] * mp.getCopyItemInAreaNum();
        }
        lastGold -= StateManager.itemCosts[itemId];
        //System.out.printf("last gold: %d\n", lastGold);
        return lastGold >= 0;
    }
}
