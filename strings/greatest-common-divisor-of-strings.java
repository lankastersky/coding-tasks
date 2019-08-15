/*
Greatest Common Divisor of Strings

For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""
 

Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.

https://leetcode.com/problems/greatest-common-divisor-of-strings/
*/

class Solution {
    public String gcdOfStrings(String s, String p) {
        int n = s.length();
        int m = p.length();
        StringBuilder sb = new StringBuilder();
        String res = "";
        for (int i = 1; i <= m; i++) {
            sb.append(p.charAt(i - 1));
            if ((n % i != 0) || (m % i != 0)) {
                continue;
            }
            if (!div(p, sb.toString())) {
                continue;
            }
            if (!div(s, sb.toString())) {
                continue;
            }
            if (sb.length() > res.length()) {
                res = sb.toString();
            }
        }
        return res;
    }
    
    boolean div(String s, String p) {
        int i = 0;
        int n = s.length();
        int m = p.length();
        if (n % m != 0) {
            return false;
        }
        for (; i < n; i++) {
            if (s.charAt(i) != p.charAt(i % m)) {
                return false;
            }
        }
        return true;
    }
}
