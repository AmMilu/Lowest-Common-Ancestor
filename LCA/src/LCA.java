public class LCA {
	
	class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    Node root;
    
    LCA(){
    	root = null;
    }

    // This method mainly calls insertRec() 
    void insert(int value) { 
       root = insertRec(root, value); 
    } 
      
    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int value) { 
  
        /* If the tree is empty, return a new node */
        if (root == null) { 
            root = new Node(value); 
            return root; 
        } 
  
        /* Otherwise, search the tree to find the place to insert*/
        if (value < root.value) 
            root.left = insertRec(root.left, value); 
        else if (value > root.value) 
            root.right = insertRec(root.right, value); 
  
        /* return the (unchanged) node pointer */
        return root; 
    } 

    public boolean search(Node root, int value) 
    { 
        // Base Cases: root is null or value is present at root 
        if(root == null) return false;
        if(root.value == value) return true;
      
        // value is greater than root's value 
        if (root.value > value) 
            return search(root.left, value); 
      
        // value is less than root's value 
        return search(root.right, value); 
    } 
    
    //The code below is what is required in the question.
    //The code above are all to support.
    
    public int lca(Node node, int a, int b) {
    	
    	//if there is no node with value a or b, return 0, representing the lca is not found. the person is not in the family.
    	if(!search(node,a) || !search(node,b))   return 0;
    	
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
		return node.value;
	}
}