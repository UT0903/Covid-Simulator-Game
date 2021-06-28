package panels;

import Game.*;
import utils.Utils;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
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
            if(curHoverAreaId != -1 || curHoverAreaId != -1){
                setLayout(new GridLayout(3, 1));
                JLabel t = Utils.newLabelString("123", 15);
                add(t);
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
