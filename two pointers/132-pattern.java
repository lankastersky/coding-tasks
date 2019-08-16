/*
132 Pattern

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that 
i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether 
there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

https://leetcode.com/problems/132-pattern/
*/

class Solution {
    // O(n^2). See O(n) at https://leetcode.com/articles/132-pattern/
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int n = nums.length;
        int mini = nums[0];
        for (int j = 1; j < n - 1; j++) {
            mini = Math.min(mini, nums[j]);
            for (int k = j + 1; k < n; k++) {
                if (nums[k] < nums[j] && mini < nums[k]) {
                    return true;
                }
            }
        }
        return false;
    }
/*
    // O(n^3)
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        min[n - 1] = nums[n - 1];
        max[n - 1] = nums[n - 1];
        int minval = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
          min[i] = Math.min(min[i + 1], nums[i]);
          max[i] = Math.max(max[i + 1], nums[i]); 
        }
        for (int i = 0; i < n - 2; i++) {
            int ai = nums[i];
            if (ai >= minval) {
                continue;
            }
            minval = ai;
            if (ai >= max[i + 1]) {
                continue;
            }
            int maxval = Integer.MIN_VALUE;
            for (int k = i + 1; k < n - 1; k++) {
                int ak = nums[k];
                if (ak <= min[k + 1]) {
                    continue;
                }
                if (ai >= max[k + 1]) {
                    continue;
                }
                if (ak <= ai) {
                    continue;
                }
                if (ak <= maxval) {
                    continue;
                }
                maxval = ak;
                for (int j = k + 1; j < n; j++) {
                    int aj = nums[j];
                    if (ai < aj && ak > aj) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
*/
}
