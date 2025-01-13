package com.practice;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class FoodManager {

    private Point foodPos;

    Point getFoodPos(){
        return foodPos;
    }

    Point generateFoodPos(int width, int length, HashSet<Point> set){
        Random random=new Random();
        Point foodPoint=new Point();
        do{
            foodPoint.x= random.nextInt(width-1);
            foodPoint.y= random.nextInt(length-1);

        }while(set.contains(foodPoint));
        return foodPoint;
    }
    public boolean isAtPosition(int x, int y) {
        return this.foodPos.x == x && this.foodPos.y == y;
    }

}
