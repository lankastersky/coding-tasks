/*
Compare Strings by Frequency of the Smallest Character

Let's define a function f(s) over a non-empty string s, which calculates the frequency of 
the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest
character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each 
answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.


Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").

Example 2:
Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and
f("aaaa") are both > f("cc").
 
Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.

https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/
*/

class Solution {
  int AB = 26;
  
  
  public int[] numSmallerByFrequency(String[] queries, String[] words) {
    int n = words.length;
    int m = queries.length;
    int[] res = new int[m];

    // O(n*m)
    // int[] wordscache = new int[n];
    // for (int i = 0; i < n; i++) {
    //   wordscache[i] = f(words[i]);
    // }
    // for (int i = 0; i < m; i++) {
    //   int fq = f(queries[i]);
    //   for (int j = 0; j < n; j++) {
    //     if (fq < wordscache[j]) {
    //       res[i]++;
    //     }
    //   }
    // }
    
    int FREQ_SIZE = 11;  // words[i].length <= 10
    int[] freqs = new int[FREQ_SIZE];
    for (int i = 0; i < n; i++) {
      freqs[f(words[i])]++;
    }
    int[] cum = new int[FREQ_SIZE + 1];
    for (int i = FREQ_SIZE - 1; i >= 0; i--) {
      cum[i] = freqs[i] + cum[i + 1];
    }
    for (int i = 0; i < m; i++) {
      int fq = f(queries[i]);
      res[i] = cum[fq + 1];
    }
    return res;
  }
  
  int f(String s) {
    int[] cnt = new int[AB];
    for (char c : s.toCharArray()) {
      cnt[c - 'a']++;
    }
    for (int i = 0; i < AB; i++) {
      if (cnt[i] != 0) {
        return cnt[i];    
      }
    }
    return 0;
  }
}
