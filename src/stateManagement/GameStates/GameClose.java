package stateManagement.GameStates;

import spiel.Spielablauf;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

// Zustand: Spiel beenden
public final class GameClose implements GameState {
    @Override
    public String toString() {
        return "GameClose";
    }

    @Override
    public void enter() {
        Spielablauf.close();
    }
}