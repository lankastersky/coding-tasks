/*
String Transforms Into Another String

Given two strings str1 and str2 of the same length, determine whether you can transform 
str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other 
lowercase English character.

Return true if and only if you can transform str1 into str2.

Example 1:
Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of 
conversions matter.

Example 2:
Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.
 
Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.

https://leetcode.com/problems/string-transforms-into-another-string/
*/

class Solution {
  // Based on https://leetcode.com/problems/string-transforms-into-another-string/discuss/399352/Complete-Logical-Thinking-(This-is-why-only-check-if-str2-has-unused-character)
  public boolean canConvert(String str1, String str2) {
    if (str1.equals(str2)) {
      return true;
    }
    HashMap<Character, Character> map = new HashMap<>();
    for (int i = 0; i < str1.length(); i++) {
      char c1 = str1.charAt(i);
      char c2 = str2.charAt(i);
      if (map.getOrDefault(c1, c2) != c2) {
        return false;
      }
      map.put(c1, c2);
    }
    return (new HashSet<Character>(map.values()).size() < 26);
  }
  // Doesn't work for "ab", "aa"
  // public boolean canConvert(String str1, String str2) {
  //   int n = str1.length();
  //   if (n != str2.length()) {
  //     return false;
  //   }
  //   HashMap<Character, Integer> map1 = new HashMap<>();
  //   HashMap<Character, Integer> map2 = new HashMap<>();
  //   for (int i = 0; i < n; i++) {
  //     char c1 = str1.charAt(i);
  //     char c2 = str2.charAt(i);
  //     int pos1 = map1.getOrDefault(c1, 0);
  //     int pos2 = map2.getOrDefault(c2, 0);
  //     if (pos1 != pos2) {
  //       return false;
  //     }
  //     map1.put(c1, i);
  //     map2.put(c2, i);
  //   }
  //   return map1.size() < 26;
  // }
}
