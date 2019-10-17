
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
		testGraph.add(12,12);
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
	public void testParents() {
		//create expected map
		Map<Integer,Integer> expected = new HashMap<Integer, Integer>();
		expected.put(1,0);
		expected.put(2,1);
		expected.put(3,2);
		expected.put(5,3);
		
		//test the parents of vertex 5
		assertNotNull(testGraph.parents(5));
		assertNotNull(expected);
		assertEquals(testGraph.parents(5),expected);
		
		//test the parents of vertex 9
		expected.clear();
		expected.put(1, 0);
		expected.put(2, 1);
		expected.put(3, 2);
		expected.put(4, 5);
		expected.put(5, 3);
		expected.put(6, 4);
		expected.put(7, 1);
		expected.put(8, 2);
		expected.put(9, 6);
		
		assertNotNull(testGraph.parents(9));
		assertNotNull(expected);
		assertEquals(testGraph.parents(9),expected);
		
	}
	
	@Test
	public void testLca() {
		//create expected List
		List<Integer> expected = new ArrayList<Integer>();
		
		//test1 vertex 3 and vertex 11 lca is 2
		expected.add(2);
		
		assertEquals(testGraph.lca(3, 11),expected);
		
		//test2 vertex 5 and vertex 9 lca is 5
		expected.clear();
		expected.add(5);
		assertEquals(testGraph.lca(5, 9),expected);
		
		//test3 vertex 4 and vertex 8 lca is 1
		expected.clear();
		expected.add(1);
		assertEquals(testGraph.lca(4,8),expected);
		
		//test4 vertex 3 and vertex 4 lca is 3
		expected.clear();
		expected.add(3);
		assertEquals(testGraph.lca(3, 4),expected);
		
		//test5 vertex 10 and vertex 9 lca is 5
		expected.clear();
		expected.add(5);
		assertEquals(testGraph.lca(9, 10),expected);
		
		//test6 vertex 10 and vertex 11 lca is 2
		expected.clear();
		expected.add(2);
		assertEquals(testGraph.lca(10, 11),expected);
		
		//test7 vertex 8, vertex 13 lca is 8
		expected.clear();
		expected.add(8);
		assertEquals(testGraph.lca(8, 13),expected);
		
		//test8 vertex 12 and vertex 13 lca is 8 because 8 is still 7's child
		expected.clear();
		expected.add(8);
		
		assertEquals(testGraph.lca(12,13),expected); 
		
		//test 9 add vertex 14, points to 2 and 7
		//then the lca of 2 and 7 is 1 and 14
		testGraph.add(14, 2);
		testGraph.add(14,7);
		
		expected.clear();
		expected.add(1);
		expected.add(14);
		
		assertTrue(testGraph.isDag());   //make sure there is no cycle first
		assertEquals(testGraph.lca(2, 7),expected);
		
		//test10 add vertex 15, points to 14
		//the lca of 2 and 7 should not change
		//but it might be changed to 14 no 1 any more
		testGraph.add(15,14);
		
		expected.clear();
		expected.add(14);
		
		assertTrue(testGraph.isDag());
		assertEquals(testGraph.lca(2, 7), expected);
		
		//thoughts: there are two roots in the DAG right now. though vertex 1 and 14 are both 
		//          common ancestors for vertex 2 and 7, but compare the depth, depth of 14 is
		//          2 and depth of 1 is 1. So the program choose 14 to be the "lowest" common ancestor
		
		//So, now, what about other nodes, anything changed?
		//repeat test 1 - passed
		expected.clear();
		expected.add(2);
		
		assertEquals(testGraph.lca(3, 11),expected);
		
		//repeat test 4 -passed
		expected.clear();
		expected.add(3);
		assertEquals(testGraph.lca(3, 4),expected);
		
		//repeat test 7 -passed
		expected.clear();
		expected.add(2);
		assertEquals(testGraph.lca(10, 11),expected);
		
		//repeat test 8 -passed
		expected.clear();
		expected.add(8);
		
		assertEquals(testGraph.lca(12,13),expected); 
		
		//conclusion: there is a bit confusion about the definition of LCA, from my understanding, 
		//            the lowest common ancestor for vertex a and b is common ancestors which have
		//			  the highest depth, regardless how many roots there are.
		
	}

}
