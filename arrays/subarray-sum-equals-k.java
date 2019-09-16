/*
Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

https://leetcode.com/problems/subarray-sum-equals-k/
*/

class Solution {
  public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    int[] cum = new int[n + 1];
    for (int i = 0; i < n; i++) {
      cum[i + 1] = cum[i] + nums[i];
    }
    Map<Integer, Set<Integer>> sums = new HashMap<>();
    int res = 0;
    Set<Integer> initsum = new HashSet<>();
    initsum.add(0);
    sums.put(0, initsum);
    for (int i = 1; i <= n; i++) {
      int tar = cum[i] - k;
      if (sums.containsKey(tar)) {
        res += sums.get(tar).size();
      }
      Set<Integer> sum = sums.getOrDefault(cum[i], new HashSet<>());
      sum.add(i);
      sums.put(cum[i], sum);
    }
    return res;
  }
}
