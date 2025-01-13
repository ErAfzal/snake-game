package com.learning;

import java.util.Random;

public class Food {
    private int x;
    private int y;

    public void generateRandomPosition(int width, int height) {
        Random random = new Random();
        this.x = random.nextInt(width);
        this.y = random.nextInt(height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAtPosition(int x, int y) {
        return this.x == x && this.y == y;
    }
}
