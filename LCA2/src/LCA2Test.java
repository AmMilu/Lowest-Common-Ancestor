
import static org.junit.Assert.*;

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

}
