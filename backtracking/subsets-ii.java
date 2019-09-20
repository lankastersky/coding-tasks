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

import java.util.*;

class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    NavigableMap<Integer, Integer> freqMap = buildFreqMap(nums);
    List<List<Integer>> res = new ArrayList<>();
    dfs(0, new ArrayList<>(), res, freqMap);
    return res;
  }

  NavigableMap<Integer, Integer> buildFreqMap(int[] nums) {
    NavigableMap<Integer, Integer> freqMap = new TreeMap<>();
    for (int a : nums) {
      freqMap.put(a, freqMap.getOrDefault(a, 0) + 1);
    }
    return freqMap;
  }

  void dfs(
    int ind, ArrayList<Integer> cur, 
    List<List<Integer>> res, 
    NavigableMap<Integer, Integer> freqMap) {
    
    res.add(new ArrayList<>(cur));
    
    List<Integer> keys = new ArrayList<>(freqMap.keySet());
    for (int i = ind; i < keys.size(); i++) {
      int key = keys.get(i);
      if (freqMap.get(key) > 0) {
        freqMap.put(key, freqMap.get(key) - 1);
        cur.add(key);
        dfs(i, cur, res, freqMap);
        cur.remove(cur.size() - 1);
        freqMap.put(key, freqMap.get(key) + 1);
      }
    }
  }
  
  // A working solution.
//   public List<List<Integer>> subsetsWithDup(int[] nums) {
//     Arrays.sort(nums);
//     List<List<Integer>> res = new ArrayList<>();
//     dfs(nums, 0, new ArrayList<>(), res);
//     return res;
//   }

//   void dfs(int[] nums, int ind, ArrayList<Integer> cur, List<List<Integer>> res) {
//     int n = nums.length;
//     res.add(new ArrayList<>(cur));
//     for (int i = ind; i < n; i++) {
//       if (i > ind && nums[i - 1] == nums[i]) {
//         continue;
//       }
//       cur.add(nums[i]);
//       dfs(nums, i + 1, cur, res);
//       cur.remove(cur.size() - 1);
//     }
//   }

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
