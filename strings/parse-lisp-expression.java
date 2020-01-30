/*
Parse Lisp Expression

You are given a string expression representing a Lisp-like expression to return the integer value of.

The syntax for these expressions is given as follows.

An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an 
assigned variable. Expressions always evaluate to a single integer.

(An integer could be positive or negative.)

A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string 
"let", then there are 1 or more pairs of alternating variables and expressions, meaning that the 
first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned 
the value of the expression e2, and so on sequentially; and then the value of this let-expression
is the value of the expression expr.

An add-expression takes the form (add e1 e2) where add is always the string "add", there are always
two expressions e1, e2, and this expression evaluates to the addition of the evaluation of e1 and 
the evaluation of e2.

A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always
two expressions e1, e2, and this expression evaluates to the multiplication of the evaluation of e1 
and the evaluation of e2.

For the purposes of this question, we will use a smaller subset of variable names. A variable starts
with a lowercase letter, then zero or more lowercase letters or digits. Additionally for your 
convenience, the names "add", "let", or "mult" are protected and will never be used as variable names.

Finally, there is the concept of scope. When an expression of a variable name is evaluated, within 
the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for 
the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that 
every expression is legal. Please see the examples for more details on scope.

Evaluation Examples:
Input: (add 1 2)
Output: 3

Input: (mult 3 (add 2 3))
Output: 15

Input: (let x 2 (mult x 5))
Output: 10

Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
Output: 14
Explanation: In the expression (add x y), when checking for the value of the variable x,
we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
Since x = 3 is found first, the value of x is 3.

Input: (let x 3 x 2 x)
Output: 2
Explanation: Assignment in let statements is processed sequentially.

Input: (let x 1 y 2 x (add x y) (add x y))
Output: 5
Explanation: The first (add x y) evaluates as 3, and is assigned to x.
The second (add x y) evaluates as 3+2 = 5.

Input: (let x 2 (add (let x 3 (let x 4 x)) x))
Output: 6
Explanation: Even though (let x 4 x) has a deeper scope, it is outside the context
of the final x in the add-expression.  That final x will equal 2.

Input: (let a1 3 b2 (add a1 1) b2) 
Output 4
Explanation: Variable names can contain digits after the first character.

Note:
The given string expression is well formatted: There are no leading or trailing spaces, there is only 
a single space separating different components of the string, and no space between adjacent parentheses.
The expression is guaranteed to be legal and evaluate to an integer.
The length of expression is at most 2000. (It is also non-empty, as that would not be a legal expression.)
The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.

https://leetcode.com/problems/parse-lisp-expression/
*/

  class Solution {
        // Based on https://leetcode.com/problems/parse-lisp-expression/discuss/113902/A-Clean-Java-Solution
    public int evaluate(String expression) {
      return eval(expression, new HashMap<>());
    }
    
    int eval(String ex, Map<String, Integer> map) {
      char c = ex.charAt(0);
      if (c != '(') {
        if (Character.isDigit(c) || c == '-') {
          return Integer.parseInt(ex);
        }
        return map.get(ex);
      }
      List<String> tokens = null;
      Map<String, Integer> context = new HashMap<>();
      context.putAll(map);
      if (ex.startsWith("(mult")) {
        tokens = parse(ex.substring(6, ex.length() - 1));
      } else {
        tokens = parse(ex.substring(5, ex.length() - 1)); // same for let 
      }      
      if (ex.startsWith("(mult")) {
        return eval(tokens.get(0), context) * eval(tokens.get(1), context);
      } else if (ex.startsWith("(add")) {
        return eval(tokens.get(0), context) + eval(tokens.get(1), context);
      } else { // let
        for (int i = 0; i < tokens.size() - 2; i += 2) {
          context.put(tokens.get(i), eval(tokens.get(i + 1), context));
        }
        return eval(tokens.get(tokens.size() - 1), context);
      }
    }
    
    List<String> parse(String ex) {
      int par = 0;
      ArrayList<String> res = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      for (char c : ex.toCharArray()) {
        if (c == '(') {
          par++;
        }
        if (c == ')') {
          par--;
        }
        if (par == 0 && c == ' ') {
          res.add(sb.toString());
          sb = new StringBuilder();
        } else {
          sb.append(c);
        }
      }
      if (sb.length() > 0) {
        res.add(sb.toString());
      }
      return res;
    }
    
    // Too complicated
//     int ind;
//     public int evaluate(String expression) {
//       ind = 0;
//       return eval(expression, new HashMap<>());
//     }

//     Integer eval(String ex, Map<String, Stack<Integer>> map) {
//       int n = ex.length();
//       Integer res = null;
//       while (ind < n) {
//         char c = ex.charAt(ind);
//         if (c == '(') {
//           ind++;
//           res = eval(ex, map);
//           break;
//         } else if (c == ')') {
//           ind++;
//           break;
//         } else if (Character.isLetter(c)) {
//           String val = parseStr(ex);
//           if ("let".equals(val)) {
//             res = evalLet(ex, map);
//           } else if ("add".equals(val)) {
//             res = evalAdd(ex, map);
//           } else if ("mult".equals(val)) {
//             res = evalMult(ex, map);
//           }
//           ind++;
//           break;
//         }
//         ind++;
//       }
//       return res;
//     }

//     Integer evalVal(String ex, Map<String, Stack<Integer>> map) {
//       char c = ex.charAt(ind);
//       if (c == ' ') {
//         ind++;
//       }
//       c = ex.charAt(ind);
//       if (c == '(') {
//         ind++;
//         return eval(ex, map);
//       } else if (c == ')') {
//         ind++;
//         return null;
//       } else if (Character.isLetter(c)) {
//         String val = parseStr(ex);
//         return (map.get(val).peek());
//       } else {
//         return parseInt(ex);
//       }
//     }

//     Integer evalAdd(String ex, Map<String, Stack<Integer>> map) {
//       Integer val1 = evalVal(ex, map);
//       Integer val2 = evalVal(ex, map);
//       if (val1 == null || val2 == null) {
//         return null;
//       }
//       return val1 + val2;
//     }

//     Integer evalMult(String ex, Map<String, Stack<Integer>> map) {
//       Integer val1 = evalVal(ex, map);
//       Integer val2 = evalVal(ex, map);
//       if (val1 == null || val2 == null) {
//         return null;
//       }
//       return val1 * val2;
//     }

//     Integer evalLet(String ex, Map<String, Stack<Integer>> map) {
//       int n = ex.length();
//       Set<String> seen = new HashSet<>();
//       Integer res = null;
//       while (ind < n) {
//         String var = parseStr(ex);
//         Integer val = evalVal(ex, map);
//         if (val == null) {
//           break;
//         }
//         res = val;
//         Stack<Integer> stack = map.getOrDefault(var, new Stack<>());
//         stack.push(val);
//         map.put(var, stack);
//         seen.add(var);
//         ind++;
//         char c = ex.charAt(ind);
//         if (c == '(') {
//           res = eval(ex, map);
//           break;
//         } else if (c == ')') {
//           break;
//         } else if (Character.isDigit(c) || c == '-') {
//           res = parseInt(ex);
//           break;
//         }
//       }
//       for (String var : seen) {
//         map.get(var).pop();
//       }
//       return res;
//     }

//     String parseStr(String ex) {
//       StringBuilder res = new StringBuilder();
//       if (ex.charAt(ind) == ' ') {
//         ind++;
//       }
//       while (ind < ex.length()) {
//         char c = ex.charAt(ind);
//         if (Character.isLetterOrDigit(c)) {
//           res.append(c);
//           ind++;
//         } else {
//           break;
//         }
//       }
//       return res.toString();
//     }

//     Integer parseInt(String ex) {
//       StringBuilder res = new StringBuilder();
//       if (ex.charAt(ind) == ' ') {
//         ind++;
//       }
//       while (ind < ex.length()) {
//         char c = ex.charAt(ind);
//         if (Character.isDigit(c) || c == '-') {
//           res.append(c);
//           ind++;
//         } else {
//           break;
//         }
//       }
//       return Integer.valueOf(res.toString());
//     }
  }
