package com.learning;

public class Board {
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void placeFood(Food food) {
        food.generateRandomPosition(width, height);
    }

    public boolean checkCollision(Snake snake) {
        return snake.checkSelfCollision() || snake.hitsWall(width, height);
    }

    public void render(Snake snake, Food food) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (snake.isAtPosition(x, y)) {
                    System.out.print("S");
                } else if (food.isAtPosition(x, y)) {
                    System.out.print("F");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
