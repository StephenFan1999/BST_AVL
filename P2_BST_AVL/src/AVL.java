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

import java.util.LinkedList;
import java.util.Queue;

// A BST search tree that maintains its balance using AVL rotations.
public class AVL<K extends Comparable<K>,V> extends BST<K, V> {

	// must add rebalancing to BST via rotate operations

	/**
	 * insert method for inserting a new node into the AVL
	 * @param key is the key of the node being inserted
	 * @param value is the value of the node being inserted
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // check that key is not null and throw exception if it is
        if (key == null) {
        	throw new IllegalNullKeyException();
        }
        
        root = insertHelper(root, key, value);
        numKeys++;
	}
	
	/**
	 * private helper method for implementing insert method
	 * @param current is the current BSTNode that we are comparing to
	 * @param key is the key
	 * @param value is the value
	 * @return current is the unchanged current node
	 * @throws DuplicateKeyException if a duplicate key is added
	 */
	private BSTNode<K,V> insertHelper(BSTNode<K,V> current, K key, V value) throws DuplicateKeyException {
		// check if current is null
		if (current == null) {
			return (new BSTNode<K,V>(key,value));
		}
		
		// if keys are equal, there is a duplicate key exception
		if (key.compareTo(current.key) == 0) {
			throw new DuplicateKeyException();
		}
		// if key is less than the current key, insert in left subtree
		else if (key.compareTo(current.key) < 0) {
			current.left = insertHelper(current.left, key, value);
		}
		// if key is greater than the current key, insert in right subtree
		else if (key.compareTo(current.key) > 0) {
			current.right = insertHelper(current.right, key, value);
		}
		
		// Calculate balance factor. 4 possible rotations needed.
		int balance = getBalanceFactor(current);
		
		// Case 1: Left-Left Rotation
		if (balance > 1 && key.compareTo(current.left.key) < 0) {
			return rotateRight(current);
		}
		
		// Case 2: Right-Right Rotation
		if (balance < -1 && key.compareTo(current.right.key) > 0) {
			return rotateLeft(current);
		}
		
		// Case 3: Left-Right Rotation
		if (balance > 1 && key.compareTo(current.left.key) > 0) {
			current.left = rotateLeft(current.left);
			return rotateRight(current);
		}
		
		// Case 4: Right-Left Rotation
		if (balance < -1 && key.compareTo(current.right.key) < 0) {
			current.right = rotateRight(current.right);
			return rotateLeft(current);
		}
		
		// return unchanged current node
		return current;
	}
	
	/**
	 * remove method for removing nodes from the AVL
	 * @param key is the key of the node being removed
	 * @return removed is true if the node was removed and false if it was not
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// initialize return variable
		boolean removed = false;
		
		// check if key is null
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		// check if tree contains the key and remove it if it is
		if (contains(key) == true) {
			this.root = removeHelper(key, this.root);
			removed = true;
		}
		// else the key was not found
		else {
			throw new KeyNotFoundException();
		}
		
		return removed;

	}
	
	/**
	 * private helper method for the remove method
	 * @param key is the key of the node being removed
	 * @param current is the current root node
	 * @return current is the current node
	 */
	private BSTNode<K,V> removeHelper(K key, BSTNode<K,V> current) {
		// check if current is null
		if (current == null) {
			return current;
		}
		
		// if key is less than the current node's key, remove from left subtree
		if (key.compareTo(current.key) < 0) {
			current.left = removeHelper(key, current.left);
		}
		// if key is greater than the current node's key, remove from right subtree
		else if (key.compareTo(current.key) > 0) {
			current.right = removeHelper(key, current.right);
		}
		// else the key's are equal and we can remove it
		else {
			// case if there is only one child or there are no children
			if (current.left == null || current.right == null) {
				BSTNode<K,V> temp = null;
				if (current.left == null) {
					temp = current.right;
				}
				else {
					temp = current.left;
				}
				
				// case if there are no children
				if (temp == null) {
					temp = current;
					current = null;
				}
				// case if there is one child
				else {
					current = temp;
				}
			}
			// case if the node has two children
			// need to get the in-order successor, copy it to the current node, and delete it
			else {
				BSTNode<K,V> temp = minNode(current.right);
				
				current.key = temp.key;
				
				current.right = removeHelper(temp.key, current.right);
			}
		}
		
		// Calculate balance factor. 4 possible rotations needed.
		int balance = getBalanceFactor(current);
		
		// Case 1: Left-Left Rotation
		if (balance > 1 && getBalanceFactor(current.left) >= 0) {
			return rotateRight(current);
		}
		
		// Case 2: Left-Right Rotation
		if (balance > 1 && getBalanceFactor(current.left) < 0) {
			current.left = rotateLeft(current.left);
			return rotateRight(current);
		}
		
		// Case 3: Right-Right Rotation
		if (balance < -1 && getBalanceFactor(current.right) <= 0) {
			return rotateLeft(current);
		}
		
		// Case 4: Right-Left Rotation
		if (balance < -1 && getBalanceFactor(current.right) > 0) {
			current.right = rotateRight(current.right);
			return rotateLeft(current);
		}
			
		return current;
	}
	
	/**
	 * private method for obtaining the minimum node in a tree
	 * @param root is the root of the tree
	 * @return current is the node of the minimum key node in the tree
	 */
	private BSTNode<K,V> minNode(BSTNode<K,V> root) {
		BSTNode<K,V> current = root;
		
		// iterate left through the tree
		while (current.left != null) {
			current = current.left;
		}
		
		return current;
	}
	
	/**
	 * private method for getting the balance factor of a node in a tree
	 * @param node is the node you want the balance factor of
	 * @return the value of the balance factor of the node
	 */
	private int getBalanceFactor(BSTNode<K,V> node) {
		// check if node is null
		if (node == null) {
			return 0;
		}
		
		return getHeight(node.left) - getHeight(node.right);
	}
	
	/**
	 * private method specific to AVL that does a right-rotate on 3 nodes to balance the
	 * tree out
	 * @param node is the node on the highest level of the tree that is being rotated
	 * @return the left child of the given node
	 */
	private BSTNode<K,V> rotateRight(BSTNode<K,V> node) {
		// left child of given node
		BSTNode<K,V> leftNode = node.left;
		
		// right child of left child of given node
		BSTNode<K,V> rightNode = leftNode.right;
		
		//rotate
		leftNode.right = node;
		node.left = rightNode;
		
		return leftNode;
	}
	
	/**
	 * private method specific to AVL that does a left-rotate on 3 nodes to balance the
	 * tree out
	 * @param node is the node on the highest level of the tree that is being rotated
	 * @return the right child of the given node
	 */
	private BSTNode<K,V> rotateLeft(BSTNode<K,V> node) {
		// left and right nodes of given node
		BSTNode<K,V> rightNode = node.right;
		BSTNode<K,V> leftNode = rightNode.left;
		
		//rotate
		rightNode.left = node;
		node.right = leftNode;
		
		return rightNode;
	}
	
	/**
	 * private method to get the height of the AVL tree
	 * @param node is the root node of the tree whose height is being obtained
	 * @return the height of the tree
	 */
	private int getHeight(BSTNode<K,V> node) {
		// base case
		if (node == null) {
			return 0;
		}
		
		// create queue for level order traversal and add root
		Queue<BSTNode<K,V>> queue = new LinkedList();
		queue.add(node);
		
		int height = 0;
		
		while (true) {
			// counter for nodes on level
			int nodesOnLevel = queue.size();
			
			if (nodesOnLevel == 0) {
				return height;
			}
			
			height++;
			
			// dequeue all nodes on current level and enqueue all nodes on next level
			while (nodesOnLevel > 0) {
				BSTNode<K,V> nextNode = queue.peek();
				queue.remove();
				if (nextNode.left != null) {
					queue.add(nextNode.left);
				}
				if (nextNode.right != null) {
					queue.add(nextNode.right);
				}
				nodesOnLevel--;
			}
		}
		
		
	}

}
