package edu.harshil.solutions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of
 * its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index
 * (1-indexed). For example, the first node with val == 1, the second node
 * with val == 2, and so on. The graph is represented in the test case using
 * an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a
 * finite graph. Each list describes the set of neighbors of a node in the
 * graph.
 *
 * The given node will always be the first node with val = 1. You must return
 * the copy of the given node as a reference to the cloned graph.
 *
 */
public class CloneGraph {


    /**
     * Runtime 26 ms Beats 65.94% O(Vertex+Edges)
     * Memory 42.6 MB Beats 51.61% O(Vertex)
     */
    public Node cloneGraph(Node node) {
        if(node==null)
            return null;
        if(node.neighbors.size()==0)
            return new Node(1, new ArrayList<Node>());


        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<Node>();
        Node output = new Node(node.val);
        q.add(node);
        map.put(node, output);
        while(!q.isEmpty()){
            Node temp = q.remove();
            for(Node neighb: temp.neighbors){
                if(!map.containsKey(neighb)){
                    map.put(neighb, new Node(neighb.val));
                    q.add(neighb);
                }
                map.get(temp).neighbors.add(map.get(neighb));
            }
        }
        return output;
    }

    /**
     *
     * Runtime 25 ms Beats 98.28% O(Vertex+Edges)
     * Memory 42.6 MB Beats 77.20% O(Vertex)
     */
    public Node cloneGraphDFS(Node node) {
        if(node==null)
            return null;
        return dfs(node, new HashMap<Node, Node>());
    }

    public Node dfs(Node node, HashMap<Node, Node> map){
        if(node == null)
            return null;

        if(map.containsKey(node))
            return map.get(node);

        else{
            Node out = new Node(node.val);
            map.put(node, out);
            for(Node neighb: node.neighbors){
                Node clone = dfs(neighb, map);
                out.neighbors.add(clone);
            }
            return out;
        }
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
