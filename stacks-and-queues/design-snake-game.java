/*
Design Snake Game

Design a Snake game that is played on a device with screen size = width x height. Play the game 
online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length
and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the 
first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied 
by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

https://leetcode.com/problems/design-snake-game/
*/

class SnakeGame {

  class Point {
    int r;
    int c;
    Point( int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
  
  int[][] food;
  int curFood;
  int width;
  int height;
  LinkedList<Point> snake;
  
  /** Initialize your data structure here.
      @param width - screen width
      @param height - screen height 
      @param food - A list of food positions
      E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
  public SnakeGame(int width, int height, int[][] food) {
    this.food = food;
    this.width = width;
    this.height = height;
    snake = new LinkedList<>();
    snake.add(new Point(0, 0));
  }

  /** Moves the snake.
      @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
      @return The game's score after the move. Return -1 if game over. 
      Game over when snake crosses the screen boundary or bites its body. */
  public int move(String direction) {
    Point head = snake.getLast();
    Point newHead = new Point(head.r, head.c);
    if (direction.equals("U")) {
      newHead.r--;
    } else if (direction.equals("L")) {
      newHead.c--;
    } else if (direction.equals("R")) {
      newHead.c++;
    } else { // D
      newHead.r++;
    }
    if (newHead.r < 0 || newHead.r == height || newHead.c < 0 || newHead.c == width) {
      return -1;
    }
    if (curFood < food.length) {
      if (food[curFood][0] == newHead.r && food[curFood][1] == newHead.c) {
        snake.addLast(newHead);
        curFood++;
        return curFood;
      }      
    }
    
    snake.removeFirst();
    for (Point point : snake) {
      if (point.r == newHead.r && point.c == newHead.c) {
        return -1;
      }
    }
    snake.addLast(newHead);
    return curFood;
  }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
