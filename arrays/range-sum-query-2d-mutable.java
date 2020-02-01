/*
Range Sum Query 2D - Mutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper
left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10

Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.

https://leetcode.com/problems/range-sum-query-2d-mutable/
*/

class NumMatrix {

  // Other solutions could be based on Binary indexed tree or Segment tree
  // cache[i][j] stores sum by columns : m[0][j] + ... + m[i - 1][j]
  int[][] matrix;
  int[][] cache;
  int m;
  int n;
  
  public NumMatrix(int[][] matrix) {
    this.matrix = matrix;
    m = matrix.length;
    if (m == 0) {
      return;
    }
    n = matrix[0].length;
    cache = new int[m + 1][n];
    for (int i = 1; i <= m; i++) {
      for (int j = 0; j < n; j++) {
        cache[i][j] = cache[i - 1][j] + matrix[i - 1][j];
      }
    }
  }

  public void update(int row, int col, int val) {
    for (int i = row + 1; i <= m; i++) {
      cache[i][col] += (val - matrix[row][col]);
    }
    matrix[row][col] = val;
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    int res = 0;
    for (int j = col1; j <= col2; j++) {
      res += cache[row2 + 1][j] - cache[row1][j];
    }
    return res;
  }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
