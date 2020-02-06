/*
Sequence Reconstruction

Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction 
means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence 
so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence
that can be reconstructed from seqs and it is the org sequence.

Example 1:
Input:
org: [1,2,3], seqs: [[1,2],[1,3]]
Output:
false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid 
sequence that can be reconstructed.

Example 2:
Input:
org: [1,2,3], seqs: [[1,2]]
Output:
false
Explanation:
The reconstructed sequence can only be [1,2].

Example 3:
Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
Output:
true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:
Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
Output:
true

https://leetcode.com/problems/sequence-reconstruction/
*/

  class Solution {
    
    // Based on Kahn's algorithm https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm
    class Graph {
      Map<Integer, Set<Integer>> outdegrees = new HashMap<>();
      Map<Integer, Integer> indegrees = new HashMap<>();
    }
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
      Graph g = buildGraph(seqs);
      int n = org.length;

      if (g.indegrees.size() != n) {
        return false;
      }
      Queue<Integer> roots = new LinkedList<>();
      for (int v : g.indegrees.keySet()) {
        if (g.indegrees.get(v) == 0) {
          roots.add(v);
        }
      }
      int ind = 0;
      while (roots.size() == 1) { // check for uniqueness of the root
        int u = roots.remove();
        if (org[ind] != u) { // check if org contains our sorted vertexes
          return false;
        }
        ind++;
        if (g.outdegrees.containsKey(u)) { // check if outdegrees has this vertex
          for (int v : g.outdegrees.get(u)) {
            g.indegrees.put(v, g.indegrees.get(v) - 1);
            if (g.indegrees.get(v) == 0) { // only affected vertexes can be potential new roots
              roots.add(v);
            }
          }
        }
      }
      return ind == n;
    }

    Graph buildGraph(List<List<Integer>> seqs) {
      Graph g = new Graph();
      for (List<Integer> seq : seqs) {
        int n = seq.size();
        if (n == 0) {
          continue;
        }
        int v = seq.get(0);
        if (!g.indegrees.containsKey(v)) {
          g.indegrees.put(v, 0);
        }
        for (int i = 1; i < n; i++) {
          int prev = seq.get(i - 1);
          int next = seq.get(i);
          Set<Integer> nb = g.outdegrees.getOrDefault(prev, new HashSet<>());
          if (nb.add(next)) { // avoid incrementing many times when seqs = [[1,2],[1,2]]
            g.outdegrees.put(prev,nb);
            g.indegrees.put(next, g.indegrees.getOrDefault(next, 0) + 1);
          }
        }
      }
      return g;
    }
  }
