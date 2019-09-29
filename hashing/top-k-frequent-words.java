/*
Top K Frequent Words

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have
the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
    
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
    
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

https://leetcode.com/problems/top-k-frequent-words/
*/

import java.util.*;

  class Solution {
    public List<String> topKFrequent(String[] words, int k) {
      Map<String, Integer> map = new HashMap<>();
      for (int i = 0; i < words.length; i++) {
        String w = words[i];
        int f = map.getOrDefault(w, 0);
        map.put(w, f + 1);
      }
      TreeSet<String> set = new TreeSet<>((w1, w2) -> {
        int f1 = map.get(w1);
        int f2 = map.get(w2);
        if (f1 == f2) {
          return w1.compareTo(w2);
        }
        return -Integer.compare(f1, f2);
      });
      List<String> res = new ArrayList<>();
      for (String w : map.keySet()) {
        set.add(w);
      }
      Iterator<String> iter = set.iterator();
      while (iter.hasNext() && k > 0) {
        res.add(iter.next());
        k--;
      }
      //Collections.reverse(res);
      return res;
    }
  }
// Using heap
/*
class Solution {
  class Pair {
    String word;
    int freq;
    Pair(String w, int f) {
      word = w;
      freq = f;
    }
  }
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> map = new HashMap<>();
    int n = words.length;
    for (int i = 0; i < n; i++) {
      String w = words[i];
      int freq = map.getOrDefault(w, 0);
      map.put(w, freq + 1);
    }
    PriorityQueue<Pair> minHeap = new PriorityQueue<>(n, (p1, p2) -> {
      if (p1.freq == p2.freq) {
        return -p1.word.compareTo(p2.word);
      }
      return Integer.compare(p1.freq, p2.freq);
    });
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      Pair p = new Pair(e.getKey(), e.getValue());
      minHeap.add(p);
      if (minHeap.size() > k) {
        minHeap.remove();
      }
    }
    List<String> res = new ArrayList<>();
    while (k > 0 && !minHeap.isEmpty()) {
      res.add(minHeap.poll().word);      
    }
    Collections.reverse(res);
    return res;
  }
}
*/
