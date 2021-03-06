package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.Area;
import components.Virus;
import utils.Date;

import javax.swing.*;

public class StateManager {
    private static ArrayList<ArrayList<Virus>> viruses = new ArrayList<ArrayList<Virus>>(12);
    private static ArrayList<ArrayList<Virus>> notChosen = new ArrayList<ArrayList<Virus>>(12);
    private static Integer[] total = new Integer[12];
    private static int totalPeople = 0;
    private static int totalDead = 0;
    private static int totalInfected = 0;

    // Win/Lose condition
    private static int prevDead = 0;
    private static int prevInfected = 0;
    private static int controlledTime = 0;

    public static void updateStatus() {
        if (totalInfected + totalDead < prevDead + prevInfected) {
            controlledTime++;
        }
        else {
            prevInfected = totalInfected;
            prevDead = totalDead;
            controlledTime = 0;
        }
    }

    public static void checkCondition() {
        // Death rate > 0.2
        if (controlledTime > 14)
            setGameState(GameState.WIN);
        else if (totalDead * 5 > totalPeople)
            setGameState(GameState.LOSE);
        else if ((totalInfected + totalDead) * 3 > totalPeople)
            setGameState(GameState.LOSE);
    }

    // Gold management
    private static final int initGold = 300;
    private static final int initIncomePerHour = 50;
    private static int gold;
    private static int incomePerHour;
    public static List<GoldListener> goldListeners = new ArrayList<>();
    public static void addGoldListener(GoldListener gl) { goldListeners.add(gl); }
    public static void updateGold() {
        gold += incomePerHour;
        for (GoldListener gl: goldListeners)
            gl.onGoldChanged(gold);
    }
    public static void updateGold(int g) {
        gold = g;
        for (GoldListener gl: goldListeners)
            gl.onGoldChanged(gold);
    }

    public static void updateIncome() { incomePerHour += 1; }
    public static void updateIncome(int income) { incomePerHour = income; }
    public static int getGold() { return gold; }

    // Date management
    private static Date date = new Date();
    private static List<DateListener> dateListeners = new ArrayList<>();
    public static void addDateListeners(DateListener dl) { dateListeners.add(dl); }
    public static void updateDate() {
        date.update();
        for (DateListener dl: dateListeners)
            dl.onDateChanged(date);
    }

    //Game states management
    private static GameState curGameState = GameState.INIT;
    public static List<GameStateListener> gameStateListeners = new ArrayList<>();
    public static void addGameStateListener(GameStateListener toAdd){
        gameStateListeners.add(toAdd);
    }
    public static void setGameState(GameState newState){
        if(newState != curGameState){
            for(GameStateListener sl: gameStateListeners){
                sl.onGameStateChanged(curGameState, newState);
            }
            curGameState = newState;
        }
    }

    //Map states management
    private static int curMapHoverId = -1;
    public static List<MapStateListener> mapStateListeners = new ArrayList<>();
    public static void addMapStateListener(MapStateListener toAdd){
        mapStateListeners.add(toAdd);
    }
    public static int getCurMapHoverId(){
        return curMapHoverId;
    }
    public static void setMapHoverId(int newMapHoverId){
        if(newMapHoverId != curMapHoverId){
            for(MapStateListener sl: mapStateListeners){
                sl.onAreaHoverChanged(curMapHoverId, newMapHoverId);
            }
            curMapHoverId = newMapHoverId;
        }
    }
    private static int curMapClickId = -1;
    public static int getCurMapClickCliId(){
        return curMapClickId;
    }
    public static void setMapClickId(int newMapClickId){
        if(newMapClickId != curMapClickId){
            for(MapStateListener sl: mapStateListeners){
                sl.onAreaClickChanged(curMapClickId, newMapClickId);
            }
            curMapClickId = newMapClickId;
        }
    }

    //Item states management
    private static int curHoverItemId = -1;
    private static int curClickItemId = -1;
    public static List<ItemStateListener> itemStateListeners = new ArrayList<>();
    public static void addItemStateListener(ItemStateListener toAdd){
        itemStateListeners.add(toAdd);
    }
    public static int getCurHoverItemId(){
        return curHoverItemId;
    }
    public static void setItemHoverId(int newHoverItemId){
        if(newHoverItemId != curHoverItemId){
            for(ItemStateListener sl: itemStateListeners){
                sl.onItemHoverChanged(curHoverItemId, newHoverItemId);
            }
            curHoverItemId = newHoverItemId;
        }
    }
    public static int getCurClickItemId(){
        return curClickItemId;
    }
    public static void setItemClickId(int newClickItemId){
        if(newClickItemId != curClickItemId){
            for(ItemStateListener sl: itemStateListeners){
                sl.onItemClickChanged(curClickItemId, newClickItemId);
            }
            curHoverItemId = newClickItemId;
        }
    }
    public static final String[] labelNames = {
            "ambulance.gif",
            "canopy.png",
            "hospital.png",
            "mask.gif",
            "spray.gif",
            "syringe.gif"
    };
    public static final String[] itemNames = {
            "?????????",
            "?????????",
            "??????",
            "??????",
            "??????",
            "??????"
    };
    public static final String[] itemFunctions = {
            "????????????????????????", //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            "?????????????????????20%",
            "???????????????10%",
            "???????????????10%",
            "?????????????????????20%",
            "????????????????????????5%"
    };
    public static Integer[] itemCosts = {
            50,
            300,
            1000,
            10,
            50,
            100
    };
    public static Integer[] itemLastNum = {
            10,
            10,
            10,
            10,
            10,
            0
    };

    //Area states management
    public static Integer[][] itemInAreaNum = {
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0}
    };
    public static void setItemInAreaNum(List<Integer> newItemInAreaNum){
        int goldChange = 0;
        for (int i = 0; i < 6; i++){
            goldChange += (newItemInAreaNum.get(i) - itemInAreaNum[i][curMapClickId]) * itemCosts[i];
            itemInAreaNum[i][curMapClickId] = newItemInAreaNum.get(i);
        }
        updateGold(gold + goldChange);
    }

    public static final String[] areaNames = {
        "????????????",
        "????????????",
        "????????????",
        "???????????????",
        "??????",
        "????????????",
        "????????????",
        "????????????",
        "??????",
        "????????????",
        "??????",
        "????????????"
    };
    public static Integer[] areaPeopleNum = {
        100,
        200,
        300,
        400,
        500,
        600,
        700,
        800,
        900,
        1000,
        1100,
        1200,
    };
    public static Integer[] areaPeopleInfectedNum = {
	    0,0,0,0,0,0,0,0,0,0,0,0
    };
    public static Integer[] areaPeopleDeadNum = {
	    0,0,0,0,0,0,0,0,0,0,0,0
    };
    private static Integer[] areaSpreadTime = {
        10,10,10,10,10,10,10,10,10,10,10,10
    };
    private static Integer[] areaTimeCount = {
        0,0,0,0,0,0,0,0,0,0,0,0
    };

    private static double[] areaSpreadProbability = {
        1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0
    };

    private static double[] areaDeadProbability = {
        0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03
    };

    private static double[] areaRecoverProbability = {
        0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2 ,0.2, 0.2, 0.2
    };

    private static List<ItemNumListener> itemNumListeners = new ArrayList<>();
    public static void addItemNumListeners(ItemNumListener inl) { itemNumListeners.add(inl); }
    public static void updateItems() {
        updateItemLastNum(0, itemLastNum[0] + 2);
        updateItemLastNum(1, itemLastNum[1] + 2);
        updateItemLastNum(2, itemLastNum[2] + 1);
        updateItemLastNum(3, itemLastNum[3] + 10);
        updateItemLastNum(4, itemLastNum[4] + 15);
        updateItemLastNum(5, itemLastNum[5] + 5);
    }

    public static void updateItemInAreaNum(int itemID, int groupID, int newNum) {
        int prevNum = itemInAreaNum[itemID][groupID];
        itemInAreaNum[itemID][groupID] = newNum;
        for (ItemNumListener inl: itemNumListeners)
            inl.onItemInAreaNumChanged(itemID, groupID, prevNum, newNum);
    }

    public static void updateItemLastNum(int itemID, int newNum) {
        int prevNum = itemLastNum[itemID];
        itemLastNum[itemID] = newNum;
        for (ItemNumListener inl: itemNumListeners)
            inl.onItemLastNumChanged(itemID, prevNum, newNum);
    }

    public static void setAmbulance(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[0][groupID];
        updateItemInAreaNum(0, groupID, nowItemInAreaNum);
        updateItemLastNum(0, nowItemLastNum);
        updateGold(gold - amount * itemCosts[0]);
        areaDeadProbability[groupID] = Math.max(0.01, areaDeadProbability[groupID] * Math.pow(0.95, amount));
        for (Integer neighbor: Area.neighborhood[groupID])
            areaDeadProbability[neighbor] = Math.max(0.01, areaDeadProbability[neighbor] * Math.pow(0.97, amount));
    }

    public static void setCanopy(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[1][groupID];
        updateItemInAreaNum(1, groupID, nowItemInAreaNum);
        updateItemLastNum(1, nowItemLastNum);
        updateGold(gold - amount * itemCosts[1]);
        areaDeadProbability[groupID] = Math.max(0.01, areaDeadProbability[groupID] * Math.pow(0.8, amount));
        StateManager.CanopyTimer canopyTimer = new StateManager.CanopyTimer(amount, groupID);
        canopyTimer.start();
    }

    private static class CanopyTimer implements ActionListener {
        private int amount, groupID;
        private Timer timer = new Timer(60000, this);
        public CanopyTimer(int amount, int groupID) {
            this.amount = amount;
            this.groupID = groupID;
            this.timer.setRepeats(false);
        }
        public void start() { this.timer.start(); }
        @Override
        public void actionPerformed(ActionEvent e) {
            areaDeadProbability[this.groupID] = Math.min(0.5, areaDeadProbability[this.groupID] * Math.pow(1.25, amount));
            updateItemInAreaNum(1, this.groupID, itemInAreaNum[1][this.groupID] - this.amount);
        }
    }

    public static void setHospital(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[2][groupID];
        updateItemInAreaNum(2, groupID, nowItemInAreaNum);
        updateItemLastNum(2, nowItemLastNum);
        updateGold(gold - amount * itemCosts[2]);
        areaDeadProbability[groupID] = Math.max(0.01, areaDeadProbability[groupID] * Math.pow(0.9, amount));
        areaRecoverProbability[groupID] = Math.min(1.0, areaRecoverProbability[groupID] * Math.pow(1.05, amount));
    }

    public static void setMask(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[3][groupID];
        updateItemInAreaNum(3, groupID, nowItemInAreaNum);
        updateItemLastNum(3, nowItemLastNum);
        updateGold(gold - amount * itemCosts[3]);
        areaSpreadProbability[groupID] = Math.min(1.0, areaSpreadProbability[groupID] * Math.pow(0.9, amount));
    }

    public static void setSpray(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[4][groupID];
        updateItemInAreaNum(4, groupID, nowItemInAreaNum);
        updateItemLastNum(4, nowItemLastNum);
        updateGold(gold - amount * itemCosts[4]);
        areaSpreadProbability[groupID] = Math.min(1.0, areaSpreadProbability[groupID] * Math.pow(0.8, amount));
        StateManager.SprayTimer sprayTimer = new StateManager.SprayTimer(amount, groupID);
        sprayTimer.start();
    }

    private static class SprayTimer implements ActionListener {
        private int amount, groupID;
        private Timer timer = new Timer(60000, this);
        public SprayTimer(int amount, int groupID) {
            this.amount = amount;
            this.groupID = groupID;
            this.timer.setRepeats(false);
        }
        public void start() { this.timer.start(); }
        @Override
        public void actionPerformed(ActionEvent e) {
            areaSpreadProbability[this.groupID] = Math.min(1.0, areaSpreadProbability[this.groupID] * Math.pow(1.25, amount));
            updateItemInAreaNum(4, this.groupID, itemInAreaNum[4][this.groupID] - this.amount);
        }
    }

    public static void setSyringe(int nowItemInAreaNum, int nowItemLastNum, int groupID) {
        int amount = nowItemInAreaNum - itemInAreaNum[5][groupID];
        updateItemInAreaNum(5, groupID, nowItemInAreaNum);
        updateItemLastNum(5, nowItemLastNum);
        updateGold(gold - amount * itemCosts[5]);
        areaDeadProbability[groupID] = Math.max(0.01, areaDeadProbability[groupID] * Math.pow(0.95, amount));
        areaSpreadProbability[groupID] = Math.max(0.1, areaSpreadProbability[groupID] * Math.pow(0.95, amount));
        areaRecoverProbability[groupID] = Math.min(1.0, areaRecoverProbability[groupID] * Math.pow(1.05, amount));
    }

    //People num management
    private static List<PeopleNumListener> peopleNumListeners = new ArrayList<>();
    public static void addPeopleNumListener(PeopleNumListener pnl) { peopleNumListeners.add(pnl); }
    public static void updatePeopleInfectedNum(int groupID, int newNum) {
        int prevNum = areaPeopleInfectedNum[groupID];
        areaPeopleInfectedNum[groupID] = newNum;
        for (PeopleNumListener pnl: peopleNumListeners)
            pnl.onPeopleInfectedNumChanged(groupID, prevNum, newNum);
    }
    public static void updatePeopleDeadNum(int groupID, int newNum) {
        int prevNum = areaPeopleDeadNum[groupID];
        areaPeopleDeadNum[groupID] = newNum;
        for (PeopleNumListener pnl: peopleNumListeners)
            pnl.onPeopleDeadNumChanged(groupID, prevNum, newNum);
    }


    public static void updateAreaDeadProbability() {
        for (int i = 0; i < 12; i++)
            areaDeadProbability[i] = Math.min(1.0, areaDeadProbability[i] * (1 + (double) areaPeopleInfectedNum[i] / (double) areaPeopleNum[i] / 2.0));
    }

    public static void updateAreaPeopleDeadNum(){
        for (int i = 0; i < 12; i++) {
            double rand = Math.random() / 2.0 + 0.75; // 0.75 ~ 1.25
            double finalDeadProbability = Math.min(areaDeadProbability[i] * rand, 1.0);
            int death = (int) ((double) areaPeopleInfectedNum[i] * finalDeadProbability);
            // areaPeopleInfectedNum[i] = (int) (areaPeopleNum[i] * ((double) viruses.get(i).size() / total[i]));
            updatePeopleDeadNum(i, areaPeopleDeadNum[i] + death);
            updatePeopleInfectedNum(i, areaPeopleInfectedNum[i] - death);
            // areaPeopleDeadNum[i] += death;
            // areaPeopleInfectedNum[i] -= death;
            totalDead += death;
            areaPeopleNum[i] -= death;
            totalInfected -= death;
            ArrayList<Virus> areaVirus = viruses.get(i);
            List<Virus> dead = new ArrayList<>();
            Collections.shuffle(areaVirus);
            for (int j = 0; j < death; j++) {
                Virus virus = areaVirus.get(j);
                dead.add(virus);
                virus.setBackground(Color.black);
                virus.revalidate();
                virus.repaint();
            }
            for (Virus d: dead)
                areaVirus.remove(d);
        }
    }
    //Virus states management
    private static List<VirusListener> virusListeners = new ArrayList<>();
    public static void addVirusListener(VirusListener vl) { virusListeners.add(vl); }
    public static int getAmount() {
        int a = 0;
        for (int j = 0; j < 12; j++){
            a += viruses.get(j).size();
        }
        return a;
    }
    public static ArrayList<ArrayList<Virus>> getVirus() { return viruses; }
    public static double getAreaPercentage(int index) { return ((double) viruses.get(index).size() / (double) total[index]); }
    public static int getPercentage() { return (int) ((double) getAmount() / (150 * 120) * 100); }

    public static void spreadVirus(){
        List<Virus> spreadList = new ArrayList<>();
        for (int j = 0; j < 12; j++) {
            areaTimeCount[j] += 1;
            if (areaTimeCount[j].equals(areaSpreadTime[j])) {
                double R0 = (areaSpreadProbability[j] * Math.pow((double)notChosen.get(j).size() / total[j], 2) *  ((double)10 / areaSpreadTime[j]));
                int n = Math.min(notChosen.get(j).size() - 1, (int) (viruses.get(j).size() * R0));
                updatePeopleInfectedNum(j, areaPeopleInfectedNum[j] + n);
                // areaPeopleInfectedNum[j] += n;
                totalInfected += n;
                Collections.shuffle(notChosen.get(j));
                List<Virus> chosen = new ArrayList<>();
                for (int c = 0; c < n; c++) {
                    Virus virus = notChosen.get(j).get(c);
                    spreadList.add(virus);
                    viruses.get(j).add(virus);
                    chosen.add(virus);
                }
                for (Virus virus: chosen)
                    notChosen.get(j).remove(virus);
                areaTimeCount[j] = 0;
            }
        }
        for (VirusListener vl: virusListeners)
            vl.onVirusIncreased(spreadList);
    }

    public static void addVirus(){
        int rand = (int) (Math.random() * 12);
        int i = Math.min(notChosen.get(rand).size() - 1, (int) Math.round(Math.random() * notChosen.get(rand).size()));
        updatePeopleInfectedNum(rand, areaPeopleInfectedNum[rand] + 1);
        // areaPeopleInfectedNum[rand]++;
        totalInfected++;
        Virus virus = notChosen.get(rand).get(i);
        viruses.get(rand).add(virus);
        notChosen.get(rand).remove(virus);
        List<Virus> increasedVirus = new ArrayList<>();
        increasedVirus.add(virus);
        for (VirusListener vl: virusListeners)
            vl.onVirusIncreased(increasedVirus);
    }

    public static void updateAreaRecoveredNum() {
        List<Virus> recoverList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double rand = Math.random() / 2.0 + 0.75; // 0.75 ~ 1.25
            double finalRecoverProbability = Math.min(areaRecoverProbability[i] * rand, 1.0);
            int recover = (int) (areaPeopleInfectedNum[i] * finalRecoverProbability);
            updatePeopleInfectedNum(i, areaPeopleInfectedNum[i] - recover);
            // areaPeopleInfectedNum[i] -= recover;
            totalInfected -= recover;
            List<Virus> areaVirus = viruses.get(i);
            List<Virus> l = new ArrayList<>();
            Collections.shuffle(areaVirus);
            for (int j = 0; j < recover; j++) {
                Virus virus = areaVirus.get(j);
                recoverList.add(virus);
                l.add(virus);
                notChosen.get(i).add(virus);
            }
            for (Virus virus: l)
                areaVirus.remove(virus);
        }
        for (VirusListener vl: virusListeners)
            vl.onVirusDecreased(recoverList);
    }

    public static void initGame(List<Virus> virusLabels) {
        setGameState(GameState.INIT);
        updateGold(initGold);
        for (DateListener dl: dateListeners)
            dl.onDateChanged(date);
        incomePerHour = initIncomePerHour;
        for (int i = 0; i < 12; i++){
            ArrayList<Virus> v = new ArrayList<>();
            viruses.add(v);
            ArrayList<Virus> nC = new ArrayList<>();
            notChosen.add(nC);
            total[i] = 0;
        }
        for (Virus virus: virusLabels) {
            notChosen.get(virus.getGroupID()).add(virus);
            total[virus.getGroupID()]++;
        }
        for (int i = 0; i < 12; i++) {
            areaPeopleNum[i] = total[i];
            totalPeople += total[i];
        }
    }

    public static void startGame() { setGameState(GameState.INGAME); }
}
