/*
Basic Calculator III

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and 
closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in
the range of [-2147483648, 2147483647].

Some examples:
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

https://leetcode.com/problems/basic-calculator-iii/
*/

  class Solution {
    int pos;
    public int calculate(String s) {
      pos = 0;
      return calc(s);
    }

    public int calc(String s) {
      int n = s.length();
      int res = 0;
      int d = 0;
      Stack<Integer> vals = new Stack<>();
      Stack<Character> ops = new Stack<>();
      while (pos < n) {
        char c = s.charAt(pos);
        if (c == ' ') {
          // do nothing
        } else if (Character.isDigit(c)) {
          d = d * 10 + (c - '0');
        } else if (c == '(') {
          pos++;
          d = calc(s);
        } else if (c == ')') {
          //pos++;
          break;
        } else { // op
          vals.push(d);
          d = 0;
          while (!ops.isEmpty() && precedence(ops.peek(), c)) {
            res = eval(vals, ops);
            vals.push(res);
          }
          ops.push(c);
        }
        pos++;
      }
      vals.push(d);
      while (!ops.isEmpty()) {
        res = eval(vals, ops);
        vals.push(res);
      }
      return vals.pop();
    }
    
    boolean precedence(char op1, char op2) {
      if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
        return false;
      }
      return true;
    }

    int eval(Stack<Integer> vals, Stack<Character> ops) {
      // if (ops.isEmpty()) {
      //   return vals.pop();
      // }
      int d2 = vals.pop();
      int d1 = vals.pop();
      char op = ops.pop();
      if (op == '+') {
        return d1 + d2;
      } else if (op == '-') {
        return d1 - d2;
      } else if (op == '*') {
        return d1 * d2;
      } else { // '/'
        return d1 / d2;
      }
    }
  }
