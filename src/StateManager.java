import java.util.ArrayList;
import java.util.List;

import components.Virus;

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
    public void updateScore(int score) { this.score += score; }
    public void updateIncome() { this.incomePerHour += 1; }
    public void updateIncome(int income) { this.incomePerHour += income; }
    public int getGold() { return this.gold; }
    public int getScore() { return this.score; }
    public int getIncome() { return this.incomePerHour; }
}
