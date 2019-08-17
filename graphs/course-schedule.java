/*
Course Schedule

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed 
as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph
is represented.

You may assume that there are no duplicate edges in the input prerequisites.

https://leetcode.com/problems/course-schedule/
*/

// Based on Kahn's algorithm (https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm)
// O(|V| + |E|)
class Solution {
  //enum State { BLANK, PROCESS, VISITED }
  class Course {
    int descendants;
    int num;
    //State state = BLANK;
    Course(int n) {
      num = n;
    }
  }

  class Graph {
    Map<Integer, Set<Integer>> nodes;
    ArrayList<Course> courses;
    Graph(int size) {
      nodes = new HashMap<>();
      courses = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        courses.add(new Course(i));
        nodes.put(i, new HashSet<>());
      }
    }
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Graph g = build(numCourses, prerequisites);
    return traverse(g, numCourses);
  }
  
  Graph build(int numCourses, int[][] edges) {
    Graph g = new Graph(numCourses);
    for (int[] edge : edges) {
      g.nodes.get(edge[0]).add(edge[1]);
      g.courses.get(edge[1]).descendants++;
    }
    return g;
  }
  
  boolean traverse(Graph g, int numCourses) {
    ArrayList<Course> roots = getRoots(g);
    Course[] res = new Course[numCourses];
    int last = 0;
    for (Course c : roots) {
      res[last++] = c;
    }
    int i = 0;
    while (last < numCourses) {
      if (res[i] == null) {
        return false;
      }
      Course c = res[i++];
      for (int childnum : g.nodes.get(c.num)) {
        Course child = g.courses.get(childnum);
        child.descendants--;
        if (child.descendants == 0) {
          res[last++] = child;
        }
      }
    }
    return true;
  }
  
  ArrayList<Course> getRoots(Graph g) {
    ArrayList<Course> res = new ArrayList<>();
    for (Course c: g.courses) {
      if (c.descendants == 0) {
        res.add(c);
      }
    }
    return res;
  }
}
