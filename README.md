# DungeonCrawler – Schulprojekt (FIAE)

Kleines Roguelike/Dungeoncrawler mit rundenbasiertem Kampf. Fokus: **OOP**, **Collections**, **Swing-GUI** und **testbare Kernlogik**.

## Ziel & Umfang (Kurzfassung)
- **Ziel:** Kompakter Dungeon (Räume/Ebenen), Gegner begegnen, Items sammeln, XP erhalten, Level-Ups. Basis: **eine Ebene mit Boss**; Erweiterungen: mehrere Ebenen + **Hometown** (Meta-Progression).
- **Kernsysteme:** Spielwelt (Räume & Events), Encounter, Charakter-/Gegnerwerte, rundenbasierter Kampf (Angriff/Items/Skills), Inventar (Relikte/Tränke), GUI (frei begehbare Räume + fester Kampfbildschirm), optional Meta-Progression & Quests.

## Basisversion (MVP)
- **Spielwelt:** 1 Ebene mit ~5 Räumen, Raumtypen: normaler Kampf (ein Gegner) & Bossraum.
- **Spieler:** HP, Angriff, Verteidigung, Level-Up (erhöht Basiswerte), **Aktions-/Manapunkte** pro Runde, **Standardangriff**, feste Anzahl Tränke.
- **Gegner:** Mind. 2 Typen (z. B. Goblin/Ork) + Boss; einfache KI (jeder Runde Angriff).
- **Kampf:** rundenbasiert (Spieler → Gegner), Aktionen: **Angriff**, **Item nutzen**; Ausgang: Sieg (XP/Level-Up) oder Game Over.
- **GUI (MVP):** Kampf-View (links Spieler / rechts Gegner), Buttons (Angriff/Tränke), Statusanzeigen (Name/HP/Level), Mini-Karte, Kampflog; Bewegung per Buttons/Klick.

## Meilensteine
1. **Spielbare Basisversion** – Kernlogik (Kampf, Bewegung, Sieg/Niederlage); 1 Ebene, 2 Gegner, 1 Boss.  
2. **Welt & Gegner** – Mehr Ebenen; neue Gegnertypen; neue Raumtypen (Schatz/Händler).  
3. **Skills, Stats & Items** – ~10–15 Skills (max. 4 aktiv), zusätzliche Stats (Ausweichrate/Genauigkeit/Crit); passive Itemeffekte.  
4. **GUI-Optimierung** – Platzhalter → bessere Darstellung, Statusanzeigen/Buttons.  
5. **Stretch Goals** – Hometown (Meta-Progression), einfache Quests, **Speichern/Laden (JSON)** für Meta & Run-Status.

---

## Team & Aufgaben (eintragen)
- **Spielablauf / Run-Loop** – _…_  
- **Kampfablauf / Rundenlogik** – _…_  
- **Charaktere & Modelle (Stats/Skills/Enemies)** – _…_  
- **GUI (Ansichten/Interaktion)** – _…_  
- **GUI (weitere Komponenten/Support)** – _…_

> Zusammenarbeit: Niemand arbeitet direkt auf `main`. Jeder Feature-Branch → Pull Request → Review → Merge.

---

## Paket-/Ordnerstruktur (Vorschlag)

~~~text
src/
└── main/
    └── java/
        └── app/
            ├── Main.java
            ├── game/
            │   ├── GameController.java
            │   └── GameState.java
            ├── world/
            │   ├── Dungeon.java
            │   ├── Floor.java
            │   ├── Room.java
            │   └── RoomType.java
            ├── entities/
            │   ├── Player.java
            │   ├── Enemy.java
            │   ├── Stats.java
            │   └── Skill.java
            ├── combat/
            │   ├── CombatEngine.java
            │   ├── Action.java
            │   └── CombatLog.java
            ├── items/
            │   ├── Item.java
            │   ├── Potion.java
            │   ├── Relic.java
            │   └── Inventory.java
            ├── ui/
            │   ├── GameWindow.java
            │   ├── MapPanel.java
            │   ├── CombatPanel.java
            │   └── StatusPanel.java
            ├── meta/
            │   ├── Hometown.java
            │   └── UnlockService.java
            ├── quest/
            │   ├── Quest.java
            │   └── QuestLog.java
            ├── save/
            │   ├── SaveGame.java
            │   └── JsonSaveService.java
            └── util/
                └── RandomUtil.java
~~~

**Hinweis:** Leere Ordner erscheinen in Git nicht. Für Platzhalter ggf. `.gitkeep` anlegen.

---

## Entwicklungs-Workflow

### Branch-Strategie
- **Feature-Branches:** `feature/<thema>` (z. B. `feature/kampf`), Fixes: `fix/<thema>`.
- PR erst nach „baut lokal“ + kleinem Selbsttest (kann man starten? Buttons klicken?).

### VS Code (empfohlen)
1. **Branch erstellen:** Git-Panel → Branch-Icon → *Create new branch…*  
2. **Commit & Push:** Änderungen → Message → *Commit* → *Publish Branch*  
3. **PR:** Extension **GitHub Pull Requests & Issues** → *Create Pull Request* (Base: `main`)

### Bash (Alternative)
```bash
git checkout -b feature/spielablauf
git add .
git commit -m "Implementiert Grundgerüst Spielablauf"
git push -u origin feature/spielablauf
