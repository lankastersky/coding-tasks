/*
Remove Invalid Parentheses

Remove the minimum number of invalid parentheses in order to make the input 
string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:
Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:
Input: ")("
Output: [""]

https://leetcode.com/problems/remove-invalid-parentheses/
*/

class Solution {
  public List<String> removeInvalidParentheses(String s) {
    int n = s.length();
    ArrayList<String> res = new ArrayList<>();
    if (n == 0) {
      res.add("");
      return res;
    }
    int left = 0;
    int right = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        left++;
      } else if (c == ')') {
        if (left > right) {
          right++;          
        }
      }
    }
    int pairs = Math.min(left, right);
    Set<String> set = new HashSet<>();
    dfs(s, 0, 0, 0, pairs, new StringBuilder(), set);
    if (set.isEmpty()) {
      res.add("");
    } else {
      res.addAll(set);
    }
    return res;
  }
  
  void dfs(String s, int pos, 
           int left, int right,
           int pairs,
           StringBuilder cur, Set<String> set) {
    int n = s.length();
    if (pos == n) {
      if (left == right && left == pairs) {
        set.add(cur.toString());        
      }
      return;
    }
    char c = s.charAt(pos);
    if (c == '(') {
      cur.append(c);
      dfs(s, pos + 1, left + 1, right, pairs, cur, set);
      cur.setLength(cur.length() - 1);
      if (left >= right) { // skip this '('
        dfs(s, pos + 1, left, right, pairs, cur, set);
      } 
    } else if (c == ')') { 
      if (left > right) {
        cur.append(c);
        dfs(s, pos + 1, left, right + 1, pairs, cur, set);
        cur.setLength(cur.length() - 1);
      }
      // skip this ')'
      dfs(s, pos + 1, left, right, pairs, cur, set);        
    } else {
      cur.append(c);
      dfs(s, pos + 1, left, right, pairs, cur, set);
      cur.setLength(cur.length() - 1);      
    }
  }
}
