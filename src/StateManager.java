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

    public StateManager() {
        this.viruses = new ArrayList<>();
        this.gold = 1000;
        this.incomePerHour = 100;
        this.score = 0;
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
    public void addRedVirus(MapPanel mapPanel){
        int l = (int) Math.round(Math.random() * mapPanel.getNotChosen().size());
        Point location = mapPanel.getNotChosen().get(l);
        Virus redVirus = new Virus(location);
        mapPanel.add(redVirus, Integer.valueOf(1));
        redVirus.setLocation(location);
        mapPanel.getViruses().add(redVirus);
        mapPanel.getChosen().add(location);
        mapPanel.getNotChosen().remove(location);
    }

}
