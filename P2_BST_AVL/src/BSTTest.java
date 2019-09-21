////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BST/AVL
// Files:           BST.java, BSTTest.java, AVL.java, AVLTest.java
// Course:          CS400, Spring 2019
//
// Author:          Stephen Fan
// Email:           sfan54@wisc.edu
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Binary Search Tree test class
 * @author Stephen Fan
 *
 */
public class BSTTest extends DataStructureADTTest {
	 
	BST<String,String> bst;
	BST<Integer,String> bst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// The setup must initialize this class's instances 
		// and the super class instances.
		// Because of the inheritance between the interfaces and classes,
		// we can do this by calling createInstance() and casting to the desired type
		// and assigning that same object reference to the super-class fields.
		dataStructureInstance = bst = createInstance();
		dataStructureInstance2 = bst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = bst = null;
		dataStructureInstance2 = bst2 = null;
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected BST<String, String> createInstance() {
		return new BST<String,String>();
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected BST<Integer, String> createInstance2() {
		return new BST<Integer,String>();
	}

	/**
	 * Test that empty trees still produce a valid but empty traversal list
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_001_empty_traversal_orders() {
		try {

			List<String> expectedOrder = new ArrayList<String>();

			// Get the actual traversal order lists for each type		
			List<String> inOrder = bst.getInOrderTraversal();
			List<String> preOrder = bst.getPreOrderTraversal();
			List<String> postOrder = bst.getPostOrderTraversal();
			List<String> levelOrder = bst.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 002: "+e.getMessage());
		}

	}

	/**
	 * Test that trees with one key,value pair produce a valid traversal lists
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_002_check_traversals_after_insert_one() {

		try {

			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			bst2.insert(10,"ten");
			if (bst2.numKeys()!=1) 
				fail("added 10, size should be 1, but was "+bst2.numKeys());

			List<Integer> inOrder = bst2.getInOrderTraversal();
			List<Integer> preOrder = bst2.getPreOrderTraversal();
			List<Integer> postOrder = bst2.getPostOrderTraversal();
			List<Integer> levelOrder = bst2.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: "+e.getMessage());
		}

	}
	

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_003_check_inOrder_for_balanced_insert_order() {
		// insert 20-10-30 BALANCED
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	
	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Pre-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_004_check_preOrder_for_balanced_insert_order() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // L
			expectedOrder.add(10);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 005: "+e.getMessage());
		}

	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Post-Order traversal order: 10-30-20
	 */
	@Test
	void testBST_005_check_postOrder_for_balanced_insert_order() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(30);   // V
			expectedOrder.add(20);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 006: "+e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Level-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_006_check_levelOrder_for_balanced_insert_order() {
		
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // L
			expectedOrder.add(10);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 007: "+e.getMessage());
		}
	}

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R
			
			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 008: "+e.getMessage());
		}


	}

	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Pre-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_008_check_preOrder_for_not_balanced_insert_order() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 009: "+e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Post-Order traversal order: 30-20-10
	 */
	@Test
	void testBST_009_check_postOrder_for_not_balanced_insert_order() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(30);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(10);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 010: "+e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
	 */
	@Test
	void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 011: "+e.getMessage());
		}
		
	}
	
	/**
	 * Test that the pre-order traversal order is correct
	 * 
	 * Insert order: 20-10-40-30-50
	 * Pre-Order traversal order: 20-10-40-30-50
	 */
	@Test
	void testBST_011_check_preOrder() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // L
			expectedOrder.add(10);   // V
			expectedOrder.add(40);   // R
			expectedOrder.add(30);   // R
			expectedOrder.add(50);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 012: "+e.getMessage());
		}
	}
	
	/**
	 * Test that the in-order traversal order is correct
	 * 
	 * Insert order: 20-10-40-30-50
	 * In-Order traversal order: 10-20-30-40-50
	 */
	@Test
	void testBST_012_check_inOrder() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R
			expectedOrder.add(40);   // R
			expectedOrder.add(50);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 013: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getKeyOfRightChildOf() method works as intended
	 */
	@Test
	void testBST_013_check_getKeyOfRightChildOf() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int key = bst2.getKeyOfRightChildOf(40);

			// key should be 50
			if (key != 50) {
				fail("Key should be 50, not: " + key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 014: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getKeyOfRightChildOf() method works as intended
	 */
	@Test
	void testBST_014_check_getKeyOfRightChildOf2() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int key = bst2.getKeyOfRightChildOf(20);

			// key should be 40
			if (key != 40) {
				fail("Key should be 40, not: " + key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 015: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getKeyOfLeftChildOf() method works as intended
	 */
	@Test
	void testBST_015_check_getKeyOfLeftChildOf() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int key = bst2.getKeyOfLeftChildOf(40);

			// key should be 30
			if (key != 30) {
				fail("Key should be 30, not: " + key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 016: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getKeyOfLeftChildOf() method works as intended
	 */
	@Test
	void testBST_016_check_getKeyOfLeftChildOf2() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int key = bst2.getKeyOfLeftChildOf(20);

			// key should be 10
			if (key != 10) {
				fail("Key should be 10, not: " + key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 017: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getHeight() method returns the correct height of the tree
	 */
	@Test
	void testBST_017_check_getHeight() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(40,"3rd key inserted");
			bst2.insert(30,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int height = bst2.getHeight();

			// height should be 3
			if (height != 3) {
				fail("Height should be 3, not: " + height);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 018: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getHeight() method returns the correct height of the tree
	 */
	@Test
	void testBST_018_check_getHeight2() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			bst2.insert(40,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			int height = bst2.getHeight();

			// height should be 5
			if (height != 5) {
				fail("Height should be 5, not: " + height);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 018: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the getHeight() method returns the correct height of the tree
	 */
	@Test
	void testBST_019_check_getHeight3() {
		try {
			int height = bst2.getHeight();

			// height should be 0
			if (height != 0) {
				fail("Height should be 0, not: " + height);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 019: "+e.getMessage());
		}
	}
	
	/**
	 * tests that the contains method returns true if the node is in the tree and
	 * false otherwise
	 */
	@Test
	void testBST_019_check_contains() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted"); // 20 is in the tree
			bst2.insert(30,"3rd key inserted");
			bst2.insert(40,"4th key inserted");
			bst2.insert(50,"5th key inserted");

			// checks if 20 is in the tree
			if (bst2.contains(20) != true) {
				fail("BST should contain 20");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 020: "+e.getMessage());
		}
	}
	
	// TODO: Add your own tests
	
	// Add tests to make sure that get and remove work as expected.
	// Does the height of the tree reflect it's actual and its expected height?
	// Use the traversal orders to check.
	
	// Can you insert many and still "get" them back out?
	
	// Does delete work?  
	// When the key is a leaf?  node with one left child?
	// node with one right child?  node with two children?
	
	// Write replacement value did you choose? 
	// in-order precessor?  in-order successor?
	// How can you test if it is replaced correctly?
	


}
