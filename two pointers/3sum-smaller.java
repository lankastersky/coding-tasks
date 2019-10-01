/*
3Sum Smaller

Given an array of n integers nums and a target, find the number of index triplets
i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?

https://leetcode.com/problems/3sum-smaller/
*/

class Solution {
  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int res = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      res += twoSums(nums, target - nums[i], i);
    }
    return res;
  }
  
  int twoSums(int[] nums, int target, int i) {
    int n = nums.length;
    int left = i + 1;
    int right = n - 1;
    int res = 0;
    while (left < right) {
      if (nums[left] + nums[right] < target) {
        res += right - left;
        left++;
      } else {
        right--;        
      }
    }
    return res;
  }
}
