/*
Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

https://leetcode.com/problems/word-break-ii/
*/

class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    boolean[] memo = new boolean[s.length()];
    List<String> res = new ArrayList<>();
    check(s, 0, new StringBuilder(), dict, res, memo);
    return res;
  }
  
  void check(
    String s, int pos, 
    StringBuilder cur, 
    Set<String> dict, 
    List<String> res, 
    boolean[] memo) {
    
    int n = s.length();
    if (pos == n) {
      res.add(cur.toString());
      return;
    }
    if (memo[pos]) {
      return;
    }
    int ressize = res.size();
    for (int i = pos + 1; i <= n; i++) {
      String subs = s.substring(pos, i);
      if (!dict.contains(subs)) {
        continue;
      }
      if (pos != 0) {
        cur.append(" ");        
      }
      cur.append(subs);
      check(s, i, cur, dict, res, memo);
      cur.setLength(cur.length() - subs.length());
      if (pos != 0) {
        cur.setLength(cur.length() - 1);
      }
    }
    if (ressize == res.size()) {
      memo[pos] = true;
    }
  }
}
