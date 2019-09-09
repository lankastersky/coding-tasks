/*
Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

https://leetcode.com/problems/search-a-2d-matrix-ii
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      int n = matrix.length;
      if (n == 0) {
        return false;
      }
      int m = matrix[0].length;
      if (m == 0) {
        return false;
      }
      int i = 0;
      int j = m - 1;
      while (i < n && j >= 0) {
        int x = matrix[i][j];
        if (x == target) {
          return true;
        }
        if (target < x) {
          j--;
        } else {
          i++;
        }
      }
      return false;
    }
}
