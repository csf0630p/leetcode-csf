/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
// dfs
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Integer, Node> dict = new HashMap<>();
        return dfs(node, dict);
    }
    
    private Node dfs(Node node, Map<Integer, Node> dict) {
        if (dict.containsKey(node.val)) return dict.get(node.val);
        Node newNode = new Node(node.val, new ArrayList<Node>());
        dict.put(node.val, newNode);
        for (Node e : node.neighbors) {
            newNode.neighbors.add(dfs(e, dict));
        }
        return newNode;
    }
}