/*
Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence 
of bits so that it can be stored in a file or memory buffer, or transmitted across a 
network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on 
how your serialization/deserialization algorithm should work. You just need to ensure that 
a binary tree can be serialized to a string and this string can be deserialized to the 
original tree structure.

Example: 
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with 
different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and 
deserialize algorithms should be stateless.

https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
  
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }
  
   // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] str = data.split(" ");
    ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
    return deserialize(list);
  }
  
  void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append("null");
      sb.append(" ");
      return;
    }
    sb.append(root.val);
    sb.append(" ");
    serialize(root.left, sb);
    serialize(root.right, sb);
  }
  
  TreeNode deserialize(ArrayList<String> list) {
    if (list.isEmpty()) {
      return null;
    }
    String s = list.remove(0);
    if (s.equals("null")) {
      return null;
    }
    TreeNode node = new TreeNode(Integer.valueOf(s));
    node.left = deserialize(list);
    node.right = deserialize(list);
    return node;
  }
    // BFS
//   // Encodes a tree to a single string.
//   public String serialize(TreeNode root) {
//     StringBuilder sb = new StringBuilder();
//     Queue<TreeNode> queue = new LinkedList<>();
//     queue.add(root);
//     while (!queue.isEmpty()) {
//       TreeNode cur = queue.remove();
//       if (cur == null) {
//         sb.append((Object) null);
//         sb.append(' ');
//         continue;
//       }
//       sb.append(cur.val);
//       sb.append(' ');
//       queue.add(cur.left);
//       queue.add(cur.right);
//     }
//     return sb.toString().trim();
//   }

//   // Decodes your encoded data to tree.
//   public TreeNode deserialize(String data) {
//     String[] a = data.split(" ");
//     int size = 1;
//     int i = 0;
//     int n = a.length;
//     Deque<TreeNode> queue = new LinkedList<>();
//     boolean left = true;
//     TreeNode root = null;
//     while (i < n) {
//       for (int j = 0; j < size && i < n; j++, i++) {
//         String s = a[i];
//         TreeNode node = s.equals("null") ? null : new TreeNode(Integer.valueOf(s));
//         if (root == null) {
//           root = node;
//           queue.add(node);
//           continue;
//         }
//         if (left) {
//           TreeNode parent = queue.getFirst();
//           parent.left = node;
//         } else {
//           TreeNode parent = queue.removeFirst();
//           parent.right = node;          
//         }
//         if (node != null) {
//           queue.addLast(node);          
//         }
//         left = !left;
//       }
//       size <<= 1;
//     }
//     return root;
//   }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
