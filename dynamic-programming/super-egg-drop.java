/*
Super Egg Drop

You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at
a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any 
floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is,
regardless of the initial value of F?

Example 1:

Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4
 

Note:

1 <= K <= 100

https://leetcode.com/problems/super-egg-drop/
*/

class Solution {
  
  public int superEggDrop(int K, int N) {
    /*
    f(k, t) = // max floor to check with k eggs and t moves
      f(k - 1, t - 1) // broken egg
      + f(k, t - 1) // can check more floors with survived eggs
      + 1
      See https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
    */
    int[][] dp = new int[K + 1][N + 1];
    int t = 0;
    while (dp[K][t] < N) {
      t++;
      for (int k = 1; k <= K; k++) {
        dp[k][t] = dp[k - 1][t - 1] + dp[k][t - 1] + 1;
      }
    }
    return t;
  }
  
  // Working solution
//   public int superEggDrop(int K, int N) {
//     int[][] dp = new int[K + 1][N + 1];
//     return rec(K, N, dp);
//   }
  
//   int rec(int k, int n, int[][] dp) {
//     // If there are no floors, then  
//     // no trials needed. OR if there  
//     // is one floor, one trial needed.  
//     if (n == 0 || n == 1) {
//       return n;
//     }
//     // we need n trials for 1 egg and n floors
//     if (k == 1) {
//       return n;
//     }
//     if (dp[k][n] != 0) {
//       return dp[k][n];
//     }
//     int res = n;
//     int low = 0;
//     int high = n;
//     while (low <= high) {
//       int mid = (low + high) / 2;
//       int left = rec(k - 1, mid - 1, dp);
//       int right = rec(k, n - mid, dp);
//       res = Math.min(res, 1 + Math.max(left, right));
//       if (left == right) {
//         break;
//       }
//       if (left < right) {
//         low = mid + 1;
//       } else {
//         high = mid - 1;
//       }
//     }
//     dp[k][n] = res;
//     return res;
//   }

  // O(kn^2); gives TLE.
//   public int superEggDrop(int K, int N) {
//     int[][] dp = new int[K + 1][N + 1];
//     return rec(K, N, dp);
//   }
  
//   int rec(int k, int n, int[][] dp) {
//     // If there are no floors, then  
//     // no trials needed. OR if there  
//     // is one floor, one trial needed.  
//     if (n == 0 || n == 1) {
//       return n;
//     }
//     // we need n trials for 1 egg and n floors
//     if (k == 1) {
//       return n;
//     }
//     if (dp[k][n] != 0) {
//       return dp[k][n];
//     }
//     int res = n;
//     for (int i = 1; i <= n; i++) {
//       res = Math.min(
//         res,
//         1 + Math.max(
//           rec(k - 1, i - 1, dp),
//           rec(k, n - i, dp)
//         )
//       );
//     }
//     dp[k][n] = res;
//     return res;
//   }
}
