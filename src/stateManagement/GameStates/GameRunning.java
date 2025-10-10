package stateManagement.GameStates;

import spiel.Game;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

// Zustand: Laufendes Spiel
public final class GameRunning implements GameState {
    @Override
    public String toString() {
        return "GameRunning";
    }

    @Override
    public void enter() {
        Game.running();
        System.out.println("[DEBUG] Game.running() - before showSpiel()");
    }

}