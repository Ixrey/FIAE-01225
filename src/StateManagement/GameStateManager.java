package stateManagement;

import java.util.ArrayList;
import java.util.List;

import stateManagement.GameStates.GameState;

// Sie Arbeiten nur im package Game. Alle anderen Dateien bleiben von Ihnen unberührt. 

public class GameStateManager {
    private GameState currentState;
    private final List<GameObserver> observers = new ArrayList<>();

    public GameStateManager(GameState initialState) {
        this.currentState = initialState;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    // Ändert den Zustand und benachrichtigt die Observer nur bei einer
    // tatsächlichen Änderung
    public void setState(GameState newState) {
        if (!this.currentState.equals(newState)) {
            this.currentState = newState;
            notifyObservers();
        }
    }

    public GameState getState() {
        return currentState;
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onStateChange(currentState);
        }
    }
}