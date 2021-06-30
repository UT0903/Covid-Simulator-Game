package Game;

public interface PeopleNumListener {
    void onPeopleInfectedNumChanged(int groupID, int prevNum, int newNum);
    void onPeopleDeadNumChanged(int groupID, int prevNum, int newNum);
}
