package stateManagement.GameStates;

import spiel.Game;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

// Zustand: Spiel starten
public final class GameStart implements GameState {
    @Override
    public String toString() {
        return "GameStart";
    }

    @Override
    public void enter() {
        Game.close();
    }
}