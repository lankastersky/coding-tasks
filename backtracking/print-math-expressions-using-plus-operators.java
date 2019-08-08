/*
you're given an input string of digits (1-9) like "123". Keeping those digits in that same order
and using every digit once, *PRINT* all possible math expressions using those digits
and zero or more "+" plus operators.

Examples:
"1" -> 1
"12" -> 1+2, 12
"123" -> 123, 1+23, 12+3, 1+2+3

2-nd part:

print out the result of each expression. E.g.
"12" -> 1+2=3, 12=12

interviewing.io
*/

    public static void printStrings(String s) {
      if (s == null || s.isEmpty()) {
        return;
      }
      StringBuilder sb = new StringBuilder();
      rec(s, 0, sb, 0, 0);
    }

    static void rec(String s, int i, StringBuilder sb, int accum, int sum) {
      int n = s.length();
      if (i == n) {
        sum += accum;
        print(sb.toString() + " = " + sum);
        return;
      }
      sb.append(s.charAt(i));
      accum = accum * 10 + (s.charAt(i) - '0');
      if (i < n - 1) {
        sb.append('+');
        rec(s, i + 1, sb, 0, sum + accum);
        sb.deleteCharAt(sb.length() - 1);
      }
      rec(s, i + 1, sb, accum, sum);
      sb.deleteCharAt(sb.length() - 1);
    }

    static void print(String s) {
      System.out.println(s);
    }
