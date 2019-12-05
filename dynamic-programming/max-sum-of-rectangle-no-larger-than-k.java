/*
Max Sum of Rectangle No Larger Than K

Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in
the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
*/

class Solution {
  public int maxSumSubmatrix(int[][] matrix, int k) {
    // 2D Kadane's algorithm + 1D max sum problem
    int m = matrix.length;
    int n = matrix[0].length;
    int rect = Integer.MIN_VALUE;
    // from left column
    for (int left = 0; left < n; left++) {
      // cumulative sum for each row
      int[] cum = new int[m];
      // to right column
      for (int right = left; right < n; right++) {
        // Accumulate values for the next column
        for (int i = 0; i < m; i++) {
          cum[i] += matrix[i][right];
        }
        TreeSet<Integer> set = new TreeSet<>();
        // To handle single row case
        set.add(0);
        int sum = 0;
        for (int i = 0; i < m; i++) {
          sum += cum[i];
          // sum - k gives us remained amount.
          // But that amount can be bigger.
          Integer remainedSum = set.ceiling(sum - k);
          if (remainedSum != null) {
            // No take the diff between sum and found amount
            rect = Math.max(rect, sum - remainedSum);
          }
          set.add(sum);
        }
      }
    }
    return rect;
  }
}
