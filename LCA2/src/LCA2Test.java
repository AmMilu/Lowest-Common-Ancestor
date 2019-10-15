
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

public class LCA2Test {
	
	/*         1
	 *        / \
	 *       v   v
	 *      2     7---->12   (7->12,13)
	 *     /|\    |\----^
	 *    v v v   v/---v
	 *   11 3 4   8--->13    (8->12,13)
	 *     /  ^\  |   
	 *    v   | | |  
	 *    5  /  | | 
	 *   /| |   \ |
	 *  v v/     vv
	 * 10 6------>9
	 * 
	 */
	
	LCA2 testGraph = new LCA2();
	
	@Before
	public void setup() {
		testGraph.add(1);
		testGraph.add(1,2);
		testGraph.add(2,3);
		testGraph.add(2,4);
		testGraph.add(2,11);
		testGraph.add(3,5);
		testGraph.add(5,10);
		testGraph.add(5,6);
		testGraph.add(6,4);
		testGraph.add(4,9);
		testGraph.add(6,9);
		testGraph.add(1,7);
		testGraph.add(7,8);
		testGraph.add(8,9);
		testGraph.add(7,12);
		testGraph.add(8,12);
		testGraph.add(7,13);
		testGraph.add(8,13);
	}
	
	@Test
	public void testContains() {
		//to test if the vertex 9 is in the graph
		assertTrue(testGraph.contains(9));
		//to test if the vertex 12 is in the graph
	    assertTrue(testGraph.contains(12));
		//to test if the vertex 1 is in the graph
		assertTrue(testGraph.contains(1));
		//to test if the vertex 8 is in the graph
		assertTrue(testGraph.contains(8));
		
		//to test if the vertex 42 is in the graph
		assertFalse(testGraph.contains(42));
		//to test if the vertex 0 is in the graph
		assertFalse(testGraph.contains(0));
		//to test if the vertex -3 is in the graph
		assertFalse(testGraph.contains(-3));
	}
	
	@Test
	public void testInDegree() {
		//create expected map
		Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
		expected.put(1, 0);
		expected.put(2, 1);
		expected.put(3, 1);
		expected.put(4, 2);
		expected.put(5, 1);
		expected.put(6, 1);
		expected.put(7, 1);
		expected.put(8, 1);
		expected.put(9, 3);
		expected.put(10, 1);
		expected.put(11, 1);
		expected.put(12, 2);
		expected.put(13, 2);
		
		//test 
		assertNotNull(testGraph.inDegree());
		assertNotNull(expected);
		assertEquals(testGraph.inDegree(),expected);
		
	}
	
	@Test
	public void testOutDegree() {
		//create expected map
		Map<Integer,Integer> expected = new HashMap<Integer, Integer>();
		expected.put(1, 2);
		expected.put(2, 3);
		expected.put(3, 1);
		expected.put(4, 1);
		expected.put(5, 2);
		expected.put(6, 2);
		expected.put(7, 3);
		expected.put(8, 3);
		expected.put(9, 0);
		expected.put(10, 0);
		expected.put(11, 0);
		expected.put(12, 0);
		expected.put(13, 0);
		
		//test
		assertNotNull(testGraph.outDegree());
		assertNotNull(expected);
		assertEquals(testGraph.outDegree(),expected);
	}
	
	@Test
	public void testTopSort() {
		//create expected topsort list
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(7);
		expected.add(8);
		expected.add(13);
		expected.add(12);
		expected.add(2);
		expected.add(11);
		expected.add(3);
		expected.add(5);
		expected.add(6);
		expected.add(4);
		expected.add(9);
		expected.add(10);
		
		//test
		assertNotNull(testGraph.topSort());
		assertNotNull(expected);
		assertEquals(testGraph.topSort(), expected);
	}
	
	@Test
	public void testIsDag() {
		assertTrue(testGraph.isDag());
		testGraph.add(13,7);
		assertFalse(testGraph.isDag());
	}
	
	@Test
	public void testBfs() {
		//create expected map
		Map <Integer, Integer> expected = new HashMap<Integer, Integer>();
		expected.put(1, 0);
		expected.put(2, 1);
		expected.put(3, 2);
		expected.put(4, 2);
		expected.put(5, 3);
		expected.put(6, 4);
		expected.put(7, 1);
		expected.put(8, 2);
		expected.put(9, 3);
		expected.put(10, 4);
		expected.put(11, 2);
		expected.put(12, 2);
		expected.put(13, 2);
		
		//test start from the root node 1
		assertNotNull(testGraph.bfs(1));
		assertNotNull(expected);
		assertEquals(testGraph.bfs(1),expected);
		
	}

}
