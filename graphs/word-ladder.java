/*
Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of 
shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

https://leetcode.com/problems/word-ladder/
*/

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    wordList.add(beginWord);
    //wordList.add(endWord);
    int n = wordList.size();
    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
    for (int i = 0; i < n; i++) {
      map.put(wordList.get(i), i);
    }
    if (!map.containsKey(endWord)) {
      return 0;
    }
    boolean[][] g = buildGraph(wordList, map);
    return bfs(g, map.get(beginWord), map.get(endWord));
  }
  
  int bfs(boolean[][] g, int s, int t) {
    Queue<Integer> queue = new LinkedList<>();
    int n = g.length;
    boolean[] visited = new boolean[n];
    queue.add(s);
    visited[s] = true;
    int steps = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int cur = queue.remove();
        if (cur == t) {
          return steps;
        }
        for (int j = 0; j < n; j++) {
          if (!visited[j] && g[cur][j]) {
            visited[j] = true;
            queue.add(j);
          }
        }        
      }
      steps++;
    }
    return 0;
  }
  
  boolean[][] buildGraph(List<String> wordList, Map<String, Integer> map) {
    int n = wordList.size();
    boolean[][] g = new boolean[n][n];
    
    for (int j = 0; j < n; j++) {
      String w = wordList.get(j);
      char[] a = w.toCharArray();
      for (int i = 0; i < a.length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          if (c == a[i]) {
            continue;
          }
          char t = a[i];
          a[i] = c;
          String s = new String(a);
          if (map.containsKey(s)) {
            g[j][map.get(s)] = true;
          }
          a[i] = t;
        }
      }
    }
    return g;
  }
}
