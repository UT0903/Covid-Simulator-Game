import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import components.Virus;
import panels.MapPanel;

public class StateManager {
    private List<Virus> viruses;
    private int gold;
    private int incomePerHour;
    private int score;
    private ArrayList<Point> chosen = new ArrayList<Point>();
    private ArrayList<Point> notChosen = new ArrayList<Point>();
    public StateManager() {
        this.viruses = new ArrayList<>();
        this.gold = 1000;
        this.incomePerHour = 100;
        this.score = 0;
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 110; j++){
                notChosen.add(new Point(i * 5 , j * 5));
            }
        }
    }

    public void updateGold() { this.gold += this.incomePerHour; }
    public void updateGold(int gold) { this.gold += gold; }
    public void updateViruses(List<Virus> viruses) {
        this.viruses = viruses;
    }
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
