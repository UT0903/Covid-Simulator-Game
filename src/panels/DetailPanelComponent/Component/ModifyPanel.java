package panels.DetailPanelComponent.Component;

import Game.StateManager;
import panels.DetailPanelComponent.AreaModifyPanel;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyPanel extends JPanel{
    private NumChangeBtn incBtn, decBtn;
    private JLabel jl;
    private int itemId;

    public int getItemId() {
        return itemId;
    }

    private int copyItemLastNum, copyItemInAreaNum;
    public int getCopyItemLastNum() {
        return copyItemLastNum;
    }

    public int getCopyItemInAreaNum() {
        return copyItemInAreaNum;
    }

    public void setCopyItemLastNum(int copyItemLastNum) {
        this.copyItemLastNum = copyItemLastNum;
        jl.setText(StateManager.itemNames[itemId] + ":" + copyItemInAreaNum + "/" + StateManager.itemLastNum[itemId]);
    }

    public void setCopyItemInAreaNum(int copyItemInAreaNum) {
        this.copyItemInAreaNum = copyItemInAreaNum;
    }


    public ModifyPanel(int itemId){
        this.itemId = itemId;
        setBackground(Color.lightGray);

        jl = Utils.newLabelString("", 12);

        incBtn = new NumChangeBtn("+", itemId, new ChangeBtnAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                AreaModifyPanel amp = (AreaModifyPanel)e.getComponent().getParent().getParent();
                if(copyItemInAreaNum + 1 <= StateManager.itemLastNum[itemId] &&
                        amp.calEnoughGold(itemId)){
                    copyItemInAreaNum += 1;
                }
            }
        });
        decBtn = new NumChangeBtn( "-", itemId, new ChangeBtnAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(copyItemInAreaNum - 1 >= 0){
                    copyItemInAreaNum -= 1;
                }
            }
        });
        setVi(false);
        add(decBtn); add(jl); add(incBtn);
    }
    public void update(){
        jl.setText(StateManager.itemNames[itemId] + ":" + copyItemInAreaNum + "/" + StateManager.itemLastNum[itemId]);
    }
    public void setCopy(int curClickAreaId){
        copyItemInAreaNum = StateManager.itemInAreaNum[itemId][curClickAreaId];
        copyItemLastNum = StateManager.itemLastNum[itemId];
    }
    public void setVi(boolean b){
        setOpaque(false);
        incBtn.setVisible(b);
        incBtn.revalidate();
        incBtn.repaint();
        decBtn.setVisible(b);
        decBtn.revalidate();
        decBtn.repaint();
        jl.setVisible(b);
        jl.revalidate();
        jl.repaint();
    }
}
