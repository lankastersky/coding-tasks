/*
Longest Increasing Subsequence

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

https://leetcode.com/problems/longest-increasing-subsequence/
*/

class Solution {

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return n;
    }
    int[] dp = new int[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
      int pos = Arrays.binarySearch(dp, 0, res, nums[i]);
      if (pos < 0) {
        pos = -(pos + 1);
      }
      dp[pos] = nums[i];
      res = Math.max(res, pos + 1);
    }
    return res;
  }
  
  // O(n^2)
  // public int lengthOfLIS(int[] nums) {
  //   int n = nums.length;
  //   if (n <= 1) {
  //     return n;
  //   }
  //   int[] dp = new int[n + 1];
  //   int max = 0;
  //   for (int i = 1; i <= n; i++) {
  //     for (int j = 1; j < i; j++) {
  //       if (nums[j - 1] < nums[i - 1]) {
  //         dp[i] = Math.max(dp[i], dp[j] + 1);
  //       }
  //     }
  //     max = Math.max(max, dp[i]);
  //   }
  //   return max + 1;
  // }
}
