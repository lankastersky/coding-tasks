/*
Partition Equal Subset Sum

Given a non-empty array containing only positive integers, find if the array can be partitioned 
into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

https://leetcode.com/problems/partition-equal-subset-sum/
*/

class Solution {
  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }
    if (sum % 2 != 0) {
      return false;
    }
    int target = sum / 2;
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][target + 1];
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;      
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= target; j++) {
        dp[i][j] = dp[i - 1][j];
        if (j >= nums[i - 1]) {
          dp[i][j] |= dp[i - 1][j - nums[i - 1]];
        }
      }
    }
    return dp[n][target];
  }
}
