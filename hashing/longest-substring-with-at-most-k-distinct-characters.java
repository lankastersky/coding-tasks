/*
Longest Substring with At Most K Distinct Characters

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
*/

class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (k == 0 || s.isEmpty()) {
      return 0;
    }
    int[] cnt = new int[256];
    int start = 0;
    int res = 0;
    int total = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (cnt[c] == 0) {
        total++;
      }
      cnt[c]++;
      if (total > k) {
        res = Math.max(res, i - start);
        while (start < i) {
          cnt[s.charAt(start)]--;
          start++;
          if (cnt[s.charAt(start - 1)] == 0) {
            total--;
            break;
          }
        }
      }
    }
    if (total <= k) {
      res = Math.max(res, s.length() - start);        
    }
    return res;
  }
}
