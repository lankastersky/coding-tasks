/*
Word Abbreviation

Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations
for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the 
last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix
is used instead of only the first character until making the map from word to abbreviation become 
unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.

Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]

Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.

https://leetcode.com/problems/word-abbreviation/
*/

  class Solution {

    class Pair {
      String w;
      int ind;
      Pair(String w, int i) {
        this.w = w;
        ind = i;
      }
    }

    class Trie {

      class Node {
        Node[] nodes = new Node[A];
        int cnt;
      }

      int A = 26;
      Node root = new Node();

      void add(String s) {
        Node cur = root;
        for (char c : s.toCharArray()) {
          if (cur.nodes[c - 'a'] == null) {
            cur.nodes[c - 'a'] = new Node();
          }
          cur.nodes[c - 'a'].cnt++;
          cur = cur.nodes[c - 'a'];
        }
      }

      int getPref(String s) {
        Node cur = root;
        int pref = 0;
        for (char c : s.toCharArray()) {
          if (cur.nodes[c - 'a'].cnt == 1) {
            return pref;
          }
          cur = cur.nodes[c - 'a'];
          pref++;
        }
        return -1;
      }
    }

    // Based on https://leetcode.com/articles/word-abbreviation/
    public List<String> wordsAbbreviation(List<String> dict) {
      Map<String, ArrayList<Pair>> map = new HashMap<>();
      int n = dict.size();
      for (int i = 0; i < n; i++) {
        String s = dict.get(i);
        String ab = abbr(s, 0);
        ArrayList<Pair> pairs = map.getOrDefault(ab, new ArrayList<>());
        pairs.add(new Pair(s, i));
        map.put(ab, pairs);
      }
      ArrayList<String> res = new ArrayList<>(Collections.nCopies(n, ""));
      for (ArrayList<Pair> pairs : map.values()) {
        Trie trie = new Trie();
        for (Pair pair : pairs) {
          trie.add(pair.w);
        }
        for (Pair pair : pairs) {
          int pref = trie.getPref(pair.w);
          res.set(pair.ind, abbr(pair.w, pref));
        }
      }
      return res;
    }

    String abbr(String s, int pref) {
      int n = s.length();
      if (n - pref <= 3) {
        return s;
      }
      return s.substring(0, pref + 1) + (n - pref - 2) + s.substring(n - 1);
    }
  }
