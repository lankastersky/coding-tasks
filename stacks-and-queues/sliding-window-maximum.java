/*
Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very 
left of the array to the very right. You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

https://leetcode.com/problems/sliding-window-maximum/
*/

class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
      return new int[0];
    }
    int[] res = new int[n - k + 1];
    MaxQueue maxQueue = new MaxQueue();
    for (int i = 0; i < k; i++) {
      maxQueue.add(nums[i]);
    }
    res[0] = maxQueue.max();
    for (int i = k; i < n; i++) {
      maxQueue.remove();
      maxQueue.add(nums[i]);
      res[i - k + 1] = maxQueue.max();
    }
    return res;
  }
  
  class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> maxes;
    
    MaxQueue() {
      queue = new LinkedList<>();
      maxes = new LinkedList<>();
    }
    
    void add(int el) {
      queue.add(el);
      while (!maxes.isEmpty() && maxes.getLast() < el) {
        maxes.removeLast();
      }
      maxes.addLast(el);
    }
    
    void remove() {
      if (queue.isEmpty()) {
        return;
      }
      int rem = queue.remove();
      if (maxes.getFirst() == rem) {
        maxes.removeFirst();
      }
    }
    
    Integer max() {
      if (!maxes.isEmpty()) {
        return maxes.getFirst();
      }
      return null;
    }
  }  
}
