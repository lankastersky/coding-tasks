/*
Longest String Chain

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere 
in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a 
predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 
Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.

https://leetcode.com/problems/longest-string-chain/
*/

class Solution {
  
  public int longestStrChain(String[] words) {
    Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
    int max = 0;
    Map<String, Integer> cnt = new HashMap<>();
    for (String w : words) {
      cnt.put(w, 1);
    }
    for (String w : words) {
      int best = 0;
      for (int i = 0; i < w.length(); i++) {
        String prev = w.substring(0, i) + w.substring(i + 1);
        best = Math.max(best, cnt.getOrDefault(prev, 0) + 1);
      }
      cnt.put(w, best);
      max = Math.max(max, best);
    }
    return max;
  }
  
  // Backracking with recursion
//   int max = 0;
//   public int longestStrChain(String[] words) {
//     Set<String> set = new TreeSet<>(Arrays.asList(words));
//     int longest = 0;
//     for (String w : words) {
//       if (longest < w.length()) {
//         longest = w.length();
//       }
//     }
//     for (String w : words) {
//       dfs(set, w, longest, 1);      
//     }
//     return max;
//   }
  
//   void dfs(Set<String> words, String word, int longest, int res) {
//     if (max < res) {
//       max = res;
//     }
//     if (word.length() >= longest) {
//       return;
//     }
//     for (int i = 0; i <= word.length(); i++) {
//       String left = word.substring(0, i);
//       String right = i < word.length() ? word.substring(i) : "";
//       for (char c = 'a'; c <= 'z'; c++) {
//         String next = left + c + right;
//         if (words.contains(next)) {
//           dfs(words, next, longest, res + 1);
//         }        
//       }
//     }
//   }
  
}
