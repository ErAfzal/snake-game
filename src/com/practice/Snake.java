package com.practice;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Snake {

    private LinkedList<Point> body;
    private HashSet<Point> set;
    private volatile Direction dir;
    private int width;
    private int height;
    private FoodManager foodManager;

    public Snake( int width, int height, FoodManager foodManager) {
        this.body = new LinkedList<Point>();
        this.set = new HashSet<>();
        Point p1=new Point(0,0);
        Point p2=new Point(0,1);
        Point p3=new Point(0,2);
        body.add(p1);
        body.add(p2);
        body.add(p3);

        set.add(p1);
        set.add(p2);
        set.add(p3);

        this.dir = Direction.RIGHT;
        this.width = width;
        this.height = height;
        this.foodManager = foodManager;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void setBody(LinkedList<Point> body) {
        this.body = body;
    }

    Boolean move(){

        Point newHead=getNextCell(body.getFirst(),dir);

        //boundary collision
        /*if(newHead.x<0  || newHead.x>=length || newHead.y<0 || newHead.y>=width) {
            return false;
        }*/


        //snake collision
        if(set.contains(newHead) && !body.getLast().equals(newHead)) return false;

        body.addFirst(newHead);
        set.add(newHead);
        /*Point foodPos=foodManager.getFoodPos();
        if(!foodPos.equals(newHead)){
            set.remove(body.removeLast());
        }*/
        return true;
    }




    Point getNextCell(Point head, Direction dir){
        Point cur=null;
        switch(dir){
            case UP: cur=new Point(-1,0); break;
            case DOWN: cur=new Point(1,0); break;
            case LEFT: cur=new Point(0,-1); break;
            case RIGHT: cur=new Point(0,1);
        }
        //snake crossing border to enter from other end
        int nextX=(head.x+cur.x+width)%width;
        int nextY=(head.y+cur.y+height)%height;

        return new Point(nextX,nextY);
    }


    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setDirectionFromUser()
    {
        Scanner sc=new Scanner(System.in);
        char st=sc.nextLine().charAt(0);

        synchronized (dir) {
            switch (st) {
                case 'w':
                    dir = Direction.UP;
                    break;
                case 'a':
                    dir = Direction.LEFT;
                    break;
                case 's':
                    dir = Direction.DOWN;
                    break;
                case 'd':
                    dir = Direction.RIGHT;
            }
        }
    }

    public boolean isAtPosition(int x, int y) {
        return body.stream().anyMatch(body -> body.x == x && body.y == y);
    }
}
