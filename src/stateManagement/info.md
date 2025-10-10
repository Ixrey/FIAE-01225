Alle Klassen im package StateManangement lassen Sie für das Projekt unbeührt.
Nutzen Sie nur die Funktionen, welche für den Wechsel der States benötigt werden.

Startet das Spiel 
stateManager.setState(new GameStart());
Können Sie auch nutzen, wenn der Spieler die Möglichkeit erhalten soll ins Startmenu zu kommen.

Setzt den State auf GameRunning(). 
stateManager.setState(new GameRunning());
Diese sollte ausgeführt werden, wenn der Spieler entweder ein "Neues Spiel" oder "Spielladen auswählt"

Setzt den State auf GameClose() und beendet das Programm.
stateManager.setState(new GameClose());