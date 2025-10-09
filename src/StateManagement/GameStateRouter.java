package stateManagement;

import spiel.Game;
import stateManagement.GameStates.GameClose;
import stateManagement.GameStates.GameRunning;
import stateManagement.GameStates.GameStart;
import stateManagement.GameStates.GameState;

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
