package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import components.Virus;

public class StateManager {
    private List<Virus> viruses;
    private ArrayList<Point> chosen = new ArrayList<Point>();
    private ArrayList<Point> notChosen = new ArrayList<Point>();
    private int gold;
    private int incomePerHour;
    private int score;
    public StateManager() {
        this.viruses = new ArrayList<>();
        this.gold = 1000;
        this.incomePerHour = 100;
        this.score = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 110; j++) {
                notChosen.add(new Point(i * 5, j * 5));
            }
        }
    }

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

    public void updateViruses(List<Virus> viruses) { this.viruses = viruses; }
    public void updateGold() { this.gold += this.incomePerHour; }
    public void updateGold(int gold) { this.gold += gold; }
    public void updateScore(int score) { this.score += score; }
    public void updateIncome() { this.incomePerHour += 1; }
    public void updateIncome(int income) { this.incomePerHour += income; }
    public int getGold() { return this.gold; }
    public int getScore() { return this.score; }
    public int getIncome() { return this.incomePerHour; }
    public List<Virus> getViruses() { return viruses; }
    public Virus addVirus(){
        int l = (int) Math.round(Math.random() * notChosen.size());
        Point location = notChosen.get(l);
        Virus virus = new Virus(location);
        viruses.add(virus);
        chosen.add(location);
        notChosen.remove(location);
        return virus;
    }
}
