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

import java.util.ArrayList;  // allowed for creating traversal lists
import java.util.LinkedList;
import java.util.List;       // required for returning List<K>
import java.util.Queue;

/**
 * Binary Search Tree class made up of BSTNodes with a key and a value
 * @author Stephen Fan
 *
 * @param <K> is the key
 * @param <V> is the value
 */
public class BST<K extends Comparable<K>,V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K,V> root;
	protected int numKeys; // number of keys in BST

	// Must have a public, default no-arg constructor
	public BST() { 
		this.root = null;
		this.numKeys = 0;
	}

	/**
	 * method to obtain a pre-order traversal of the BST
	 * @return preList is a list of the keys of the nodes in the BST
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		List<K> preList = new ArrayList<K>();
		
		getPreOrder(root, preList);
		
		return preList;
	}
	
	/**
	 * private helper method for getPreOrderTraversal() method
	 * @param the root of the BST that is being traversed
	 * @return a list of the nodes in the BST
	 */
	private void getPreOrder(BSTNode root, List<K> preList) {
		// check if root is null
		if (root == null) {
			return;
		}
		else {
			// root, left, right
		    preList.add((K) root.key);
		
		    getPreOrder(root.left,preList);
		    getPreOrder(root.right,preList);
		}
	}

	/**
	 * method to obtain a post-order traversal of the BST
	 * @return postList is a list of the keys of the nodes in the BST
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		List<K> postList = new ArrayList<K>();
		
		getPostOrder(root, postList);
		
		return postList;
	}
	
	/**
	 * private helper method for getPostOrderTraversal() method
	 * @param the root of the BST that is being traversed
	 * @return a list of the nodes in the BST
	 */
	private void getPostOrder(BSTNode root, List<K> postList) {
		// check if root is null
        if (root == null) {
        	return;
        }
        else {
        	// left, right, root
        	getPostOrder(root.left,postList);
        	getPostOrder(root.right,postList);
        
        	postList.add((K) root.key);
        }
	}

	/**
	 * method to obtain a level-order traversal of the BST
	 * @return levelList is a list of the keys of the nodes in the BST
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// initialize ArrayList of keys and queue of nodes
		List<K> levelList = new ArrayList<K>();
		Queue<BSTNode<K,V>> queue = new LinkedList<BSTNode<K,V>>();
		
		queue.add(root);
		
		// add each level to levelList while queue is not empty
		while (!queue.isEmpty()) {
			BSTNode<K,V> node = queue.poll();
			if (node == null) {
				break;
			}
			levelList.add(node.key);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		
		return levelList;
	}

	/**
	 * method to obtain an in-order traversal of the BST
	 * @return inList is a list of the keys of the nodes in the BST
	 */
	@Override
	public List<K> getInOrderTraversal() {
		List<K> inList = new ArrayList<K>();
		
		getInOrder(root, inList);
		
		return inList;
	}
	
	/**
	 * private helper method for getPostOrderTraversal() method
	 * @param the root of the BST that is being traversed
	 * @return a list of the nodes in the BST
	 */
	private void getInOrder(BSTNode root, List<K> inList) {
		// check if root is null
        if (root == null) {
        	return;
        }
        else {
        	// left, root, right
        	getInOrder(root.left,inList);
        	inList.add((K) root.key);
        	getInOrder(root.right,inList);
        }
	}

	/**
	 * insert method for inserting a new node into the BST
	 * @param key is the key of the node being inserted
	 * @param value is the value of the node being inserted
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // check that key is not null and throw exception if it is
        if (key == null) {
        	throw new IllegalNullKeyException();
        }
        
        // run helper method and increment numKeys
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
		// check if current node is null
		if (current == null) {
			current = new BSTNode<K,V>(key,value);
		}
		// check if key is a duplicate of the current key
		else if (key.compareTo(current.key) == 0) {
			throw new DuplicateKeyException();
		}
		// insert in left subtree
		else if (key.compareTo(current.key) < 0) {
			current.left = insertHelper(current.left, key, value);
		}
		// insert in right subtree
		else if (key.compareTo(current.key) > 0) {
			current.right = insertHelper(current.right, key, value);
		}
		
		return current;
	}

	/**
	 * remove method for removing nodes from the BST
	 * @param key is the key of the node being removed
	 * @return removed is true if the node was removed and false if it was not
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// removed is true if the node was removed and false if it was not
		boolean removed = false;
		
		// check if key is null
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		// check if the BST contains the node, and if it does, remove it
		if (contains(key) == true) {
			this.root = removeHelper(key, this.root);
			removed = true;
		}
		// if node is not in tree throw exception
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
		// check if node is null
		if (current == null) {
			return current;
		}
		
		// if key is less than the current root node, go the left tree
		if (key.compareTo(current.key) < 0) {
			current.left = removeHelper(key, current.left);
		}
		// if key is greater than the current root node go to the right tree
		else if (key.compareTo(current.key) > 0) {
			current.right = removeHelper(key, current.right);
		}
		// else key must be equal to the current root node so remove it from the tree
		else {
			if (current.left == null) {
				return current.right;
			}
			else if (current.right == null) {
				return current.left;
			}
			
			// replace with in-order successor
			current.key = getMinValue(current.right);
			current.right = removeHelper(current.key, current.right);
		}
			
		return current;
	}
	
	/**
	 * private method that gets a node's minimum value
	 * used to obtain a node's in-order successor by passing in the right child node
	 * of the node you want the in-order successor of
	 * @param node is the root node of the tree that you want to obtain the minimum value of
	 * @return the key of the minimum value of the tree
	 */
	private K getMinValue(BSTNode<K,V> node) {
		K key = node.key;
		
		// iterate through left tree for minimum value
		while (node.left != null) {
			key = node.left.key;
			node = node.left;
		}
		
		return key;
	}

	/**
	 * get method for getting the value of a node given its key
	 * @param key is the key of the node that you want to obtain the value for
	 * @return is the value of the given key
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// check if key is null
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		BSTNode<K,V> current = this.root;
		
		return getHelper(key, current);
	}
	
	/**
	 * private helper method for the get method
	 * @param key is the key of the node that you want to obtain the value for
	 * @param current is the current node
	 * @return the value of the node with the given key
	 * @throws KeyNotFoundException when the 
	 */
	private V getHelper(K key, BSTNode<K,V> current) throws KeyNotFoundException{
		// if the current node is null the key was not found
		if (current == null) {
			throw new KeyNotFoundException();
		}
		// if keys are the same the key has been found
		if (key.compareTo(current.key) == 0) {
			return current.value;
		}
		// if key is less than the current node's key, go to the left tree
		else if (key.compareTo(current.key) < 0) {
			return getHelper(key, current.left);
		}
		// if key is greater than the current node's key, go to the right tree
		else if (key.compareTo(current.key) > 0) {
			return getHelper(key, current.right);
		}
		// else the key was not found
		else {
			throw new KeyNotFoundException();
		}
	}

	/**
	 * contains method for checking if the BST contains a node
	 * @param key is the key that you want to check if the BST contains
	 * @return true if the BST contains the node and false if not
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		// check for null key
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		// obtain list of nodes in tree
		List<K> inList = getInOrderTraversal();
		
		// use the List class' contains method to check if the key is in the BST
		if (inList.contains(key)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * simple get method for the number of keys in the BST
	 * @return numKeys is the number of keys in the BST
	 */
	@Override
	public int numKeys() {
		return this.numKeys;
	}

	/**
	 * simple get method for the key of the root of the BST
	 * @return the key of the root of the BST
	 */
	@Override
	public K getKeyAtRoot() {
		return this.root.key;
	}

	/**
	 * gets the key of the left child of a node given its key
	 * @param key is the key who you want the left child's key of
	 * @return the key of the node that is the left child of the node who's key was given
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// check if key is null
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		BSTNode<K,V> current = this.root;
		
		return getKeyOfLeftChildOfHelper(key, current);
	}
	
	/**
	 * private helper method for the getKeyOfLeftChildOf() method
	 * @param key is the key who you want the left child's key of
	 * @param node is the node of the root of the current subtree
	 * @return the key of the node that is the left child of the node who's key was given
	 * @throws KeyNotFoundException if the key was not found
	 */
	private K getKeyOfLeftChildOfHelper(K key, BSTNode<K,V> node) throws KeyNotFoundException{
		// check if node was null
		if (node == null) {
			throw new KeyNotFoundException();
		}
		// if keys are equal the key was found
		if (key.compareTo(node.key) == 0) {
			return node.left.key;
		}
		// if key is less than the node's key, then go the left subtree
		else if (key.compareTo(node.key) < 0) {
			return getKeyOfLeftChildOfHelper(key, node.left);
		}
		// if key is greater than the node's key, then go to the right subtree
		else if (key.compareTo(node.key) > 0) {
			return getKeyOfLeftChildOfHelper(key, node.right);
		}
		// else key was not found
		else {
			throw new KeyNotFoundException();
		}
	}

	/**
	 * gets the key of the right child of a node given its key
	 * @param key is the key who you want the right child's key of
	 * @return the key of the node that is the right child of the node who's key was given
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// check if key is null
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		BSTNode<K,V> current = this.root;
		
		return getKeyOfRightChildOfHelper(key, current);
	}
	
	/**
	 * private helper method for the getKeyOfRightChildOf() method
	 * @param key is the key who you want the right child's key of
	 * @param node is the node of the root of the current subtree
	 * @return the key of the node that is the right child of the node who's key was given
	 * @throws KeyNotFoundException if the key was not found
	 */
	private K getKeyOfRightChildOfHelper(K key, BSTNode<K,V> node) throws KeyNotFoundException{
		// check if node is null
		if (node == null) {
			throw new KeyNotFoundException();
		}
		// if keys are equal the key was found
		if (key.compareTo(node.key) == 0) {
			return node.right.key;
		}
		// if key is less than the node's key, go the left subtree
		else if (key.compareTo(node.key) < 0) {
			return getKeyOfRightChildOfHelper(key, node.left);
		}
		// if key is greater than the node's key, go the right subtree
		else if (key.compareTo(node.key) > 0) {
			return getKeyOfRightChildOfHelper(key, node.right);
		}
		// else key was not found
		else {
			throw new KeyNotFoundException();
		}
	}

	/**
	 * method to obtain the height of the BST
	 * @return height is the height of the BST
	 */
	@Override
	public int getHeight() {
		int height = height(root);
		
		return height;
	}
	
	/**
	 * private helper method for getHeight()
	 * @param root is the root of the tree that you want to obtain the height of
	 * @return leftheight if the height of the left subtree is larger
	 * @return rightheight if the height of the right subtree is larger
	 */
	private int height(BSTNode<K,V> root) {
		// check if root is null
		if (root == null) {
			// if root is null height is 0
			return 0;
		}
		else {
			// initialize heights of left and right subtrees
			int leftheight = height(root.left);
			int rightheight = height(root.right);
			
			// check which subtree has the greater height
			if (leftheight > rightheight) {
				// increment height
				leftheight++;
				return leftheight;
			}
			else {
				// increment height
				rightheight++;
				return rightheight;
			}
		}
		
	}
}
