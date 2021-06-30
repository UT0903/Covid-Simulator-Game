package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.Virus;
import utils.Date;

public class StateManager {
    private static ArrayList<ArrayList<Virus>> viruses = new ArrayList<ArrayList<Virus>>(12);
    private static ArrayList<ArrayList<Virus>> notChosen = new ArrayList<ArrayList<Virus>>(12);
    private static Integer[] total = new Integer[12];

    // Gold management
    private static final int initGold = 1000;
    private static final int initIncomePerHour = 100;
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
            "救護車",
            "快篩站",
            "醫院",
            "口罩",
            "酒精",
            "疫苗"
    };
    public static final String[] itemFunctions = {
            "增加醫院收治範圍", //原本醫院只能減低自己區的死亡率，多了救護車除了可以額外減低自己區的死亡率，也可以減低周圍地區的死亡率
            "暫時降低死亡率20%",
            "降低死亡率10%",
            "降低感染率10%",
            "暫時降低感染率20%",
            "降低感染、死亡率5%"
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
        "五股泰山",
        "三重蘆洲",
        "士林北投",
        "內湖陽明山",
        "板橋",
        "永和中和",
        "文湖新店",
        "萬華中正",
        "大安",
        "信義南港",
        "松山",
        "中山大同"
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
    public static Integer[] areaSpreadTime = {
        10,10,10,10,10,10,10,10,10,10,10,10
    };
    public static Integer[] areaTimeCount = {
        0,0,0,0,0,0,0,0,0,0,0,0
    };

    public static double[] areaSpreadProbability = {
        1.0,1.0,1.0,1.0,1.0,1.0,
        1.0,1.0,1.0,1.0,1.0,1.0
    };
    public static final double deadProbability = 0.01;
    public static void updateAreaPeopleInfectedDeadNum(){
        for (int i = 0; i < 12; i++){
            areaPeopleInfectedNum[i] = (int) (areaPeopleNum[i] * ((double) viruses.get(i).size() / total[i]));
            int death = (int) (areaPeopleInfectedNum[i] * 0.05);
            areaPeopleNum[i] -= death;
            areaPeopleDeadNum[i] += death;
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
    public static int getAreaPercentage(int index) { return (int) ((double) viruses.get(index).size() / (double) total[index]); }
    public static int getPercentage() { return (int) ((double) getAmount() / 16500 * 100); }
    public static void spreadVirus(){
        List<Virus> spreadList = new ArrayList<>();
        for (int j = 0; j < 12; j++) {
            areaTimeCount[j] += 1;
            if (areaTimeCount[j].equals(areaSpreadTime[j])) {
                double R0 = (areaSpreadProbability[j] * Math.pow((double)notChosen.get(j).size() / total[j], 2) *  ((double)10 / areaSpreadTime[j]));
                int n = (int) (viruses.get(j).size() * R0);
                Collections.shuffle(notChosen.get(j));
                for (int c = 0; c < n; c++) {
                    Virus virus = notChosen.get(j).get(0);
                    spreadList.add(virus);
                    viruses.get(j).add(virus);
                    notChosen.get(j).remove(virus);
                }
                areaTimeCount[j] = 0;
            }
        }
        for (VirusListener vl: virusListeners)
            vl.onVirusIncreased(spreadList);
    }

    public static void addVirus(){
        int rand = (int) (Math.random() * 12);
        int i = (int) Math.round(Math.random() * notChosen.get(rand).size());
        Virus virus = notChosen.get(rand).get(i);
        viruses.get(rand).add(virus);
        notChosen.get(rand).remove(virus);
        List<Virus> increasedVirus = new ArrayList<>();
        increasedVirus.add(virus);
        for (VirusListener vl: virusListeners)
            vl.onVirusIncreased(increasedVirus);
    }

    public static void initGame() {
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
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 110; j++) {
                Virus virus = new Virus(new Point(i * 5, j * 5));
                notChosen.get(virus.getGroupID()).add(virus);
                total[virus.getGroupID()] += 1;
            }
        }
    }

    public static void startGame() { setGameState(GameState.INGAME); }

}
