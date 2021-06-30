package panels.DetailPanelComponent;

import Game.StateManager;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDetailPanel extends JPanel {
    private List<JLabel> labels;
    public ItemDetailPanel(){
        setSize(330, 320);
        setBounds(0, 0, 330, 320);
        setLayout(new GridLayout(0, 1));
        labels = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            JLabel templ = Utils.newLabelString("", 18);
            templ.setVisible(false);
            add(templ); labels.add(templ);
        }
        setVi(false);
    }
    public void update(int curHoverItemId){
      labels.get(0).setText("名稱:"  + StateManager.itemNames[curHoverItemId]);
      labels.get(1).setText("功能: " + StateManager.itemFunctions[curHoverItemId]);
      labels.get(2).setText("花費: "+ StateManager.itemCosts[curHoverItemId]);
      labels.get(3).setText("剩餘數量: "+ StateManager.itemLastNum[curHoverItemId]);
    }
    public void setVi(boolean b){
        setOpaque(false);
        for(JLabel label: labels) {
            label.setVisible(b);
            label.revalidate();
            label.repaint();
        }
    }
}
