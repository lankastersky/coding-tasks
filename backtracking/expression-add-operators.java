/*
Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all
possibilities to add binary operators (not unary) +, -, or * between the digits
so they evaluate to the target value.

Example 1:
Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Example 3:
Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Example 4:
Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]

Example 5:
Input: num = "3456237490", target = 9191
Output: []

https://leetcode.com/problems/expression-add-operators/
*/

class Solution {
  // Based on https://leetcode.com/problems/expression-add-operators/solution/
  public List<String> addOperators(String num, int target) {
    ArrayList<String> res = new ArrayList<>();
    if (num.length() == 0) {
      return res;
    }
    Set<String> set = new TreeSet<>();
    dfs(num, target, 0, new StringBuilder(), 0, 0, set);
    res.addAll(set);
    return res;
  }
  
  void dfs(String num, int target, 
          int pos, StringBuilder sb, 
          long cur, long tray,
         Set<String> res) {
    int n = num.length();
    if (pos == n) {
      if (cur == target) {
        res.add(sb.toString());
      }
      return;
    }
    StringBuilder valStr = new StringBuilder();
    for (int i = pos; i < n; i++) {
      char c = num.charAt(i);
      valStr.append(c);
      long val = Long.valueOf(valStr.toString());
      if (String.valueOf(val).length() != valStr.length()) {
        break;
      }
            
      if (pos == 0) {        
        sb.append(valStr);
        dfs(num, target, i + 1, sb, val, val, res);                
        sb.setLength(0);
      } else {
        sb.append("+" + valStr);
        dfs(num, target, i + 1, sb, cur + val, val, res);
        sb.setLength(sb.length() - valStr.length() - 1);

        sb.append("-" + valStr);
        dfs(num, target, i + 1, sb, cur - val, -val, res);
        sb.setLength(sb.length() - valStr.length() - 1);
        
        sb.append("*" + valStr);
        dfs(num, target, i + 1, sb, cur - tray + (val * tray), val * tray, res);        
        sb.setLength(sb.length() - valStr.length() - 1);
      }
    }
  }
  
//   void dfs(String num, int target, int pos, StringBuilder cur, ArrayList<String> res) {
//     int n = num.length();
//     if (pos == n) {
//       int ans = calc(cur.toString());
//       if (ans == target) {
//         res.add(cur.toString());
//       }
//       return;
//     }
//     // no op
//     cur.append(num.charAt(pos));
//     dfs(num, target, pos + 1, cur, res);
//     cur.setLength(cur.length() - 1);
    
//     // + op
//     cur.append(num.charAt(pos));
//     cur.append('+');
//     dfs(num, target, pos + 1, cur, res);
//     cur.setLength(cur.length() - 2);

//     // - op
//     cur.append(num.charAt(pos));
//     cur.append('-');
//     dfs(num, target, pos + 1, cur, res);
//     cur.setLength(cur.length() - 2);

//     // * op
//     cur.append(num.charAt(pos));
//     cur.append('*');
//     dfs(num, target, pos + 1, cur, res);
//     cur.setLength(cur.length() - 2);
//   }
  
//   int calc(String s) {
//     // Set<Character> ops = new HashSet<>();
//     // ops.add('')
//     int res = 0;
//     int num = 0;
//     int n = s.length();
//     int i = 0;
//     Stack<String> stack = new Stack<>();
//     while (i < 0) {
//       char c = s.charAt(i);
//       i++;
//       // TODO
//     }
//     return res;
//   }
}
