
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;

public class LCATests {
	
	//create a tree here as test data
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
	
	LCA testTree = new LCA();
	
	@Before
	public void setup() {
		testTree.insert(10);
		testTree.insert(6);
		testTree.insert(15);
		testTree.insert(3);
		testTree.insert(8);
		testTree.insert(12);
		testTree.insert(17);
		testTree.insert(4);
		testTree.insert(16);
		testTree.insert(20);
		testTree.insert(1);
		testTree.insert(9);
		testTree.insert(21);
	}
	
	@Test
	public void testSearch() {
		//test the nodes exist in the tree
		assertTrue(testTree.search(testTree.root,10));
		assertTrue(testTree.search(testTree.root,6));
		assertTrue(testTree.search(testTree.root,20));
		//test the nodes not exist in the tree
		assertFalse(testTree.search(testTree.root,25));
		assertFalse(testTree.search(testTree.root,7));
		assertFalse(testTree.search(testTree.root,13));
	}
	
	@Test
	public void testLca() {
		//test 1, the node 1 and node 4, lca is node 3
		assertEquals("the return result should be 3", 3, testTree.lca(testTree.root,1, 4));
		//test 2, the node 1 and node 9, lca is node 6
		assertEquals("the return result should be 6", 6, testTree.lca(testTree.root, 1, 9));
		//test 3, the node 1 and node 12, lca is node 10
		assertEquals("the return result should be 10", 10, testTree.lca(testTree.root, 1, 12));
		//test 4, the node 16 and node 21, lca is node 17
		assertEquals("the return result should be 17", 17, testTree.lca(testTree.root, 16, 21));
		//test 5, the node 20 and node 15, lca is node 15
		assertEquals("the return result should be 15", 15, testTree.lca(testTree.root, 20, 15));
		
		//test 6, the node 1 and node 25, lca is 0, because node 25 is not found
		assertEquals("the return result should be 0", 0, testTree.lca(testTree.root, 1, 25));
		//test 7, the node 2 and node 13, lca is 0, because both nodes are not found in the tree(family)
		assertEquals("the return result should be 0", 0, testTree.lca(testTree.root, 2, 13));
	}
	
}
