package panels;

import Game.*;
import panels.DetailPanelComponent.AreaDetailPanel;
import panels.DetailPanelComponent.AreaModifyPanel;
import panels.DetailPanelComponent.ItemDetailPanel;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JLayeredPane implements ItemStateListener, MapStateListener, ItemNumListener, PeopleNumListener {
    private int curClickItemId = -1;
    private int curHoverItemId = -1;
    private int curHoverAreaId = -1;
    private int curClickAreaId = -1;
    private AreaDetailPanel adp;
    private AreaModifyPanel amp;
    private ItemDetailPanel idp;
    private int curLayer = -1;
    public DetailPanel(){
        super(); //self init
//        setPreferredSize(new Dimension(330, 320));
        setName("DetailPanel");
//        setSize(330, 320);
        setOpaque(true);
        //setLocation(750, 470);
        setBackground(new Color(255, 246, 143, 255));
        StateManager.addItemStateListener(this);
        StateManager.addMapStateListener(this);
        adp = new AreaDetailPanel();
        amp = new AreaModifyPanel();
        idp = new ItemDetailPanel();
        StateManager.addItemNumListeners(this);
        StateManager.addPeopleNumListener(this);
        add(adp, Integer.valueOf(0)); add(amp, Integer.valueOf(1)); add(idp, Integer.valueOf(2));
    }


    @Override
    public void onItemHoverChanged(int prevId, int newId) {
        curHoverItemId = newId;
        //System.out.printf("onItemHoverChanged %d\n", newId);
        //System.out.println(curClickAreaId);
        if(curClickAreaId == -1 && newId != -1){
            idp.update(newId);
            setVi(curLayer, false);
            setVi(2, true);
            curLayer = 2;
        }
    }

    @Override
    public void onItemClickChanged(int prevId, int newId) {}
    private void setVi(int i, boolean b){
        //System.out.println(i);
        //System.out.println(b);
        if(i == 0){
            adp.setVi(b);
        }
        else if(i == 1){
            amp.setVi(b);
        }
        else if(i == 2){
            idp.setVi(b);
        }
    }
    @Override
    public void onAreaHoverChanged(int prevId, int newId) {
        curHoverAreaId = newId;
        //System.out.printf("onAreaHoverChanged %d\n", newId);
        if(curClickAreaId == -1 && curHoverAreaId != -1){
            adp.update(curHoverAreaId);
            setVi(curLayer, false);
            setVi(0, true);
            curLayer = 0;
        }
    }

    @Override
    public void onAreaClickChanged(int prevId, int newId) {
        //System.out.printf("curClickAreaId %d\n", newId);
        curClickAreaId = newId;

        if(curClickAreaId != -1){
            amp.update(curClickAreaId);
            setVi(curLayer, false);
            setVi(1, true);
            curLayer = 1;
        }
        else{
            setVi(curLayer, false);
            curLayer = -1;
        }
    }


    @Override
    public void onItemInAreaNumChanged(int itemID, int groupID, int prevNum, int curNum) {
        //System.out.printf("onItemInAreaNumChanged\n");
        if(curLayer == 0 && curHoverAreaId == groupID){ //adp
            adp.listenerUpdate(curHoverAreaId);
        }
        else if(curLayer == 1 && curClickAreaId == groupID){ //amp
            amp.listenerUpdate(curClickAreaId, itemID);
        }
    }

    @Override
    public void onItemLastNumChanged(int itemID, int prevNum, int curNum) {
        //System.out.printf("onItemLastNumChanged\n");
        if(curLayer == 1){ //amp
            amp.listenerUpdate(curClickAreaId, itemID);
        }
        else if(curLayer == 2 && curHoverItemId == itemID){ //idp
            idp.listenerUpdate(curHoverItemId);
        }

    }

    @Override
    public void onPeopleInfectedNumChanged(int groupID, int prevNum, int newNum) {
        if(curLayer == 0 && curHoverAreaId == groupID){ //adp
            adp.listenerUpdate(curHoverAreaId);
            adp.listenerPeopleNumUpdate(curHoverAreaId);
        }
    }

    @Override
    public void onPeopleDeadNumChanged(int groupID, int prevNum, int newNum) {
        if(curLayer == 0 && curHoverAreaId == groupID){ //adp
            adp.listenerUpdate(curHoverAreaId);
            adp.listenerPeopleNumUpdate(curHoverAreaId);
        }
    }
}
