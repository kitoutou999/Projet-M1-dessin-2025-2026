package model;

/**
 * Contient les parametres choisis par le joueur au demarrage : mode de jeu, difficulte et type de niveaux.
 */
public class GameSettings {
    private final GameMode mode;
    private final Difficulty difficulty;
    private final LevelType levelType;

    public GameSettings(GameMode mode, Difficulty difficulty, LevelType levelType) {
        this.mode = mode;
        this.difficulty = difficulty;
        this.levelType = levelType;
    }

    public GameMode getMode() { return mode; }
    public Difficulty getDifficulty() { return difficulty; }
    public LevelType getLevelType() { return levelType; }
}
