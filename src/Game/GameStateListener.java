package Game;

public interface GameStateListener {
    void onGameStateChanged(GameState prevState, GameState newState);
}
