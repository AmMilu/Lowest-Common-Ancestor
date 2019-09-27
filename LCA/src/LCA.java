public class LCA {

    Node root;

    public Node getRoot() {
		return root;
	}

	public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {

        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        boolean result = true;
    	if (current == null) {
            result = false;
        }
        if (value == current.value) {
            result = true;
        }
        if(value < current.value) {
        	result = containsNodeRecursive(current.left,value);
        }
        if(value > current.value) {
        	result = containsNodeRecursive(current.right, value);
        }
        return result;
    }
    
    //The code below is what is required in the question.
    //The code above are all to support.
    
    Node lca(Node node, int a, int b) {
		
		if(node == null) {
			return null;
		}
		
		//a<root.data & b<root.data, which means the lca is in the left branch
		if(a<node.value && b<node.value) {
			return lca(node.left, a, b);
		}
		
		//a>root.data & b>root.data, which means the lca is in the right branch
		if(a>node.value && b>node.value) {
			return lca(node.right, a, b);
		}
		
		//if a<node.data && b>node.data, then the current node is the lca
		//if a or b is equal to node.data, then the current node is the lca
		return node;
	}

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
}