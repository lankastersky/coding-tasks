/*
Add and Search Word - Data structure design

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only 
letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.

https://leetcode.com/problems/add-and-search-word-data-structure-design/
*/

class WordDictionary {

  class Node {
    char val;
    boolean term;
    ArrayList<Node> children;
    Node(char val) {
      this.val = val;
      children = new ArrayList<>();
    }
  }
  
  class Trie {
    Node root;
    Trie() {
      root = new Node((char) 0);
    }
    
    void add(String word) {
      Node cur = root;
      int ind = 0;
      while (ind < word.length()) {
        boolean found = false;
        for (Node child : cur.children) {
          if (child.val == word.charAt(ind)) {
            cur = child;
            found = true;
            break;
          }
        }
        if (!found) {
          Node child = new Node(word.charAt(ind));
          cur.children.add(child);
          cur = child;
        }
        ind++;
      }
      cur.term = true;
    }

    boolean search(String word, int ind, Node root) {
      if (ind == word.length()) {
        return root.term;
      }
      char ch = word.charAt(ind);
      if (ch != '.') {
        for (Node child : root.children) {
          if (child.val == ch) {
            return search(word, ind + 1, child);
          }          
        }
      } else {
        for (char c = 'a'; c <= 'z'; c++) {
          for (Node child : root.children) {
            if (child.val == c) {
              if (search(word, ind + 1, child)) {
                return true;
              }
            }
          }
        }
      }
      return false;
    } 
  }
  
  Trie trie;
  
  /** Initialize your data structure here. */
  public WordDictionary() {
    trie = new Trie();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    trie.add(word);
  }

  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
    return trie.search(word, 0, trie.root);
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
