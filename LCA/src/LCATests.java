
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
	
}
