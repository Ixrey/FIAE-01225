package stateManagement.GameStates;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

// Definiert den abgeschlossenen Zustandsraum für das Spiel
public sealed interface GameState permits GameStart, GameRunning, GameClose {

    void enter();
}