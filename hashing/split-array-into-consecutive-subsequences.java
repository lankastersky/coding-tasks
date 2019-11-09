/*
Split Array into Consecutive Subsequences

Given an array nums sorted in ascending order, return true if and only if you can split it 
into 1 or more subsequences such that each subsequence consists of consecutive integers and 
has length at least 3.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:
Input: [1,2,3,4,4,5]
Output: False
 
Constraints:

1 <= nums.length <= 10000

https://leetcode.com/problems/split-array-into-consecutive-subsequences/
*/

  class Solution {
    
    // O(n), based on https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106493/C%2B%2B-O(n)-solution-two-pass
    public boolean isPossible(int[] nums) {
      int N = nums.length;
      if (N < 3) {
        return false;
      }
      Map<Integer, Integer> cnt = new HashMap<>();
      Map<Integer, Integer> tails = new HashMap<>();
      for (int i : nums) {
        cnt.put(i, cnt.getOrDefault(i, 0) + 1);
      }
      for (int i : nums) {
        if (cnt.get(i) == 0) {
          continue;
        }
        if (tails.getOrDefault(i - 1, 0) > 0) {
          tails.put(i - 1, tails.get(i - 1) - 1);
          tails.put(i, tails.getOrDefault(i, 0) + 1);
        } else if (cnt.getOrDefault(i + 1, 0) > 0 && cnt.getOrDefault(i + 2, 0) > 0) {
          cnt.put(i + 1, cnt.get(i + 1) - 1);
          cnt.put(i + 2, cnt.get(i + 2) - 1);
          tails.put(i + 2, tails.getOrDefault(i + 2, 0) + 1);
        } else {
          return false;
        }
        cnt.put(i, cnt.get(i) - 1);
      }
      return true;
    }    
    
    // O(n log n)
    // public boolean isPossible(int[] nums) {
    //   int N = nums.length;
    //   if (N < 3) {
    //     return false;
    //   }
    //   Map<Integer, PriorityQueue<Integer>> tails = new HashMap<>();
    //   for (int i : nums) {
    //     PriorityQueue<Integer> prevHeap = tails.getOrDefault(i - 1, new PriorityQueue<>());
    //     int seqlength = prevHeap.isEmpty() ? 0 : prevHeap.remove();
    //     PriorityQueue<Integer> nextHeap = tails.getOrDefault(i, new PriorityQueue<>());
    //     nextHeap.add(seqlength + 1);
    //     tails.put(i, nextHeap);
    //   }
    //   for (PriorityQueue<Integer> heap : tails.values()) {
    //     while (!heap.isEmpty()) {
    //       if (heap.remove() < 3) {
    //         return false;
    //       }
    //     }
    //   }
    //   return true;
    // }
  }
