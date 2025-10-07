import spiel.Game;
import stateManagement.GameStateManager;
import stateManagement.GameStateRouter;
import stateManagement.GameStates.GameStart;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

public class App {
    public static void main(String[] args) {

        // Initialer Zustand: GameStart
        GameStateManager stateManager = new GameStateManager(new GameStart());
        Game.setStateManager(stateManager);

        GameStateRouter router = new GameStateRouter();
        stateManager.addObserver(router);

        // Startet das Spiel
        router.onStateChange(stateManager.getState());

        // stateManager.setState(new GameStart());
        // Fehler? weil prüfung immer false ist?

    }
}
