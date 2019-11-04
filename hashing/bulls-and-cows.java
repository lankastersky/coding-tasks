/*
Bulls and Cows

You are playing the following Bulls and Cows game with your friend: You write down
a number and ask your friend to guess what the number is. Each time your friend makes
a guess, you provide a hint that indicates how many digits in said guess match your 
secret number exactly in both digit and position (called "bulls") and how many digits 
match the secret number but locate in the wrong position (called "cows"). Your friend 
will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

Note: You may assume that the secret number and your friend's guess only contain digits,
and their lengths are always equal.

https://leetcode.com/problems/bulls-and-cows/
*/

class Solution {
  public String getHint(String secret, String guess) {
    int n = secret.length();
    int bulls = 0;
    int cows = 0;
    int[] scnt = new int[10];
    int[] gcnt = new int[10];
    for (int i = 0; i < n; i++) {
      char s = secret.charAt(i);
      char g = guess.charAt(i);
      if (s == g) {
        bulls++;
      } else {
        scnt[s - '0']++;
        gcnt[g - '0']++;
      }
    }
    for (int i = 0; i < 10; i++) {
      cows += Math.min(scnt[i], gcnt[i]);
    }
    return bulls + "A" + cows + "B";
  }
     // O(n^2)
//   public String getHint(String secret, String guess) {
//     int n = secret.length();
//     boolean[] bulls = new boolean[n];
//     boolean[] cows = new boolean[n];
//     int b = 0;
//     int c = 0;
//     for (int i = 0; i < n; i++) {
//       char g = guess.charAt(i);
//       if (secret.charAt(i) == g) {
//         b++;
//         bulls[i] = true;
//       }
//     }

//     for (int i = 0; i < n; i++) {
//       char g = guess.charAt(i);
//       if (bulls[i]) {
//         continue;
//       }
//       for (int j = 0; j < n; j++) {
//         if (j == i) {
//           continue;
//         }
//         if (bulls[j] || cows[j]) {
//           continue;
//         }
//         if (secret.charAt(j) == g) {
//           c++;
//           cows[j] = true;
//           break;
//         }
//       }
//     }
//     String res = String.format("%dA%dB", b, c);
//     return res;
//   }
}
