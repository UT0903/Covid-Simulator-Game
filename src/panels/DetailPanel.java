package panels;

import Game.*;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class DetailPanel extends JPanel implements ItemStateListener, MapStateListener {
    private int curClickItemId = -1;
    private int curHoverItemId = -1;
    private int curHoverAreaId = -1;
    private int curClickAreaId = -1;
    private List<Integer> copyItemInAreaNum, copyItemLastNum;
    private List<MyNumChangeBtn> incBtns = new ArrayList<>(), decBtns = new ArrayList<>();
    private JButton confirmBtn, cancelBtn;
    private void updateCopyItemInAreaNum(int itemId, int delta){
        int num = copyItemInAreaNum.get(itemId) + delta;
        if(num <= StateManager.itemLastNum[itemId] && num >= 0 &&
                (delta < 0 || calEnoughGold(itemId, delta))) {
            //System.out.println("success");
            copyItemInAreaNum.set(itemId, num);
            copyItemLastNum.set(itemId, copyItemLastNum.get(itemId) - delta);
        }
        //System.out.println(copyItemInAreaNum);
    }
    private boolean calEnoughGold(int itemId, int delta){
        //System.out.println(StateManager.getGold());
        int lastGold = StateManager.getGold();
        for(int i = 0; i < copyItemLastNum.size(); i++){
            lastGold -= StateManager.itemCosts[i] * copyItemInAreaNum.get(i);
        }
        lastGold -= delta * StateManager.itemCosts[itemId];
        //System.out.printf("last gold: %d\n", lastGold);
        return lastGold >= 0;
    }
    public DetailPanel(){
        super();
        copyItemInAreaNum = new ArrayList<>();
        copyItemLastNum = new ArrayList<>();
        for(int i = 0; i < StateManager.itemNames.length; i++){
            copyItemInAreaNum.add(0);
            copyItemLastNum.add(0);
            MyNumChangeBtn decBtn = new MyNumChangeBtn("-", i, new MyMouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    MyNumChangeBtn mb = (MyNumChangeBtn)e.getComponent();
                    updateCopyItemInAreaNum(mb.itemId, -1);
                    reRender();
                }
            });
            decBtns.add(decBtn);
            MyNumChangeBtn incBtn = new MyNumChangeBtn("+", i, new MyMouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    MyNumChangeBtn mb = (MyNumChangeBtn)e.getComponent();
                    updateCopyItemInAreaNum(mb.itemId, 1);
                    reRender();
                }
            });
            incBtns.add(incBtn);
        }

        confirmBtn = Utils.getButton("confirm", 20, 10);
        MouseAdapter confirmAdded = new MyMouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int j = 0; j < copyItemInAreaNum.size(); j++){
                    StateManager.itemInAreaNum[j][curClickAreaId] = copyItemInAreaNum.get(j);
                    StateManager.itemLastNum[j] = copyItemLastNum.get(j);
                    //copyItemInAreaNum.set(j, 0);
                }
                StateManager.setMapClickId(-1);
            }

        };
        confirmBtn.addMouseListener(confirmAdded);
        confirmBtn.addMouseMotionListener(confirmAdded);

        cancelBtn = Utils.getButton("cancel", 20, 10);
        MouseAdapter added = new MyMouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                StateManager.setMapClickId(-1);
            }

        };
        cancelBtn.addMouseListener(added);
        cancelBtn.addMouseMotionListener(added);


        setName("DetailsPanel");
        setSize(330, 320);
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLocation(750, 470);
        setBackground(new Color(255, 246, 143, 200));
        // setVisible(true);
    }

    private void reRender(){
        for(Component c: getComponents()){
            remove(c);
        }

        if(curClickAreaId == -1){
            if(curHoverItemId != -1){
                setLayout(new GridLayout(0, 1));
                add(Utils.newLabelString("名稱: " + StateManager.itemNames[curHoverItemId], 18));
                add(Utils.newLabelString("功能: " + StateManager.itemFunctions[curHoverItemId], 18));
                add(Utils.newLabelString( "花費: "+ StateManager.itemCosts[curHoverItemId], 18));
                add(Utils.newLabelString( "剩餘數量: "+ StateManager.itemLastNum[curHoverItemId], 18));
            }
            else if(curHoverAreaId != -1){
                setLayout(new GridLayout(0, 2));
                add(Utils.newLabelString("地區: " + StateManager.areaNames[curHoverAreaId], 18));
                add(Utils.newLabelString("人口數: " + StateManager.areaPeopleNum[curHoverAreaId], 18));
                add(Utils.newLabelString("感染人數: " + StateManager.areaPeopleInfectedNum[curHoverAreaId], 18));
                add(Utils.newLabelString("死亡人數: " + StateManager.areaPeopleDeadNum[curHoverAreaId], 18));
                for(int i = 0; i < StateManager.itemNames.length; i++){
                    add(Utils.newLabelString(StateManager.itemNames[i] + ": " + StateManager.itemInAreaNum[i][curHoverAreaId], 12));
                }
            }
        }
        else{
            setLayout(new GridLayout(0, 2));
            add(Utils.newLabelString("地區: " + StateManager.areaNames[curClickAreaId], 18));
            add(Utils.newLabelString("人口數: " + StateManager.areaPeopleNum[curClickAreaId], 18));
            add(Utils.newLabelString("感染人數: " + StateManager.areaPeopleInfectedNum[curClickAreaId], 18));
            add(Utils.newLabelString("死亡人數: " + StateManager.areaPeopleDeadNum[curClickAreaId], 18));
            for(int i = 0; i < StateManager.itemNames.length; i++) {
                JPanel p = new JPanel();
                p.setBackground(Color.lightGray);

                JLabel jl =  Utils.newLabelString(StateManager.itemNames[i] + ":" +
                        copyItemInAreaNum.get(i) + "/" + StateManager.itemLastNum[i], 12);
                p.add(decBtns.get(i)); p.add(jl); p.add(incBtns.get(i));
                add(p);
            }

            add(confirmBtn);add(cancelBtn);

        }

        revalidate();
        repaint();
    }
    public void InitCopy(){
        for(int i = 0; i < StateManager.itemNames.length; i++) {
            copyItemInAreaNum.set(i, StateManager.itemInAreaNum[i][curClickAreaId]);
            copyItemLastNum.set(i, StateManager.itemLastNum[i]);
        }
    }
    public class MyNumChangeBtn extends JButton{
        public int itemId;
        public boolean enable;
        public MyNumChangeBtn(String text, int itemId, MouseAdapter adapter){
            super(text);
            setBackground(Color.gray);
            setForeground(Color.black);
            this.itemId = itemId;
            //decBtn.setPreferredSize(new Dimension(15, 15));
            setMargin( new Insets(10, 10, 10, 10) );
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
        }

    }
    @Override
    public void onItemHoverChanged(int prevId, int newId) {
        curHoverItemId = newId;
        if(curClickAreaId == -1){
            reRender();
        }
    }

    @Override
    public void onItemClickChanged(int prevId, int newId) {
//        System.out.printf("curClickItemId %d\n", newId);
//        curClickItemId = newId;
//        reRender();
    }

    @Override
    public void onAreaHoverChanged(int prevId, int newId) {
        curHoverAreaId = newId;
        //System.out.printf("onAreaHoverChanged %d %d\n", newId, curClickAreaId);
        if(curClickAreaId == -1){
            reRender();
        }
    }

    @Override
    public void onAreaClickChanged(int prevId, int newId) {
        //System.out.printf("curClickAreaId %d\n", newId);
        curClickAreaId = newId;
        if(newId != -1){
            InitCopy();
        }
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
