/*
Task Scheduler

Given a char array representing tasks CPU need to do. It contains capital letters 
A to Z where different letters represent different tasks. Tasks could be done without
original order. Each task could be done in one interval. For each interval, CPU could 
finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 
Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

https://leetcode.com/problems/task-scheduler/
*/

class Solution {
  // Based on https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
  public int leastInterval(char[] tasks, int n) {
    int size = tasks.length;
    int[] cnt = new int[26];
    int max = 0;
    int maxCount = 0;
    for (char task : tasks) {
      cnt[task - 'A']++;
      if (max < cnt[task - 'A']) {
        max = cnt[task - 'A'];
        maxCount = 1;
      } else if (max == cnt[task - 'A']) {
        maxCount++;
      }
    }
    // Integer[] cnt = new Integer[26];
    // Arrays.fill(cnt, 0);
    // for (char task : tasks) {
    //   cnt[task - 'A']++;
    // }
    // Arrays.sort(cnt,  Collections.reverseOrder());
    // int max = cnt[0];
    // int maxCount = 1;
    // for (int i = 1; i < 26; i++) {
    //   if (cnt[i - 1] != cnt[i]) {
    //     break;
    //   }
    //   maxCount++;
    // }
    int partCount = max - 1;
    int partLength = n - (maxCount - 1);
    int emptySlots = partCount * partLength;
    int availableTasks = size - max * maxCount;
    int idles = emptySlots - availableTasks;
    return idles > 0 ? size + idles : size;
  }
}
