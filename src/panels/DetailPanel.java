package panels;

import Game.*;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailPanel extends JPanel implements ItemStateListener, MapStateListener {
    private int curClickItemId = -1;
    private int curHoverItemId = -1;
    private int curHoverAreaId = -1;
    private int curClickAreaId = -1;
    public DetailPanel(){
        super();
        setName("DetailsPanel");
        setSize(330, 320);
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLocation(750, 470);
        setBackground(new Color(255, 246, 143, 200));

        //setPreferredSize(new Dimension(200, 100));
        StateManager.setGameState(GameState.INGAME);
        setVisible(true);
    }

    private void reRender(){
        for(Component c: getComponents()){
            remove(c);
        }

        if(curClickItemId == -1 && curClickAreaId == -1){
            if(curHoverItemId != -1){
                setLayout(new GridLayout(4, 1));
                add(Utils.newLabelString("名稱: " + StateManager.itemNames[0], 12));
                add(Utils.newLabelString("功能: " + StateManager.itemFunctions[1], 12));
                add(Utils.newLabelString( "花費: "+ StateManager.itemCosts[curHoverItemId], 12));
                add(Utils.newLabelString( "剩餘數量: "+ StateManager.itemLastNum[curHoverItemId], 12));
            }
            else{
                setLayout(new GridLayout(4, 1));
                add(Utils.newLabelString("地區: " + StateManager.areaNames[curHoverAreaId], 12));
                add(Utils.newLabelString("人口數: " + StateManager.areaPeopleNum[curHoverAreaId], 12));
                add(Utils.newLabelString("感染人數: " + StateManager.areaPeopleInfectedNum[curHoverAreaId], 12));
                add(Utils.newLabelString("死亡人數: " + StateManager.areaPeopleDeadNum[curHoverAreaId], 12));
                //add(Utils.newLabelString("" + StateManager.areaName[curHoverAreaId], 12));
                //add(Utils.newLabelString("" + StateManager.areaName[curHoverAreaId], 12));
            }
        }
        else{

        }

        revalidate();
        repaint();
    }
    @Override
    public void onItemHoverChanged(int prevId, int newId) {
        System.out.printf("curHoverItemId %d\n", newId);
        curHoverItemId = newId;
        reRender();
    }

    @Override
    public void onItemClickChanged(int prevId, int newId) {
        System.out.printf("curClickItemId %d\n", newId);
        curClickItemId = newId;
        reRender();
    }

    @Override
    public void onAreaHoverChanged(int prevId, int newId) {
        System.out.printf("curHoverAreaId %d\n", newId);
        curHoverAreaId = newId;
        reRender();
    }

    @Override
    public void onAreaClickChanged(int prevId, int newId) {
        System.out.printf("curClickAreaId %d\n", newId);
        curClickAreaId = newId;
        reRender();
    }

    public abstract class MyMouseAdapter extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            e.getComponent().setBackground(Color.darkGray);
            //StateManager.setGameState(S);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(Color.gray);
        }
    }
}
