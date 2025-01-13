package com.learning;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        score = 0;
    }

    public void incrementScore() {
        score += 10;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }
}
