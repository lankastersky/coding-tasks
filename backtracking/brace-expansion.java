/*
Brace Expansion

A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is 
represented as is.  If there is more than one option, then curly braces delimit the options.
For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]

Example 2:
Input: "abcd"
Output: ["abcd"]
 
Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.

https://leetcode.com/problems/brace-expansion/
*/

class Solution {
  public String[] expand(String S) {
    int sl = S.length();
    ArrayList<ArrayList<Character>> s = new ArrayList<>();
    int i = 0;
    while(i < sl) {
      char c = S.charAt(i);
      ArrayList<Character> option = new ArrayList<>();
      s.add(option);
      if (c == '{') {
        while (i < sl) {
          c = S.charAt(i);
          i++;
          if (c != '{' && c != '}' && c != ',') {
            option.add(c);
          }
          if (c == '}') {
            break;
          }
        }
        Collections.sort(option);
      } else {
        i++;
        option.add(c);
      }
    }
    ArrayList<String> res = new ArrayList<>();
    dfs(s, 0, res, new StringBuilder());
    String[] ans = new String[res.size()];
    ans = res.toArray(ans);
    return ans;
  }

  void dfs(ArrayList<ArrayList<Character>> s, int ind, List<String> res, StringBuilder sb) {
    int sl = s.size();
    if (ind == sl) {
      res.add(sb.toString());
      return;
    }
    ArrayList<Character> option = s.get(ind);
    for (char c : option) {
      sb.append(c);
      dfs(s, ind + 1, res, sb);
      sb.setLength(sb.length() - 1);
    }
  }
}
