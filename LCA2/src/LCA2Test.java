
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
	
	//create a binary tree here as test data
		/*       10
		 *      /  \
		 *     6    15
		 *    / \   / \
		 *   3   8 12  17
		 *  / \   \    / \
		 * 1   4   9  16  20
		 *                 \
		 *                 21
		 */
	
	LCA2 testGraph = new LCA2();
	LCA2 testTree = new LCA2();
	
	@Before
	public void setup() {
		
		//DAG test setup
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
		
		//binary tree test setup
		testTree.add(10);
		testTree.add(10,6);
		testTree.add(10,15);
		testTree.add(6,3);
		testTree.add(6,8);
		testTree.add(3,1);
		testTree.add(3,4);
		testTree.add(8,9);
		testTree.add(15,12);
		testTree.add(15,17);
		testTree.add(17,16);
		testTree.add(17,20);
		testTree.add(20,21);
	}
	
	@Test
	public void testContains() {
		
		//test for dag graph
		
		assertTrue(testGraph.contains(9));
	    assertTrue(testGraph.contains(12));
		assertTrue(testGraph.contains(1));
		assertTrue(testGraph.contains(8));
		
		assertFalse(testGraph.contains(42));
		assertFalse(testGraph.contains(0));
		assertFalse(testGraph.contains(-3));
		
		
		
		//test for binary tree 
		
		assertTrue(testTree.contains(10));
		assertTrue(testTree.contains(6));
		assertTrue(testTree.contains(20));
				
		//test the nodes not exist in the tree
		assertFalse(testTree.contains(25));
		assertFalse(testTree.contains(7));
		assertFalse(testTree.contains(13));
	}
	
	@Test
	public void testInDegree() {
		
		//test for dag graph
		
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
		
		
		
		//test for binary tree
		
		Map<Integer, Integer> expectedT = new HashMap<Integer, Integer>();
		expectedT.put(1, 1);
		expectedT.put(3, 1);
		expectedT.put(4, 1);
		expectedT.put(6, 1);
		expectedT.put(8, 1);
		expectedT.put(9, 1);
		expectedT.put(10, 0);
		expectedT.put(12, 1);
		expectedT.put(15, 1);
		expectedT.put(16, 1);
		expectedT.put(17, 1);
		expectedT.put(20, 1);
		expectedT.put(21, 1);
		
		//test
		assertNotNull(testTree.inDegree());
		assertNotNull(expectedT);
		assertEquals(testTree.inDegree(),expectedT);
	}
	
	@Test
	public void testTopSort() {
		
		//test for dag graph
		
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
		
		
		
		//test for binary tree
		List<Integer> expectedT = new ArrayList<Integer>();
		expectedT.add(10);
		expectedT.add(15);
		expectedT.add(17);
		expectedT.add(20);
		expectedT.add(21);
		expectedT.add(16);
		expectedT.add(12);
		expectedT.add(6);
		expectedT.add(8);
		expectedT.add(9);
		expectedT.add(3);
		expectedT.add(4);
		expectedT.add(1);
		
		//test
		assertNotNull(testTree.topSort());
		assertNotNull(expectedT);
		assertEquals(testTree.topSort(),expectedT);
	}
	
	@Test
	public void testIsDag() {
		
		//test for dag graph
		
		assertTrue(testGraph.isDag());
		testGraph.add(13,7);
		assertFalse(testGraph.isDag());
		
		
		
		//test for binary tree
		
		assertTrue(testTree.isDag());
		testTree.add(15,10);
		assertFalse(testTree.isDag());
	}
	
	@Test
	public void testParents() {
		
		//test for dag graph
		
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
		
		
		
		//test for binary tree
		
		Map<Integer, Integer> expectedT = new HashMap<Integer, Integer>();
		expectedT.put(10,0);
		expectedT.put(6, 1);
		expectedT.put(3, 2);
		expectedT.put(4, 3);
		
		//test the parents of vertex 4
		assertNotNull(testTree.parents(4));
		assertNotNull(expectedT);
		assertEquals(testTree.parents(4),expectedT);
		
		expectedT.clear();
		expectedT.put(10,0);
		expectedT.put(15, 1);
		expectedT.put(17, 2);
		expectedT.put(20, 3);
		expectedT.put(21, 4);
		
		//test the parents of vertex 21
		assertNotNull(testTree.parents(21));
		assertNotNull(expectedT);
		assertEquals(testTree.parents(21),expectedT);
	}
	
	@Test
	public void testLca() {
		
		//test for dag graph
		
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
		
		/*thoughts: there are two roots in the DAG right now. though vertex 1 and 14 are both 
		 *          common ancestors for vertex 2 and 7, but compare the depth, depth of 14 is
		 *          2 and depth of 1 is 1. So the program choose 14 to be the "lowest" common ancestor
		*/
		
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
		
		/*conclusion: there is a bit confusion about the definition of LCA, from my understanding, 
		 *            the lowest common ancestor for vertex a and b is common ancestors which have
		 *			  the highest depth, regardless how many roots there are.
		*/
		
		
		
		//test for binary tree
		
		List<Integer> expectedT = new ArrayList<Integer>();
		
		expectedT.add(3);
		
		//test 1, the node 1 and node 4, lca is node 3
		assertEquals("the return result should be 3", expectedT, testTree.lca(1, 4));
		
		expectedT.clear();
		expectedT.add(6);
		//test 2, the node 1 and node 9, lca is node 6
		assertEquals("the return result should be 6", expectedT, testTree.lca(1, 9));
		
		expectedT.clear();
		expectedT.add(17);
		//test 3, the node 16 and node 21, lca is node 17
		assertEquals("the return result should be 17", expectedT, testTree.lca(16, 21));
		
		expectedT.clear();
		expectedT.add(15);
		//test 4, the node 20 and node 15, lca is node 15
		assertEquals("the return result should be 15", expectedT, testTree.lca(20, 15));
				
		expectedT.clear();
		expectedT.add(10);
		//test 5, the node 1 and node 12, lca is node 10
		assertEquals("the return result should be 10", expectedT, testTree.lca(1, 12));
		
		//test 6, the node 3 and node 16, lca is node 10
		assertEquals("the return result should be 10", expectedT, testTree.lca(3, 16));
			
		//test 8, the node 1 and node 25, lca is 0, because node 25 is not found
		assertNull("the return result should be null",testTree.lca(1, 25));
		//test 9, the node 2 and node 13, lca is 0, because both nodes are not found in the tree(family)
		assertNull("the return result should be null", testTree.lca(2, 13));
		
	}

}
