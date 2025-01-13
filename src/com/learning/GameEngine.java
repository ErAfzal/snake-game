package com.learning;
public class GameEngine {
    private Board board;
    private Snake snake;
    private Food food;
    private Direction currentDirection;
    private ScoreManager scoreManager;

    public GameEngine() {
        board = new Board(10, 10);
        snake = new Snake();
        food = new Food();
        scoreManager = new ScoreManager();
        currentDirection = Direction.RIGHT; // Default starting direction
        board.placeFood(food);
    }

    public void render() {
        board.render(snake, food);
        System.out.println("Score: " + scoreManager.getScore());
    }

    public void setDirection(Direction direction) {
        // Prevent reversing direction directly
        if ((currentDirection == Direction.UP && direction == Direction.DOWN) ||
                (currentDirection == Direction.DOWN && direction == Direction.UP) ||
                (currentDirection == Direction.LEFT && direction == Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && direction == Direction.LEFT)) {
            return;
        }
        currentDirection = direction;
    }

    public boolean updateGame() {
        snake.setDirection(currentDirection);
        snake.move();

        // Check collisions
        if (board.checkCollision(snake)) {
            return false; // Game over
        }

        // Check if the snake eats food
        if (snake.eats(food)) {
            snake.grow();
            scoreManager.incrementScore();
            board.placeFood(food);
        }

        return true; // Game continues
    }
}

