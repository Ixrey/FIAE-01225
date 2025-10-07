import StateManagement.GameObserver;
import StateManagement.GameStateManager;
import StateManagement.GameStates.GameClose;
import StateManagement.GameStates.GameRunning;
import StateManagement.GameStates.GameStart;
import StateManagement.GameStates.GameState;
import spiel.Game;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

public class App {
    public static void main(String[] args) {
        // Initialer Zustand: GameStart

        GameStateManager stateManager = new GameStateManager(new GameStart());
        Game.setStateManager(stateManager);

        // Observer der auf Zustandsänderungen reagiert
        stateManager.addObserver(new GameObserver() {
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
        });
        // Startet das Spiel
        stateManager.setState(new GameStart());

    }
}
