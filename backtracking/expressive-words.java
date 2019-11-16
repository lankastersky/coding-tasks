/*
Expressive Words

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo",
"hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters 
that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any 
number of applications of the following extension operation: choose a group consisting of 
characters c, and add some number of characters c to the group so that the size of the group
is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another
extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word
"hello" would be stretchy because of these two extension operations: 
query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 
Notes:
0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

https://leetcode.com/problems/expressive-words/
*/

// There is a faster solution: https://leetcode.com/problems/expressive-words/solution/
class Solution {
  public int expressiveWords(String S, String[] words) {
    int res = 0;
    int m = S.length();
    for (String w : words) {
      int n = w.length();
      boolean[][] memo = new boolean[m][n];
      if (stretchy(S, w, 0, 0, memo)) {
        res++;
      }
    }
    return res;
  }
  
  boolean stretchy(String s, String w, int spos, int wpos, boolean[][] memo) {
    int m = s.length();
    int n = w.length();
    if (spos == m && wpos == n) {
      return true;
    }
    if (spos == m || wpos == n) {
      return false;
    }
    if (memo[spos][wpos]) {
      return false;
    }
    int sc = s.charAt(spos);
    int wc = w.charAt(wpos);
    if (sc != wc) {
      return false;
    }
    if (stretchy(s, w, spos + 1, wpos + 1, memo)) {
      return true;
    }
    boolean stretch = false;
    if (spos + 2 < m && s.charAt(spos + 1) == sc && s.charAt(spos + 2) == sc) {
      stretch = true;
    }
    if (stretch) {
      if (stretchy(s, w, spos + 1, wpos, memo)) {
        return true;
      }      
      if (stretchy(s, w, spos + 2, wpos, memo)) {
        return true;
      }      
    }
    memo[spos][wpos] = true;
    return false;
  }
}
