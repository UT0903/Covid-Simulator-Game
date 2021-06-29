package Game;

import java.util.List;

import components.Virus;

public interface VirusListener {
    void onVirusIncreased(List<Virus> increasedVirus);
    void onVirusDecreased(List<Virus> decreasedVirus);
}
