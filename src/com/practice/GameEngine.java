package com.practice;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

//com.practice solution which we came in discussion
public class GameEngine {
    private FoodManager foodManager;
    private Snake snake;
    private Board board;



    public GameEngine(FoodManager foodManager, Snake snake) {
        this.foodManager = foodManager;
        this.snake = snake;
        board=new Board(10,10);

    }

    void startGame(GameEngine gm){
        boolean isRunning=true;
        while(isRunning){
            //gm.render(snake,foodManager);
            System.out.println("input");
            /*Scanner sc=new Scanner(System.in);
            char st=sc.nextLine().charAt(0);

            Direction dir=Direction.RIGHT;
            switch(st){
                case 'w': dir=Direction.UP; break;
                case 'a': dir=Direction.LEFT; break;
                case 's': dir=Direction.DOWN; break;
                case 'd': dir=Direction.RIGHT;
            }*/
            if(!snake.move()) {
                System.out.println("game over");
                isRunning=false;
            }
            System.out.println(snake.getBody());
        }

    }
    public void  render(Snake snake, FoodManager food) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
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

    public  static void main(String [] a){
        //one extra thread is running along with main thread and this thread has to be before         t.execute(()-> gm.startGame(gm));
        //because otherwise it will get cpu cycle after its completed executed which is wrong.
        FoodManager f=new FoodManager();
        Snake snake=new Snake(10,10,f);
        GameEngine gm=new GameEngine(new FoodManager(),snake);
        //new thread for
        snake.setDirectionFromUser();
        ScheduledExecutorService t= Executors.newScheduledThreadPool(1);
        t.execute(()-> gm.startGame(gm));

    }


}
