/*
 Sort Colors

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and 
followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

https://leetcode.com/problems/sort-colors/
*/

class Solution {
    int R = 0;
    int W = 1;
    int B = 2;
    public void sortColors(int[] nums) {
      int n = nums.length;
      int r = 0;
      int b = n - 1;
      
      for (int i = 0; i <= b; i++) {
        if (nums[i] == R) {
          if (i == r) {
            continue;
          }
          swap(nums, i, r);
          r++;
          i--;
        } else if (nums[i] == B) {
          if (i == b) {
            continue;
          }
          swap(nums, i, b);
          b--;
          i--;
        }
      }
    }
  
  void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}