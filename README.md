# DungeonCrawler – Schulprojekt (FIAE)

Kleines Roguelike/Dungeoncrawler mit rundenbasiertem Kampf. Fokus: **OOP**, **Collections**, **Swing-GUI** und **testbare Kernlogik**.

## Ziel & Umfang (Kurzfassung)
- **Ziel:** Kompakter Dungeon (Räume/Ebenen), Gegner begegnen, Items sammeln, XP erhalten, Level-Ups. Basis: **eine Ebene mit Boss**; Erweiterungen: mehrere Ebenen + **Hometown** (Meta-Progression).
- **Kernsysteme:** Spielwelt (Räume & Events), Encounter, Charakter-/Gegnerwerte, rundenbasierter Kampf (Angriff/Items/Skills), Inventar (Relikte/Tränke), GUI (frei begehbare Räume + fester Kampfbildschirm), optional Meta-Progression & Quests.

## Basisversion (MVP)
- **Spielwelt:** 1 Ebene mit ~5 Räumen, 2 Raumtypen: normaler Kampf (ein Gegner) & Bossraum.
- **Spieler:** HP, Angriff, Verteidigung, Level-Up (erhöht Basiswerte), **Aktionspunkte** pro Runde, **Standardangriff**, feste Anzahl Tränke.
- **Gegner:** 2 Typen (z. B. Goblin, Ork) + Boss; einfache KI (jede Runde Angriff).
- **Kampf:** rundenbasiert (Spieler → Gegner), Aktionen: **Angriff**, **Item nutzen**; Ausgang: Sieg (XP/Level-Up) oder Game Over.
- **GUI (MVP):** Kampf-View (links Spieler / rechts Gegner), Buttons (Angriff/Tränke), Statusanzeigen (Name/HP/Level), Mini-Karte, Kampflog; Bewegung über Kontext/Buttons.

## Meilensteine
1) **Spielbare Basisversion**  
   Kernlogik (Kampf, Bewegung, Sieg/Niederlage); 1 Ebene, 2 Gegner, 1 Boss.
2) **Welt & Gegner**  
   Mehr Ebenen; neue Gegnertypen; neue Raumtypen (Schatz/Händler).
3) **Skills, Stats & Items**  
   ~15 Skills, max. 4 aktiv; neue Stats (Ausweichrate/Genauigkeit/Crit); passive Itemeffekte.
4) **GUI-Optimierung**  
   Platzhalter → Sprites, bessere Statusanzeigen/Buttons.
5) **Stretch Goals**  
   Hometown (Meta-Progression), einfache Quests, **Speichern/Laden (JSON)** für Meta & Run-Status.

---

## Team & Aufgaben
- **Spielablauf:** Tobi  
- **Kampfablauf:** Apo  
- **Charaktere/Modelle:** Chris  
- **GUI (Ansichten/Interaktion):** Daniel  
- **GUI (weitere Komponenten/Support):** Abdi  

> Zusammenarbeit: Niemand arbeitet direkt auf `main`. Jeder Feature-Branch → Pull Request → Review → Merge.

---

## Paket-/Ordnerstruktur (Vorschlag)
src/
main/
java/
app/
Main.java # Einstieg, Initialisierung

game/ # Spielablauf/Run-Loop, State-Maschine
GameController.java
GameState.java

world/ # Ebenen/Räume/Events/Encounter
Dungeon.java
Floor.java
Room.java
RoomType.java
EncounterService.java

entities/ # Spieler & Gegner (Modelle/Stats/Skills)
Player.java
Enemy.java
Stats.java
Skill.java

combat/ # Kampfmotor, Rundenlogik, Aktionen
CombatEngine.java
Action.java
CombatLog.java

items/ # Tränke/Relikte/Inventar
Item.java
Potion.java
Relic.java
Inventory.java

ui/ # Swing-GUI (Views, Panels, Controls)
GameWindow.java
MapPanel.java
CombatPanel.java
StatusPanel.java

meta/ # Hometown, Freischaltungen (Stretch)
Hometown.java
UnlockService.java

quest/ # Quests (Stretch)
Quest.java
QuestLog.java

save/ # Speichern/Laden (JSON)
SaveGame.java
JsonSaveService.java

util/ # Hilfsklassen (Random, Validation, etc.)
RandomUtil.java

test/ # (optional) Unit-Tests


**Hinweise zur Aufteilung:**
- **Spielablauf (Tobi):** `game/` steuert die Zustände: Erkunden → Kampf → Sieg/Niederlage → (Hometown). Übergibt an `world/` und `combat/`.
- **Kampf (Apo):** `combat/` kapselt Rundenlogik, Aktionen, Schadensberechnung, CombatLog.
- **Charaktere (Chris):** `entities/` inkl. Stats/Skills, Basisklassen (Player/Enemy) und einfache Gegner-Implementierungen.
- **GUI (Daniel/Abdi):** `ui/` Panels/Window, Bindung von Buttons an `game/`/`combat/`, Statusleisten, Minimap, Log.

---

## Entwicklungs-Workflow

### Branch-Strategie
- **Feature-Branches:** `feature/<thema>` (z. B. `feature/kampf`), Fixes: `fix/<thema>`.
- PR erst nach „baut lokal“ + kleiner Selbsttest.

### Ablauf (VS Code)
1. **Branch erstellen:** Git-Panel → Branch-Icon → *Create new branch…*  
2. **Commit & Push:** Änderungen → Message → *Commit* → *Publish Branch*  
3. **PR:** Extension *GitHub Pull Requests & Issues* → *Create Pull Request* (Base: `main`)

### Ablauf (Bash)
```bash
git checkout -b feature/spielablauf
git add .
git commit -m "Implementiert Grundgerüst Spielablauf"
git push -u origin feature/spielablauf


