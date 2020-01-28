/*
Number of Atoms

Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters,
representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1.
If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) 
and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name 
(in sorted order), followed by its count (if that count is more than 1), followed by the second name 
(in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.

Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

Note:
All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined 
in the problem.

https://leetcode.com/problems/number-of-atoms/
*/

class Solution {
  int ind;
  public String countOfAtoms(String formula) {
    TreeMap<String, Integer> map = dfs(formula);
    return atomsString(map);
  }
  
  TreeMap<String, Integer> dfs(String s) {
    TreeMap<String, Integer> map = new TreeMap<>();
    int n = s.length();
    while (ind < n) {
      char c = s.charAt(ind);
      if (c == '(') {
        ind++;
        TreeMap<String, Integer> addMap = dfs(s);
        int count = parseInt(s);
        mult(addMap, count);
        merge(map, addMap);
      } else if (c == ')') {
        ind++;
        return map;
      } else {
        parseAtom(s, map);
      }
    }
    return map;
  }
  
  void parseAtom(String s, Map<String, Integer> map) {
    String atom = parseStr(s);
    int count = parseInt(s);
    if (count == 0) {
      count = 1;
    }
    map.put(atom, map.getOrDefault(atom, 0) + count);
  }
  
  String parseStr(String s) {
    int n = s.length();
    char c = s.charAt(ind);
    String res = "";
    if ('A' <= c && c <= 'Z') {
      res = res + c;
      ind++;
      while (ind < n && 'a' <= s.charAt(ind) && s.charAt(ind) <= 'z') {
        res = res + s.charAt(ind);
        ind++;
      }
    }
    return res;
  }
  
  int parseInt(String s) {
    int n = s.length();
    String num = "";
    while (ind < n && Character.isDigit(s.charAt(ind))) {
      num = num + s.charAt(ind);
      ind++;
    }
    if (num.isEmpty()) {
      return 0;
    }
    return Integer.valueOf(num);
  }
  
  void mult(Map<String, Integer> map, int num) {
    if (num == 0) {
      return;
    }
    for (String key : map.keySet()) {
      map.put(key, map.get(key) * num);
    }  
  }
  
  void merge(Map<String, Integer> map, Map<String, Integer> addMap) {
    for (String key : addMap.keySet()) {
      map.put(key, map.getOrDefault(key, 0) + addMap.get(key));
    }  
  }
  
  String atomsString(TreeMap<String, Integer> map) {
    StringBuilder sb = new StringBuilder();
    for (String key : map.keySet()) {
      sb.append(key);
      int count = map.get(key);
      if (count > 1) {
        sb.append(count);        
      }
    }
    return sb.toString();
  }
}
