/*
Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order, find the starting 
and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
*/

class Solution {
  // Based on https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14701/A-very-simple-Java-solution-with-only-one-binary-search-algorithm
  public int[] searchRange(int[] nums, int target) {
    int n = nums.length;
    int left = greaterThen(nums, target);
    int[] res = new int[] {-1,-1};
    if (left == n || nums[left] != target) {
      return res;
    }
    res[0] = left;
    res[1] = greaterThen(nums, target + 1) - 1;
    return res;
  }
  
  int greaterThen(int[] nums, int target) {
    int n = nums.length;
    int left = 0;
    int right = n;
    while (left < right) {
      int mid = (left + right) >> 1;
      if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

}
