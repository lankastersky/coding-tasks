/*
Missing Ranges

Given a sorted integer array nums, where the range of elements are in 
the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

https://leetcode.com/problems/missing-ranges/
*/

class Solution {
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> res = new ArrayList<>();
    int n = nums.length;
    if (n == 0) {
      res.add(range(lower, upper));
      return res;
    }
    if (upper < nums[0] || lower > nums[n - 1]) {
      res.add(range(lower, upper));
      return res;
    }
    if (lower < nums[0]) {
      res.add(range(lower, nums[0] - 1));
    }
    for (int i = 1; i < n; i++) {
      long prev = nums[i - 1];
      long next = nums[i];
      if (prev + 1 < next) {
        res.add(range((int) (prev + 1), (int) (next - 1)));
      }
    }
    if (nums[n - 1] < upper) {
      res.add(range(nums[n - 1] + 1, upper));
    }    
    return res;
  }
  
  String range(int l, int u) {
    if (l == u) {
      return String.valueOf(l);
    }
    return String.format("%d->%d", l, u);
  }

}
