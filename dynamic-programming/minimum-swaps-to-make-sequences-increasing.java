/*
Minimum Swaps To Make Sequences Increasing

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same
index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is 
strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  
It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.

Note:
A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
*/

class Solution {
  // Using dp based on https://leetcode.com/articles/minimum-swaps-to-make-sequences-increasing/
  public int minSwap(int[] A, int[] B) {
    int res = 0;
    int n = A.length;
    if (n <= 1) {
      return n;
    }
    int prevswap = 1;
    int prevnotswap = 0;
    int swap;
    int notswap;
    for (int i = 1; i < n; i++) {
      swap = Integer.MAX_VALUE;
      notswap = Integer.MAX_VALUE;
      if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
        // we can have both arrays natural or both swapped
        notswap = Math.min(notswap, prevnotswap);
        swap = Math.min(swap, prevswap + 1);
      }
      if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
        // we only can swap once
        notswap = Math.min(notswap, prevswap);
        swap = Math.min(swap, prevnotswap + 1);
      }
      prevswap = swap;
      prevnotswap = notswap;
    }
    return Math.min(prevswap, prevnotswap);
  }

  // Using dp based on https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/161887/Bottom-up-DP-with-Optimization-(Java-Python)
  // public int minSwap(int[] A, int[] B) {
  //   int res = 0;
  //   int n = A.length;
  //   if (n <= 1) {
  //     return n;
  //   }
  //   int[][] dp = new int[n][2];
  //   dp[0][0] = 0; // not swap
  //   dp[0][1] = 1; // swap
  //   for (int i = 1; i < n; i++) {
  //     boolean incr = A[i - 1] < A[i] && B[i - 1] < B[i];
  //     boolean swapIncr = A[i - 1] < B[i] && B[i - 1] < A[i];
  //     if (incr && swapIncr) {
  //       dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
  //       dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
  //     } else if (incr) {
  //        // do the same as previous action
  //       dp[i][0] = dp[i - 1][0];
  //       dp[i][1] = dp[i - 1][1] + 1;
  //     } else if (swapIncr) {
  //       // swap the previous action
  //       dp[i][0] = dp[i - 1][1];
  //       dp[i][1] = dp[i - 1][0] + 1;
  //     }
  //   }
  //   return Math.min(dp[n - 1][0], dp[n - 1][1]);
  // }

  // Using recursion
//     public int minSwap(int[] A, int[] B) {
//       int res = 0;
//       int n = A.length;
//       if (n <= 1) {
//         return n;
//       }
//       int[][] dp = new int[n][2];
//       for (int[] row : dp) {
//         Arrays.fill(row, -1);
//       }
//       return dfs(A, B, -1, -1, 0, dp, 0);
//     }

//     int dfs(int[] A, int[] B, 
//             int preva, int prevb,
//             int pos, int[][] dp, int flip) {
      
//       int n = A.length;
//       if (pos == n) {
//         return 0;
//       }
//       if (dp[pos][flip] != -1) {
//         return dp[pos][flip];
//       }
//       int res = Integer.MAX_VALUE;
//       if (preva < A[pos] && prevb < B[pos]) {
//         res = dfs(A, B, A[pos], B[pos], pos + 1, dp, 0);
//       }
//       swap(A, B, pos);
//       if (preva < A[pos] && prevb < B[pos]) {
//         res = Math.min(res, dfs(A, B, A[pos], B[pos], pos + 1, dp, 1) + 1);
//       }
//       swap(A, B, pos);
//       dp[pos][flip] = res;
//       return res;
//     }

    // void swap(int[] A, int[] B, int i) {
    //   int t = A[i];
    //   A[i] = B[i];
    //   B[i] = t;
    // }
  }
