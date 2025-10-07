package StateManagement;

import StateManagement.GameStates.GameState;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

public interface GameObserver {
    void onStateChange(GameState newState);
}