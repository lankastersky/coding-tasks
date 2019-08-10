/*
Maximum Size Subarray Sum Equals k

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one,
return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?

leetcode - facebook online assessment
https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int[] acc = new int[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            acc[i + 1] = acc[i] + nums[i];
            if (!map.containsKey(acc[i + 1])) {
                map.put(acc[i + 1], i);                
            }
            int key = acc[i + 1] - k;
            if (map.containsKey(key)) {
                max = Math.max(i - map.get(key), max);
            }
        }
        return max;
    }
    
    // O(n^2)
    // public int maxSubArrayLen(int[] nums, int k) {
    //     int[] acc = new int[nums.length + 1];
    //     for (int i = 0; i < nums.length; i++) {
    //         acc[i + 1] = acc[i] + nums[i];
    //     }
    //     int max = 0;
    //     for (int i = 0; i <= nums.length; i++) {
    //         for (int j = i + 1; j <= nums.length; j++) {
    //             if (acc[j] - acc[i] == k) {
    //                 max = Math.max(j - i, max);
    //             }
    //         }
    //     }
    //     return max;
    // }
}
