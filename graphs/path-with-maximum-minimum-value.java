/*
Path With Maximum Minimum Value

Given a matrix of integers A with R rows and C columns, find the maximum score of 
a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of 
the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited
cell in one of the 4 cardinal directions (north, east, west, south).

Example 1:
Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 

Example 2:
Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Example 3:
Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
 
Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9

https://leetcode.com/problems/path-with-maximum-minimum-value/
*/

class Solution {
  int[][] DIRS = new int[][] {
    {-1,0}, {0, -1}, {1, 0}, {0, 1}
  };
  public int maximumMinimumPath(int[][] A) {
    int m = A.length;
    int n = A[0].length;
    PriorityQueue<int[]> queue = 
      new PriorityQueue<>((el1, el2) -> el2[2] - el1[2]);
    queue.add(new int[] {0, 0, A[0][0]});
    A[0][0] = -1;
    while (!queue.isEmpty()) {
      int[] el = queue.remove();
      if (el[0] == m - 1 && el[1] == n - 1) {
        return el[2];
      }
      for (int[] dir : DIRS) {
        int i = dir[0] + el[0];
        int j = dir[1] + el[1];
        if (i < 0 || i == m || j < 0 || j == n) {
          continue;
        }
        if (A[i][j] == -1) {
          continue;
        }
        int[] newEl = new int[]{i, j, Math.min(A[i][j], el[2])};
        queue.add(newEl);
        A[i][j] = -1;
      }
    }
    return -1;
  }
}
