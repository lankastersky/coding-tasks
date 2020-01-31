/*
Decode String

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets 
is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets
are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits
are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

https://leetcode.com/problems/decode-string/
*/

  class Solution {
    int ind;

    public String decodeString(String s) {
      return decode(s);
    }

    public String decode(String s) {
      int n = s.length();
      int k = 0;
      StringBuilder sb = new StringBuilder();
      while (ind < n) {
        char c = s.charAt(ind++);
        if (Character.isDigit(c)) {
          k = k * 10 + (c - '0');
        } else if (c == '[') {
          String val = decode(s);
          for (int i = 0; i < k; i++) {
            sb.append(val);
          }
          k = 0;
        } else if (c == ']') {
          break;
        } else {
          sb.append(c);
        }
      }
      return sb.toString();
    }

    // Too complicated
//     class Rep {
//       int k;
//       String s;
//       Rep(int k, String s) {
//         this.k = k;
//         this.s = s;
//       }
//     }

//     public String decodeString(String s) {
//       int brInd = s.indexOf('[');
//       if (brInd == -1) {
//         return s;
//       }
//       List<Rep> reps = parse(s);
//       StringBuilder sb = new StringBuilder();
//       for (Rep rep : reps) {
//         String str = decodeString(rep.s);
//         for (int i = 0; i < rep.k; i++) {
//           sb.append(str);
//         }
//       }
//       return sb.toString();
//     }

//     List<Rep> parse(String s) {
//       ArrayList<Rep> res = new ArrayList<>();
//       StringBuilder sb = new StringBuilder();
//       int par = 0;
//       int k = 0;
//       int n = s.length();
//       for (int i = 0; i < n; i++) {
//         char c = s.charAt(i);
//         if (c == '[') {
//           par++;
//           if (par == 1) {
//             k = Integer.valueOf(sb.toString());
//             sb.setLength(0);
//           } else {
//             sb.append(c);
//           }
//         } else if (c == ']') {
//           par--;
//           if (par == 0) {
//             res.add(new Rep(k, sb.toString()));
//             sb.setLength(0);
//           } else {
//             sb.append(c);
//           }
//         } else {
//           sb.append(c);
//           if (par == 0 && Character.isLetter(c) && i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
//             res.add(new Rep(1, sb.toString()));
//             sb.setLength(0);
//           }
//         }
//       }
//       if (sb.length() > 0) {
//         res.add(new Rep(1, sb.toString()));
//       }
//       return res;
//     }
  }
