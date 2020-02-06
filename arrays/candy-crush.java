/*
Candy Crush

This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive 
integers board[i][j] represent different types of candies. A value of board[i][j] = 0 
represents that the cell at position (i, j) is empty. The given board represents the state 
of the game following the player's move. Now, you need to restore the board to a stable state
by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" 
them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top 
of itself, then these candies will drop until they hit a candy or bottom at the same time. 
(No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to 
repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return
the current board.
You need to perform the above rules until the board becomes stable, then return the current board.

Example:

Input:
board =
[[110,5,112,113,114],
[210,211,5,213,214],
[310,311,3,313,314],
[410,411,412,5,414],
[5,1,512,3,3],
[610,4,1,613,614],
[710,1,2,713,714],
[810,1,2,1,1],
[1,1,2,2,2],
[4,1,4,4,1014]]

Output:
[[0,0,0,0,0],
[0,0,0,0,0],
[0,0,0,0,0],
[110,0,0,0,114],
[210,0,0,0,214],
[310,0,0,113,314],
[410,0,0,213,414],
[610,211,112,313,614],
[710,311,412,613,714],
[810,411,512,713,1014]]

Explanation:
https://assets.leetcode.com/uploads/2018/10/12/candy_crush_example_2.png

Note:
The length of board will be in the range [3, 50].
The length of board[i] will be in the range [3, 50].
Each board[i][j] will initially start as an integer in the range [1, 2000].

https://leetcode.com/problems/candy-crush/
*/

  class Solution {

    public int[][] candyCrush(int[][] board) {
      boolean crushed = true;
      int m = board.length;
      if (m == 0) {
        return board;
      }
      while (crushed) {
        crushed = crush(board);
        // Sweeping can be combined with moveDown by checking that values are negative
        // But it's clearer this way
        sweep(board);
        moveDown(board);
      }
      return board;
    }

    boolean crush(int[][] board) {
      int m = board.length;
      int n = board[0].length;
      boolean result = false;
      // We don't need to traverse the graph; just mark values to crush as negatives
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) {
            continue;
          }
          int color = Math.abs(board[i][j]);
          if (i - 1 >= 0 && i + 1 < m
              && Math.abs(board[i - 1][j]) == color
              && Math.abs(board[i + 1][j]) == color) {
            result = true;
            board[i - 1][j] = -color;
            board[i][j] = -color;
            board[i + 1][j] = -color;
          }
          if (j - 1 >= 0 && j + 1 < n
              && Math.abs(board[i][j - 1]) == color
              && Math.abs(board[i][j + 1]) == color) {
            result = true;
            board[i][j - 1] = -color;
            board[i][j] = -color;
            board[i][j + 1] = -color;
          }
        }
      }
      return result;
    }

    void sweep(int[][] board) {
      int m = board.length;
      int n = board[0].length;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] < 0) {
            board[i][j] = 0;
          }
        }
      }
    }
    
    void moveDown(int[][] board) {
      int m = board.length;
      int n = board[0].length;
      for (int i = m - 2; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) {
            continue;
          }
          int k = i;
          while (k + 1 < m && board[k + 1][j] == 0) {
            k++;
          }
          if (k == i) {
            continue;
          }
          int t = board[i][j];
          board[k][j] = t;
          board[i][j] = 0;
        }
      }
    }
  }
