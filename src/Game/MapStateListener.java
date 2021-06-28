package Game;

public interface MapStateListener {
    void onAreaHoverChanged(int prevId, int newId);
    void onAreaClickChanged(int prevId, int newId);
}
