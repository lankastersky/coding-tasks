/*
Increasing Triplet Subsequence

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false

https://leetcode.com/problems/increasing-triplet-subsequence/
*/

class Solution {
  public boolean increasingTriplet(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return false;
    }
    // we actually don't need arrays but just one var for min and one for max
    int[] max = new int[n];
    int[] min = new int[n];
    min[0] = nums[0];
    for (int i = 1; i < n; i++) {
      min[i] = Math.min(min[i - 1], nums[i]);
    }
    max[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      max[i] = Math.max(max[i + 1], nums[i]);
    }
    for (int i = 0; i < n; i++) {
      if (min[i] < nums[i] && nums[i] < max[i]) {
        return true;
      }
    }
    return false;
  }
}
