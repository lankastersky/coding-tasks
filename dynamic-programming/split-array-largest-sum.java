/*
Split Array Largest Sum

Given an array which consists of non-negative integers and an integer m, you can split 
the array into m non-empty continuous subarrays. Write an algorithm to minimize the 
largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)

Examples:
Input:
nums = [7,2,5,10,8]
m = 2
Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

https://leetcode.com/problems/split-array-largest-sum/
*/

class Solution {
  // Based on https://leetcode.com/articles/split-array-largest-sum/
  public int splitArray(int[] nums, int m) {
    int n = nums.length;
    long left = 0;
    long right = 0;
    for (int i : nums) {
      if (left < i) {
        left = i;
      }
      right += i;
    }
    long res = right;
    while (left <= right) {
      long mid = (left + right) >> 1;
      int cuts = 1;
      long sum = 0;
      for (int i : nums) {
        if (sum + i > mid) {
          sum = i;
          cuts++;
        } else {
          sum += i;
        }
      }
      if (cuts <= m) {
        right = mid - 1;
        res = Math.min(res, mid);
      } else {
        left = mid + 1;
      }
    }
    return (int) res;
  }
  
  // public int splitArray(int[] nums, int m) {
  //   /* 
  //   let's d[i][j] - nums[0:i] split in j parts
  //   dp[i][j] = Math.max(dp[k][j - 1], nums[k + 1] + .. + nums[i])
  //   */
  //   int n = nums.length;
  //   int[][] dp = new int[n + 1][m + 1];
  //   int[] sums = new int[n + 1];
  //   for (int i = 0; i < n; i++) {
  //     sums[i + 1] = sums[i] + nums[i];
  //   }
  //   for (int[] row: dp) {
  //     Arrays.fill(row, Integer.MAX_VALUE);
  //   }
  //   dp[0][0] = 0;
  //   for (int i = 1; i <= n; i++) {
  //     for (int j = 1; j <= m; j++) {
  //       for (int k = 0; k < i; k++) {
  //         dp[i][j] = Math.min(
  //           dp[i][j], 
  //           Math.max(dp[k][j - 1], sums[i] - sums[k]));
  //       }
  //     }
  //   }
  //   return dp[n][m];
  // }
  
  // Doesn't work with such memoization
  //   long min;
//   public int splitArray(int[] nums, int m) {
//     min = Integer.MAX_VALUE;
//     int n = nums.length;
//     boolean[][] dp = new boolean[n][m + 1];
//     // for (int[] row : dp) {
//     //   Arrays.fill(row, -1);
//     // }
//     dfs(nums, 0, m, 0, dp);
//     return (int) min;
//   }

//   void dfs(int[] nums, int pos, int m, long ans, boolean[][] dp) {
//     int n = nums.length;
//     if (m == 0 && pos == n) {
//       min = Math.min(min, ans);
//       return;
//     }
//     if (m == 0 || pos == n) {
//       return;
//     }
//     if (dp[pos][m]) {
//       return;
//     }
//     long sum = 0;
//     for (int i = pos; i <= n - m; i++) {
//       sum += nums[i];
//       dfs(nums, i + 1, m - 1, Math.max(sum, ans), dp);
//     }
//     dp[pos][m] = true;
//   }
}
