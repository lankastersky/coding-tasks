/*
Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]

https://leetcode.com/problems/strobogrammatic-number-ii/
*/

class Solution {
  public List<String> findStrobogrammatic(int n) {
    ArrayList<String> res = new ArrayList<>();
    if (n <= 0) {
      return res;
    }
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    map.put(1, 1);
    map.put(6, 9);
    map.put(8, 8);
    map.put(9, 6);
    dfs(n, res, new StringBuilder(), map);
    return res;
  }

  void dfs(int n, ArrayList<String> res, StringBuilder sb, Map<Integer, Integer> map) {
    if (n == 0) {
      if (sb.charAt(0) == '0' && sb.length() > 1) {
        return;
      }
      res.add(sb.toString());
      return;
    }
    for (int key : map.keySet()) {
      if (n % 2 == 1) {
        if (key == 6 || key == 9) {
          continue;
        }
        sb.append(key);
        dfs(n - 1, res, sb, map);
        sb.setLength(sb.length() - 1);
      } else {
        sb.insert(0, key);
        sb.append(map.get(key));
        dfs(n - 2, res, sb, map);
        sb.deleteCharAt(0);
        sb.setLength(sb.length() - 1);
      }
    }
  }
}
