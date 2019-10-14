import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class LCA2<V> {

    private Map<V,List<V>> map = new HashMap<V,List<V>>();

    //Add a vertex to the graph.  Nothing happens if vertex is already in graph.
    public void add(V vertex) {
        if (map.containsKey(vertex)) return;
        map.put(vertex, new ArrayList<V>());
    }

    //check if the vertex is already in graph
    public boolean contains (V vertex) {
        return map.containsKey(vertex);
    }

    //Add an edge to the graph
    public void add(V from, V to) {
        if(from != to) {
        	this.add(from); 
        	this.add(to);
        	map.get(from).add(to);
        }
    }

    //Report (as a Map) the in-degree of each vertex.
    public Map<V,Integer> inDegree () {
        Map<V,Integer> result = new HashMap<V,Integer>();
        for (V v: map.keySet()) result.put(v, 0);       // All in-degrees are 0
        for (V from: map.keySet()) {
            for (V to: map.get(from)) {
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
            for (V neighbor: map.get(v)) {
                degree.put(neighbor, degree.get(neighbor) - 1);
                if (degree.get(neighbor) == 0) stack.push(neighbor);
            }
        }
        
        // Check that we have used the entire graph (if not, there was a cycle)
        if (result.size() != map.size()) return null;
        return result;
    }
    
    

}

