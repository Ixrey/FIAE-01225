package stateManagement;

import spiel.Spielablauf;
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
            Spielablauf.start();
        } else if (newState instanceof GameRunning) {
            Spielablauf.running();
        } else if (newState instanceof GameClose) {
            Spielablauf.close();
            // Programm beenden, nachdem closed() aufgerufen wurde
            System.exit(0);
        }
    }
}
