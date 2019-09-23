/*
	To implement a function that calculate the lowest common ancestor in a graph,
	that may be structured as a binary tree. 
*/
class Node{
	int data;
	Node left, right;
	
	Node(int num){
		data = num;
		left = null;
		right = null;
	}
}

public class LCA {
	
	Node root;
	
	Node lca(Node node, int a, int b) {
		
		if(node == null) {
			return null;
		}
		
		//a<root.data & b<root.data, which means the lca is in the left branch
		if(a<node.data && b<node.data) {
			return lca(node.left, a, b);
		}
		
		//a>root.data & b>root.data, which means the lca is in the right branch
		if(a>node.data && b>node.data) {
			return lca(node.right, a, b);
		}
		
		//if a<node.data && b>node.data, then the current node is the lca
		//if a or b is equal to node.data, then the current node is the lca
		return node;
	}

}
