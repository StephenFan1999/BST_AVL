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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the rebalancing of the AVL tree operations

//@SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

	AVL<String,String> avl;
	AVL<Integer,String> avl2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = bst = avl = createInstance();
		dataStructureInstance2 = bst2 = avl2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		avl = null;
		avl2 = null;
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected AVL<String, String> createInstance() {
		return new AVL<String,String>();
	}


	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected AVL<Integer, String> createInstance2() {
		return new AVL<Integer,String>();
	}

	/** 
	 * Insert three values in sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred.
	 */
	@Test
	void testAVL_001_insert_sorted_order_simple() {
		try {
			// checks that insert and rotates are working as intended
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfRightChildOf(10).equals(20)) 
				fail("avl insert to right child of root does not work");
			
			avl2.insert(30, "30");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 001: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values in reverse sorted order and then check 
	 * the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_002_insert_reversed_sorted_order_simple() {
		try {
			// checks that insert and rotates are working as intended
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(20, "20");
			if (!avl2.getKeyOfLeftChildOf(30).equals(20)) 
				fail("avl insert to left child of root does not work");
			
			avl2.insert(10, "10");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 002: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values so that a right-left rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_003_insert_smallest_largest_middle_order_simple() {
		try {
			// checks that insert and rotates are working as intended
			avl2.insert(10, "10");
			if (!avl2.getKeyAtRoot().equals(10)) 
				fail("avl insert at root does not work");
			
			avl2.insert(30, "30");
			if (!avl2.getKeyOfRightChildOf(10).equals(30)) 
				fail("avl insert to left child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 003: "+e.getMessage() );
		}
	}

	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if rebalancing 
	 * occurred in the other direction.
	 */
	@Test
	void testAVL_004_insert_largest_smallest_middle_order_simple() {
		try {
			// checks that insert and rotates are working as intended
			avl2.insert(30, "30");
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work");
			
			avl2.insert(10, "10");
			if (!avl2.getKeyOfLeftChildOf(30).equals(10)) 
				fail("avl insert to left child of root does not work");
			
			avl2.insert(20, "20");
			Integer k = avl2.getKeyAtRoot();
			if (!k.equals(20)) 
				fail("avl rotate does not work");
			
			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(avl2.getKeyAtRoot(),new Integer(20));
			Assert.assertEquals(avl2.getKeyOfLeftChildOf(20),new Integer(10));
			Assert.assertEquals(avl2.getKeyOfRightChildOf(20),new Integer(30));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 004: "+e.getMessage() );
		}
	}
	
	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then remove the root which should be 20, and check that 30 is the new root
	 * and 10 is its left child
	 */
	@Test
	void testAVL_005_insert_3_remove_root() {
		try {
			avl2.insert(30, "30");
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			
			avl2.remove(20);
			
			if (!avl2.getKeyAtRoot().equals(30)) 
				fail("avl insert at root does not work. Root is: " + avl2.getKeyAtRoot());
			
			
			if (!avl2.getKeyOfLeftChildOf(30).equals(10)) 
				fail("avl insert to left child of root does not work");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 005: "+e.getMessage() );
		}
	}
	
	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then remove the left child of the root which is 10, and check that the tree
	 * is still balanced and correct
	 */
	@Test
	void testAVL_006_insert_3_remove_left_child() {
		try {
			avl2.insert(30, "30");
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			
			avl2.remove(10);
			
			if (!avl2.getKeyAtRoot().equals(20)) 
				fail("avl insert at root does not work. Root is: " + avl2.getKeyAtRoot());
			
			
			if (!avl2.getKeyOfRightChildOf(20).equals(30)) 
				fail("avl insert to left child of root does not work");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 000: "+e.getMessage() );
		}
	}
	
	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then remove the right child of the root which is 30, and check that the tree
	 * is still balanced and correct
	 */
	@Test
	void testAVL_007_insert_3_remove_right_child() {
		try {
			avl2.insert(30, "30");
			avl2.insert(10, "10");
			avl2.insert(20, "20");
			
			avl2.remove(30);
			
			if (!avl2.getKeyAtRoot().equals(20)) 
				fail("avl insert at root does not work. Root is: " + avl2.getKeyAtRoot());
			
			
			if (!avl2.getKeyOfLeftChildOf(20).equals(10)) 
				fail("avl insert to left child of root does not work");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 006: "+e.getMessage() );
		}
	}
	
	/** 
	 * Insert three values so that a left-right rotation is
	 * needed to fix the balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then remove the right child of the root which is 30, and check that the tree
	 * is still balanced and correct
	 */
	@Test
	void testAVL_008_insert_lots1() {
		try {
			avl2.insert(80, "80");
			avl2.insert(50, "50");
			avl2.insert(30, "30");
			avl2.insert(20, "20");
			avl2.insert(40, "40");
			avl2.insert(60, "60");
			avl2.insert(90, "90");
			avl2.insert(10, "10");
			avl2.insert(70, "70");
			
			if (!avl2.getKeyAtRoot().equals(50)) 
				fail("avl insert at root does not work. Root is: " + avl2.getKeyAtRoot());
			
			
			if (!avl2.getKeyOfLeftChildOf(50).equals(30)) 
				fail("avl insert to left child of root does not work");
			
			if (!avl2.getKeyOfRightChildOf(50).equals(80)) 
				fail("avl insert to right child of root does not work");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail( "Unexpected exception AVL 007: "+e.getMessage() );
		}
	}
	
	// TODO: Add your own tests
	
	// Add tests to make sure that rebalancing occurs even if the 
	// tree is larger.   Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.
	
	// Can you insert many and still "get" them back out?
	
	// Does delete work?  Does the tree maintain balance when a key is deleted?

}
