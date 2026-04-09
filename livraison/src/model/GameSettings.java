package model;

import model.strategy.LevelStrategy;

/**
 * Contient les parametres choisis par le joueur au demarrage : mode de jeu, difficulte et strategie de generation des niveaux.
 */
public class GameSettings {
    private final GameMode mode;
    private final Difficulty difficulty;
    private final LevelStrategy levelStrategy;

    public GameSettings(GameMode mode, Difficulty difficulty, LevelStrategy levelStrategy) {
        this.mode = mode;
        this.difficulty = difficulty;
        this.levelStrategy = levelStrategy;
    }

    public GameMode getMode() { return mode; }
    public Difficulty getDifficulty() { return difficulty; }
    public LevelStrategy getLevelStrategy() { return levelStrategy; }
}
