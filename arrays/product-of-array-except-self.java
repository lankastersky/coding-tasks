/*
Product of Array Except Self

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal 
to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space 
for the purpose of space complexity analysis.)

https://leetcode.com/problems/product-of-array-except-self/
*/

class Solution {
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    //int[] sufs = new int[n];
    int prod = 1;
    for (int i = n - 1; i >= 0; i--) {
      res[i] = prod * nums[i];
      prod *= nums[i];
    }
    prod = 1;
    for (int i = 0; i < n; i++) {
      if (i + 1 < n) {
        res[i] = prod * res[i + 1];
        prod *= nums[i];
      } else {
        res[i] = prod;
      }
    }
    return res;
  }
}
