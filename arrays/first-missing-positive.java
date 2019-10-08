/*
First Missing Positive

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.

https://leetcode.com/problems/first-missing-positive/
*/

  class Solution {
    public int firstMissingPositive(int[] nums) {
      int i = 0;
      int n = nums.length;

      // int min = Integer.MAX_VALUE;
      // for (int el : nums) {
      //   min = Math.min(min, el > 0 ? el : min);
      // }
      // if (min == Integer.MAX_VALUE) {
      //   return 1;
      // }
      int min = 1;

      while (i < n) {
        if (nums[i] > 0 && nums[i] < n + min && nums[nums[i] - min] != nums[i]) {
          swap(nums, i, nums[i] - min);
        } else {
          i++;
        }
      }

      for (i = 0; i < n; i++) {
        if (nums[i] != i + min) {
          return i + min;
        }
      }
      return n + min;
    }

    void swap(int[] nums, int i, int j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
    }
  }
