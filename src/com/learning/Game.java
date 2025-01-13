package com.learning;

import java.util.Scanner;

//com.learning by chatGPT
public class Game {
    private GameEngine gameEngine;
    private ScoreManager scoreManager;
    private boolean isRunning;

    public Game() {
        gameEngine = new GameEngine();
        scoreManager = new ScoreManager();
        isRunning = true;
    }

    public void start() {
        System.out.println("Welcome to Snake Game!");
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            gameEngine.render(); // Render the board, snake, and food
            System.out.println("Enter direction (W/A/S/D): ");
            char input = scanner.nextLine().toUpperCase().charAt(0);

            switch (input) {
                case 'W': gameEngine.setDirection(Direction.UP); break;
                case 'A' : gameEngine.setDirection(Direction.LEFT); break;
                case 'S' : gameEngine.setDirection(Direction.DOWN); break;
                case 'D' : gameEngine.setDirection(Direction.RIGHT); break;
                default : System.out.println("Invalid input! Use W/A/S/D.");
            }

            if (!gameEngine.updateGame()) {
                isRunning = false;
                System.out.println("Game Over!");
                System.out.println("Final Score: " + scoreManager.getScore());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

}



