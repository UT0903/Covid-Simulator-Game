package Game;

public interface ItemStateListener {
    void onItemHoverChanged(int prevId, int newId);
    void onItemClickChanged(int prevId, int newId);
}
