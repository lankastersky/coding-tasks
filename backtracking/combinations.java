/*
Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

https://leetcode.com/problems/combinations/
*/

class Solution {
  public List<List<Integer>> combine(int n, int k) {
    if (n < k) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    gen(n, k, 1, new ArrayList<>(), res);
    return res;
  }
  
  void gen(int n, int k, int pos, ArrayList<Integer> cur, List<List<Integer>> res) {
    if (cur.size() == k) {
      res.add(new ArrayList<>(cur));
      return;
    }
    for (int i = pos; i <= n; i++) {
      cur.add(i);
      gen(n, k, i + 1, cur, res);
      cur.remove(cur.size() - 1);
    }
  }
}
