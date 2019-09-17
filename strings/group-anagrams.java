/*
Group Anagrams

Share
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

https://leetcode.com/problems/group-anagrams/
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      int n = strs.length;
      if (n == 0) {
        return new ArrayList<>();
      }
      Map<String, List<String>> map = new HashMap<>();
      for (int i = 0; i < n; i++) {
        String s = strs[i];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        List<String> list =  map.getOrDefault(sorted, new ArrayList<>());
        list.add(s);
        map.put(sorted, list);
      }
      return new ArrayList<>(map.values());
    }
}
