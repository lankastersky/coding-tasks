/*
Evaluate Division

Equations are given in the format A / B = k, where A and B are variables represented as strings, 
and k is a real number (floating point number). Given some queries, return the answers. If the answer
does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, 
vector<pair<string, string>> queries , where equations.size() == values.size(), and the values 
are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 
The input is always valid. You may assume that evaluating the queries will result in no division 
by zero and there is no contradiction.

https://leetcode.com/problems/evaluate-division/
*/

class Solution {
  public double[] calcEquation(
    List<List<String>> equations, double[] values, List<List<String>> queries) {
    
    int n = queries.size();
    double[] res = new double[n];

    Map<String, Map<String, Double>> graph = buildGraph(equations, values);
    Arrays.fill(res, -1);
    for (int i = 0; i < n; i++) {
      List<String> query = queries.get(i);
      String a = query.get(0);
      String b = query.get(1);
      if (a.equals(b)) {
        res[i] = 1;
      }
      Double val = dfs(a, b, graph, new HashSet<>());
      if (val != null) {
        res[i] = val;
        continue;
      }
    }
    return res;
  }
  
  Double dfs(
    String source, String dest, 
    Map<String, Map<String, Double>> graph, 
    Set<String> seen) {

    if (!graph.containsKey(source)) {
      return -1.;
    }
    if (graph.get(source).containsKey(dest)) {
      return (graph.get(source).get(dest));
    }
    if (seen.contains(source)) {
      return null;
    }
    
    seen.add(source);
    
    Map<String, Double> neib = graph.get(source);
    for (String key : neib.keySet()) {
      Double val = dfs(key, dest, graph, seen);
      if (val != null) {
        return val * neib.get(key);
      }
    }
    seen.remove(source);
    return null;
  }
  
  Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
    Map<String, Map<String, Double>> res = new HashMap<>();
    int m = equations.size();
    for (int i = 0; i < m; i++) {
      List<String> eq = equations.get(i);
      String a = eq.get(0);
      String b = eq.get(1);
      double val = values[i];
      Map<String, Double> aEdges = res.getOrDefault(a, new HashMap<>());
      aEdges.put(b, val);
      res.put(a, aEdges);
      Map<String, Double> bEdges = res.getOrDefault(b, new HashMap<>());
      bEdges.put(a, 1. / val);
      res.put(b, bEdges);
    }
    return res;
  }
}
