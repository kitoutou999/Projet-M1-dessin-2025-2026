package model;

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
