/*
Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

https://leetcode.com/problems/next-permutation/
*/

class Solution {
  public void nextPermutation(int[] nums) {
    int n = nums.length;
    boolean sorted = true;
    for (int i = n - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        sorted = false;
        for (int j = n - 1; j > i; j--) {
          if (nums[j] > nums[i]) {
            swap(nums, i, j);
            break;        
          }
        }
        reverse(nums, i + 1);
        break;
      }
    }
    if (sorted) {
      reverse(nums, 0);
    }
  }
  
  void reverse(int[] nums, int i) {
    int n = nums.length;
    int left = i;
    int right = n - 1;
    while (left < right) {
      swap(nums, left, right);
      left++;
      right--;
    }
  }
  
  void swap(int[] nums, int i, int j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;    
  }
}
