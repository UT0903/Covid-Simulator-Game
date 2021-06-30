package panels;

import Game.*;
import panels.DetailPanelComponent.AreaDetailPanel;
import panels.DetailPanelComponent.AreaModifyPanel;
import panels.DetailPanelComponent.ItemDetailPanel;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JLayeredPane implements ItemStateListener, MapStateListener {
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
        setPreferredSize(new Dimension(330, 320));
        setName("DetailPanel");
        setSize(330, 320);
        //setOpaque(true);
        //setLocation(750, 470);
        setBackground(new Color(255, 246, 143, 200));
        StateManager.addItemStateListener(this);
        StateManager.addMapStateListener(this);
        adp = new AreaDetailPanel();
        amp = new AreaModifyPanel();
        idp = new ItemDetailPanel();

        add(adp, Integer.valueOf(0)); add(amp, Integer.valueOf(1)); add(idp, Integer.valueOf(2));
    }


    @Override
    public void onItemHoverChanged(int prevId, int newId) {
        curHoverItemId = newId;
        System.out.printf("onItemHoverChanged %d\n", newId);
        if(curClickAreaId != -1){
            idp.update(newId);
            setVi(curLayer, false);
            setVi(2, true);
            curLayer = 2;
        }
    }

    @Override
    public void onItemClickChanged(int prevId, int newId) {}
    private void setVi(int i, boolean b){
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
        System.out.printf("onAreaHoverChanged %d\n", newId);
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

        /*if(newId != -1){
            amp.update(curClickAreaId);
            setVi(curLayer, false);
            setVi(1, true);
            curLayer = 1;
        }*/
    }


}
