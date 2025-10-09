package StateManagement;

import StateManagement.GameStates.GameClose;
import StateManagement.GameStates.GameRunning;
import StateManagement.GameStates.GameStart;
import StateManagement.GameStates.GameState;
import spiel.Game;

public class GameStateRouter implements GameObserver {
    // Observer der auf Zustandsänderungen reagiert
    @Override
    public void onStateChange(GameState newState) {
        System.out.println("State changed to: " + newState);
        if (newState instanceof GameStart) {
            Game.start();
        } else if (newState instanceof GameRunning) {
            Game.running();
        } else if (newState instanceof GameClose) {
            Game.close();
            // Programm beenden, nachdem closed() aufgerufen wurde
            System.exit(0);
        }
    }
}
