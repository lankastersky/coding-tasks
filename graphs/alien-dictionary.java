/*
Alien Dictionary

There is a new alien language which uses the latin alphabet. However, the order 
among letters are unknown to you. You receive a list of non-empty words from the 
dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

Example 1:
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
Output: "wertf"

Example 2:
Input:
[
  "z",
  "x"
]
Output: "zx"

Example 3:
Input:
[
  "z",
  "x",
  "z"
] 
Output: "" 
Explanation: The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

https://leetcode.com/problems/alien-dictionary/
*/

  class Solution {
    int AB = 26;
    int GREY = 1;
    int BLACK = 2;

    // Based on topological sort
    public String alienOrder(String[] words) {
      if (words.length == 0) {
        return "";
      }
      Boolean[][] g = buildGraph(words);
      StringBuilder sb = new StringBuilder();
      if (!topSort(g, sb)) {
        return "";
      }
      return sb.reverse().toString();
    }

    Boolean[][] buildGraph(String[] words) {
      int n = words.length;
      Boolean[][] g = new Boolean[AB][AB];
      for (char c : words[0].toCharArray()) {
        g[c - 'a'][c - 'a'] = true;
      }
      for (int i = 1; i < n; i++) {
        String prev = words[i - 1];
        String cur = words[i];
        for (int j = 0; j < cur.length(); j++) {
          int c = cur.charAt(j) - 'a';
          g[c][c] = true;
        }
        int length = Math.min(prev.length(), cur.length());
        for (int j = 0; j < length; j++) {
          char pc = prev.charAt(j);
          char cc = cur.charAt(j);
          if (pc != cc) {
            g[pc - 'a'][cc - 'a'] = true;
            break;
          }
        }
      }
      return g;
    }

    boolean topSort(Boolean[][] g, StringBuilder res) {
      int[] visited = new int[AB];
      for (int i = 0; i < AB; i++) {
        if (g[i][i] != null) {
          if (!bfs(g, i, res, visited)) {
            return false;
          }
        }
      }
      return true;
    }

    boolean bfs(Boolean[][] g, int cur, StringBuilder res, int[] visited) {
      if (visited[cur] == BLACK) {
        return true;
      }
      if (visited[cur] == GREY) {
        return false;
      }
      visited[cur] = GREY;
      for (int i = 0; i < AB; i++) {
        if (g[cur][i] != null && cur != i) {
          if (!bfs(g, i, res, visited)) {
            return false;
          }
        }
      }
      res.append((char) (cur + 'a'));
      visited[cur] = BLACK;
      return true;
    }
  }
