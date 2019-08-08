/*
Implement grep functions which searches for positions of the substring in the given string:

function int[]/list grep(string haystack, string needle)

Example:
haystack = "aaabcdddbbddddabcdefghi"
needle = "abc"
Output: [2,14]

2-nd part: do the same with boyer-moore algorithm

interviewing.io
*/

public class Solution {
    static int BASE = 256;

    public static void main(String[] args) {
      ArrayList<Integer> res;

      // res = grep("a", "a");
      // System.out.println(res.toString());

      // res = grep("11", "1");
      // System.out.println(res.toString());

       res = grep("111", "11");
       System.out.println(res.toString());

      res = grep("aaabcdddbbddddabcdefghi", "abc");
      System.out.println(res.toString());
    }

    static ArrayList<Integer> grep(String s, String p) {
      if (s.length() < p.length()) {
        return new ArrayList<>();
      }

      ArrayList<Integer> result = new ArrayList<>();
      int hash = 0;
      int phash = 0;
      int psize = p.length();
      for (int i = 0; i < psize; i++) {
        char c1 = s.charAt(i);
        char c2 = p.charAt(i);
        hash = hash * BASE + c1;
        phash = phash * BASE + c2;
      }

      int size = s.length();
      int power = (int) Math.pow(BASE, psize - 1);

      for (int i = 0; i < size - psize; i++) {
        if (hash == phash) {
//          if (s.substring(i, i + psize).equals(p)) {
          result.add(i);
//          }
        }
        char c1 = s.charAt(i + psize);
        hash = (hash - s.charAt(i) * power);
        hash = hash * BASE;
        hash = hash + c1;
      }

      if (hash == phash) {
        result.add(size - psize);
      }
      return result;
    }

    static void print(String s) {
      //System.out.println(s);
    }
}
