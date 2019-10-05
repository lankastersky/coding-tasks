/*
Target Sum

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.

https://leetcode.com/problems/target-sum/
*/

class Solution {

  // Based on https://leetcode.com/problems/target-sum/discuss/97335/Short-Java-DP-Solution-with-Explanation
  public int findTargetSumWays(int[] nums, int S) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int sum = 0;
    for (int el : nums) {
      sum += el;
    }
    if (Math.abs(sum) < S) {
      return 0;
    }
    // Stores sums in range [-sum; + sum] shifted to avoid negative indices.
    int[][] dp = new int[n + 1][2 * sum + 1];
    dp[0][sum + 0] = 1;
    for (int i = 1; i <= n; i++) {
      int el = nums[i - 1];
      for (int j = 0; j < 2 * sum + 1; j++) {
        if (j - el >= 0) {
          dp[i][j] += dp[i - 1][j - el];
        }
        if (j + el <= 2 * sum) {
          dp[i][j] += dp[i - 1][j + el];
        }
      }
    }
    return dp[n][sum + S];
  }
  
  // Working solution
  // Based on https://leetcode.com/problems/target-sum/discuss/97333/Java-simple-DFS-with-memorization
//   public int findTargetSumWays(int[] nums, int S) {
//     int n = nums.length;
//     if (n == 0) {
//       return 0;
//     }
//     int sum = 0;
//     for (int el : nums) {
//       sum += el;
//     }
//     //int[][] dp = new int[n + 1][sum + 1];
//     Map<String, Integer> dp = new HashMap<>();
//     return rec(nums, 0, S, dp);
//   }
  
//   int rec(int[] nums, int ind, int S, Map<String, Integer> dp) {
//     if (ind == nums.length) {
//       return S == 0 ? 1 : 0;
//     }
//     String key = String.format("%d_%d", ind, S);
//     if (dp.containsKey(key)) {
//       return dp.get(key);
//     }
//     int res = 0;
//     int el = nums[ind];
//     res += rec(nums, ind + 1, S - el, dp);
//     res += rec(nums, ind + 1, S + el, dp);
//     dp.put(key, res);
//     return res;
//   }
}
