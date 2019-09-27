
import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
	
	LCA testTree;
	
	public void setup() {
		testTree = new LCA();
		testTree.add(10);
		testTree.add(6);
		testTree.add(3);
		testTree.add(1);
		testTree.add(8);
		testTree.add(9);
		testTree.add(4);
		testTree.add(15);
		testTree.add(12);
		testTree.add(17);
		testTree.add(16);
		testTree.add(20);
		testTree.add(21);
	}
	 
	@Test
    public void lcaTest () {
		 
    }
	    

}
