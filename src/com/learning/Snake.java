package com.learning;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private LinkedList<int[]> body; // List of coordinates [x, y]
    private Direction direction;

    public Snake() {
        body = new LinkedList<>();
        body.add(new int[]{5, 5}); // Starting position
        direction = Direction.RIGHT;
    }

    public void move() {
        int[] head = body.get(0);

        //int[] head = body.getFirst();
        int[] newHead = head.clone();

        //int[] newHead = head;

        switch (direction) {
            case UP:
                newHead[1]--;
                break;
            case DOWN:
                newHead[1]++;
                break;
            case LEFT:
                newHead[0]--;
                break;
            case RIGHT:
                newHead[0]++;
                break;
        }


        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        body.addLast(body.getLast());
    }

    /*public boolean checkSelfCollision() {
        int[] head = body.getFirst();
        return body.stream().skip(1).anyMatch(segment -> segment[0] == head[0] && segment[1] == head[1]);
    }*/
    public boolean checkSelfCollision() {
        int[] head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            int[] segment = body.get(i);
            if (segment[0] == head[0] && segment[1] == head[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean hitsWall(int width, int height) {
        int[] head = body.getFirst();
        return head[0] < 0 || head[0] >= width || head[1] < 0 || head[1] >= height;
    }

    public boolean eats(Food food) {
        int[] head = body.getFirst();
        return head[0] == food.getX() && head[1] == food.getY();
    }

    public boolean isAtPosition(int x, int y) {
        return body.stream().anyMatch(segment -> segment[0] == x && segment[1] == y);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
