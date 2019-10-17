import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class LCA2<V> {

    private Map<V,List<V>> map = new HashMap<V,List<V>>();

    public Map<V,List<V>> getMap() {
		return map;
	}

	//Add a vertex to the graph.  Nothing happens if vertex is already in graph.
    public void add(V vertex) {
        if (getMap().containsKey(vertex)) return;
        getMap().put(vertex, new ArrayList<V>());
    }

    //check if the vertex is already in graph
    public boolean contains (V vertex) {
        return getMap().containsKey(vertex);
    }

    //Add an edge to the graph
    public void add(V from, V to) {
        if(from != to) {
        	this.add(from); 
        	this.add(to);
        	getMap().get(from).add(to);
        }
    }

    //Report (as a Map) the in-degree of each vertex.
    public Map<V,Integer> inDegree () {
        Map<V,Integer> result = new HashMap<V,Integer>();
        for (V v: getMap().keySet()) result.put(v, 0);       // All in-degrees are 0
        for (V from: getMap().keySet()) {
            for (V to: getMap().get(from)) {
                result.put(to, result.get(to) + 1);           // Increment in-degree
            }
        }
        return result;
    }

    //Report (as a List) the topological sort of the vertices; null for no such sort.
    public List<V> topSort () {
        Map<V, Integer> degree = inDegree();
        
        //Push all the 0 degree vertices to the stack
        Stack<V> stack = new Stack<V>();       
        for (V v: degree.keySet()) {
            if (degree.get(v) == 0) stack.push(v);
        }
        
        // Determine the topological order
        List<V> result = new ArrayList<V>();
        while (!stack.isEmpty()) {
            V v = stack.pop();                  
            result.add(v);                   // put 0 degree vertics in topol order
            //deal with other vertices whose degree are not 0
            for (V neighbor: getMap().get(v)) {
                degree.put(neighbor, degree.get(neighbor) - 1);
                if (degree.get(neighbor) == 0) stack.push(neighbor);
            }
        }
        
        // Check that we have used the entire graph (if not, there was a cycle)
        if (result.size() != getMap().size()) return null;
        return result;
    }
    
    //check if the graph is directed acylic graph, return false if there is a cycle
    public boolean isDag () {
        return topSort() != null;
    }
    
    //given a vertex and return the map(vertex, depth) of the given vertex's parent
    public Map parents(V a) {
    	Map<V, Integer> parent = new HashMap<V, Integer>();
    	Map<V, Integer> degree = inDegree();
    	int depth = 0;
    	
    	for(V v: getMap().keySet()) {
    		//find the root, a root is a vertex whose indegree is 0
    		if(degree.get(v) == 0) {
    			parent.put(v, 0);
    			findParents(v, parent, a, depth);
    		}
    	}
    	return parent;
    }
    
    private V findParents(V root, Map<V, Integer> parent, V a, int depth) {
    	V v = null;
    	if(root == null) return null;
    	if(root.equals(a)) {
    		parent.put(root, depth);
    		return root;
    	}
    	List<V> child = new ArrayList<V>();
    	for(int i = 0; i<getMap().get(root).size(); i++) {
    		child.add(null);
    	}
    	for(int i = 0; i<getMap().get(root).size();i++) {
    		child.set(i, findParents(getMap().get(root).get(i),parent,a,depth+1));
    	}
    	for(int i=0; i<getMap().get(root).size(); i++) {
    		if(child.get(i) != null) {
    			//if(!parent.containsKey(root)) {
    			parent.put(root, depth);
    			return findParents(getMap().get(root).get(i),parent,a,depth+1);
    			//}
    		}
    	}
    	return v;
    }
    
    //find the lca of two vertices
    public List<V> lca(V a, V b) {
    	List<V> lca = new ArrayList<V>();
    	int depth = 0;
    	Map<V, Integer> parentA = parents(a);
    	Map<V, Integer> parentB = parents(b);
    	
    	if(!contains(a)||!contains(b)) {
    		return null;
    	}
    	
    	//compare the parents of the two nodes
    	for(V va: parentA.keySet()) {
    		for(V vb: parentB.keySet()) {
    			
    			//if there are same nodes in both maps, choose the deepest one and add to the lca list
    			//lca might be more than one
    			if(va.equals(vb) && depth <= parentA.get(va)) {
    				if(depth < parentA.get(va)) {
    					lca.clear();
    				}
    				lca.add(va);
    				depth = parentA.get(va);
    			}
    		}
    	}
    	return lca;
    }

}

