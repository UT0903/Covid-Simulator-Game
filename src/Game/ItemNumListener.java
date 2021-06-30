package Game;

public interface ItemNumListener {
    void onItemInAreaNumChanged(int itemID, int groupID, int prevNum, int curNum);
    void onItemLastNumChanged(int itemID, int prevNum, int curNum);
}
