package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import components.Virus;

import static java.util.Arrays.asList;

public class StateManager {
    // Basic states management
    private int initGold = 1000;
    private int initIncomePerHour = 100;
    private List<Virus> viruses;
    private ArrayList<Virus> notChosen = new ArrayList<Virus>();
    public Virus[][] virusIsChosen = new Virus[150][110];
    private int gold;
    private int incomePerHour;
    private int score;
    public StateManager() {
        this.viruses = new ArrayList<>();
        this.gold = initGold;
        this.incomePerHour = initIncomePerHour;
        this.score = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 110; j++) {
                Virus virus = new Virus(new Point(i * 5, j * 5));
                notChosen.add(virus);
                virusIsChosen[i][j] = virus;
            }
        }
    }

    public void updateGold() { this.gold += this.incomePerHour; }
    public void updateGold(int gold) { this.gold += gold; }
    public void updateScore(int score) { this.score += score; }
    public void updateIncome() { this.incomePerHour += 1; }
    public void updateIncome(int income) { this.incomePerHour += income; }
    public int getGold() { return this.gold; }
    public int getScore() { return this.score; }
    public int getIncome() { return this.incomePerHour; }

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
    public static String[] labelNames = {
            "ambulance.gif",
            "canopy.png",
            "hospital.png",
            "mask.gif",
            "spray.gif",
            "syringe.gif"
    };
    public static String[] itemNames = {
            "救護車",
            "快篩站",
            "醫院",
            "口罩",
            "酒精",
            "疫苗"
    };
    public static String[] itemFunctions = {
            "增加醫院收治範圍",
            "增加染病發現率",
            "增加收治人數",
            "減緩感染速度",
            "暫時降低感染人數",
            "降低感染、死亡人數"
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
            3,
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



    public static String[] areaNames = {
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
        9,9,9,9,9,9,9,9,9,9,9,9
    };
    public Integer[] areaTimeCount ={
        0,0,0,0,0,0,0,0,0,0,0,0
    };
    public double[] areaSpreadProbability = {
        0.1,0.1,0.1,0.1,0.1,0.1,
        0.1,0.1,0.1,0.1,0.1,0.1
    };

    //Virus states management
    public List<Virus> getViruses() { return viruses; }
    public int getPercentage() { return (int) ((double) viruses.size() / 16500 * 100); }
    public List<Virus> spreadVirus(){
        List<Virus> spreadList = new ArrayList<Virus>();
        for (int i = 0; i < viruses.size(); i++){
            Virus virus = viruses.get(i);
            if (areaTimeCount[virus.getGroupID()].equals(areaSpreadTime[virus.getGroupID()])){
                int x = virus.getLocation().x / 5;
                int y = virus.getLocation().y / 5;
                for (int addX = -2; addX < 3; addX++){
                    for (int addY = -2; addY < 3; addY++){
                        int X = x + addX;
                        int Y = y + addY;
                        if (X >= 0 && X < 150 && Y >= 0 && Y < 110 && !virusIsChosen[X][Y].getChosen()){
                            if (Math.random() < areaSpreadProbability[virus.getGroupID()]){
                                virusIsChosen[X][Y].setChosen(true);
                                notChosen.remove(virusIsChosen[X][Y]);
                                spreadList.add(virusIsChosen[X][Y]);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < spreadList.size(); i++){
            viruses.add(spreadList.get(i));
        }
        for (int i = 0; i < areaSpreadTime.length; i++){
            if (areaSpreadTime[i].equals(areaTimeCount[i]))
                areaTimeCount[i] = 0;
            else
                areaTimeCount[i] += 1;
        }
        return spreadList;
    }
    public Virus addVirus(){
        int i = (int) Math.round(Math.random() * notChosen.size());
        Virus virus = notChosen.get(i);
        viruses.add(virus);
        virusIsChosen[virus.getLocation().x / 5][virus.getLocation().y / 5].setChosen(true);
        notChosen.remove(virus);
        return virus;
    }
}
