package panels.DetailPanelComponent;

import Game.StateManager;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDetailPanel extends JPanel {
    private List<JLabel> labels;
    public AreaDetailPanel(){
        setSize(330, 320);

        setBounds(0, 0, 330, 320);
        setLayout(new GridLayout(0, 2));
        labels = new ArrayList<>();
        JLabel templ;
        for(int i = 0; i < 4; i++){
            templ = Utils.newLabelString("", 18);
            templ.setOpaque(false);
            add(templ); labels.add(templ);
        }
        for(int i = 0; i < StateManager.itemNames.length; i++){
            templ = Utils.newLabelString("", 12);
            templ.setOpaque(false);
            add(templ); labels.add(templ);
        }
        setVi(false);
    }
    public void update(int curHoverAreaId){
        System.out.println(curHoverAreaId);
        labels.get(0).setText("地區: " + StateManager.areaNames[curHoverAreaId]);
        labels.get(1).setText("人口數: " + StateManager.areaPeopleNum[curHoverAreaId]);
        labels.get(2).setText("感染人數: " + StateManager.areaPeopleInfectedNum[curHoverAreaId]);
        labels.get(3).setText("死亡人數: " + StateManager.areaPeopleDeadNum[curHoverAreaId]);
        for(int i = 0; i < StateManager.itemNames.length; i++){
            labels.get(i+4).setText(StateManager.itemNames[i] + ": " + StateManager.itemInAreaNum[i][curHoverAreaId]);
        }
    }
    public void setVi(boolean b){
        setOpaque(false);
        for(JLabel label: labels){
            label.setOpaque(b);
            label.revalidate();
            label.repaint();
        }
    }
}
