/*
Subsets II

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

https://leetcode.com/problems/subsets-ii/
*/

class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, 0, new ArrayList<>(), res);
    return res;
  }

  void dfs(int[] nums, int ind, ArrayList<Integer> cur, List<List<Integer>> res) {
    int n = nums.length;
    res.add(new ArrayList<>(cur));
    for (int i = ind; i < n; i++) {
      if (i > ind && nums[i - 1] == nums[i]) {
        continue;
      }
      cur.add(nums[i]);
      dfs(nums, i + 1, cur, res);
      cur.remove(cur.size() - 1);
    }
  }

  // Also a working solution.
//   void dfs(int[] nums, int ind, ArrayList<Integer> cur, List<List<Integer>> res) {
//     int n = nums.length;
//     if (ind == n) {
//       res.add(new ArrayList<>(cur));
//       return;
//     }
    
//     int similar = 1;
//     for (int i = ind + 1; i < n; i++) {
//       if (nums[i] != nums[i - 1]) {
//         break;
//       }
//       similar++;
//     }

//     // without cur el
//     dfs(nums, ind + similar, cur, res);

//     // include cur el
//     for (int i = 0; i < similar; i++) {
//       cur.add(nums[ind + i]);
//       dfs(nums, ind + similar, cur, res);
//     }
    
//     cur.subList(cur.size() - similar, cur.size()).clear();
//   }
}
