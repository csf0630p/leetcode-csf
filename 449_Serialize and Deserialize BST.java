Serialize and Deserialize BST

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.
  
  
  You may serialize the following tree:

    3
   / \
  2   5
     / \
    4   6
         \
          8

//as "[3,2,5,null,null,4,6]"
as "[3,2,5,4,6,8][2,3,4,5,6,8]"
      
class Node {
  int value;
  Node left;
  Node right;
  Node(int value) {
    this.value = value;
  }
}
class Solution {
  ArrayList<Integer> preo = new ArrayList<>();
  ArrayList<Integer> ino = new ArrayList<>();
  
  public String serialize (Node root) {
    if (root == null)
      return "[]";
//     Queue<Node> q = new LinkedList<>();
//     ArrayList<Integer> ans = new ArrayList<>();
//     q.add(root);
//     while (!q.isEmpty()) {
//       Node temp = q.poll();
//       Node l = temp.left;
//       Node r = temp.right;
//       if (temp != null)
//         ans.add(temp.value);
//       else {
//         ans.add(null);
        
//       }
      
//       if 
//       Node l = temp.left;
//       Node r = temp.right;
//       q.add(l);
//       q.add(r);    
//     }
    preOrder(root);
    inOrder(root);
    return this.preo.toString() + this.ino.toString();
  }
  
  private void preOrder (Node t) {
    preo.add(t.value);
    if (t.left != null)
      preOrder(t.left);
    if (t.right != null)
      preOrder(t.right);
  }
  
  private void inOrder (Node t) {
    if (t.left != null)
      inOrder(t.left);
    ino.add(t.value);
    if (t.right != null)
      inOrder(t.right);
  }
  
  public Node deserialize (String s) {
    int p = s.indexOf("]");
    String s1 = s.substring(1,p);
    String[] preo = s1.split(",");
    String s2 = s.substring(p + 2, s.length() - 1);
    String[] ino = s2.split(",");
    
    for (int i = 0; i < s.length(); i++) {
    }
  }
}