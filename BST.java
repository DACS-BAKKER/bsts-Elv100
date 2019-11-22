import edu.princeton.cs.algs4.StdOut;

/*
 * Elven Shum
 * Binary Search Tree edited
 */


public class BST<T> {
    private Node root;

    private BST() {
        root = null;
    }

    public boolean contains(int key) {
        return get(key) != null;
    }

    //Checks if BST contains given key
    private T get(int key) {
        return getRecur(root, key);
    }
    //get helper method
    private T getRecur(Node node, int key) {
        if (node == null)
            return null;
        if (key < node.key)
            return getRecur(node.left, key);
        else if (key > node.key)
            return getRecur(node.right, key);
        else
            return node.element;
    }

    //Deletes Key and Value, fixing the rest
    private void delete(int key) {
        root = deleteRecur(root, key);
    }
    //delete helper method
    private Node deleteRecur(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key)
            node.left = deleteRecur(node.left, key);
        else if (key > node.key)
            node.right = deleteRecur(node.right, key);
        else if (node.right == null)
            return node.left;
        else if (node.left == null)
            return node.right;
        else {
            Node temp = node;
            node = minRecur(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    //deletes the minimum
    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    // traverse keys inorder
    private void inOrder() {
        inOrderRecur(root);
    }
    // traverse keys inorder recursive helper
    private void inOrderRecur(Node node) {
        if (node == null) {
            return;
        }
        inOrderRecur(node.left);
        StdOut.println(node.element + " ");
        inOrderRecur(node.right);
    }

    //traverse keys postOrder
    private void postOrder() {
        postOrderRecur(root);
    }
    // traverse keys postOrder recursive helper
    private void postOrderRecur(Node node) {
        if (node == null)
            return;
        // Recursion on left subtree
        postOrderRecur(node.left);
        // Recursion on right subtree
        postOrderRecur(node.right);
        // Print data of root node
        StdOut.println(node.element + " ");
    }

    // traverse keys preOrder
    private void preOrder() {
        preOrderRecur(root);
    }
    // traverse keys preOrder recursive helper
    private void preOrderRecur(Node node) {
        if (node == null)
            return;
        // Print data of root node
        StdOut.println(node.element + " ");
        // Recursion on left subtree
        preOrderRecur(node.left);
        // Recursion on right subtree
        preOrderRecur(node.right);
    }

    // places key-value pair, overwriting if the key's a dupe
    private void put(int key, T element) {
        root = put(root, key, element);
    }

    //returns size of BST
    private int size() {
        return sizeRecur(root);
    }
    //returns size of BST recursive helper
    private int sizeRecur(Node node) {
        if (node == null) {
            return 0;
        } return sizeRecur(node.left) + sizeRecur(node.right) +1;
    }

    //returns height of BST
    private int height() {
        return heightRecur(root) + 1;
    }
    //returns height of BST recursive helper
    private int heightRecur(Node node) {
        if (node == null)
            return -1;
        return Math.max(heightRecur(node.left), heightRecur(node.right)) + 1;
    }

    //returns largest key
    public int max() {
        return maxRecur(root).key;
    }
    //returns largest key recursive helper
    private Node maxRecur(Node n) {
        if (n.right == null)
            return n;
        else
            return maxRecur(n.right);
    }

    // returns smallest key
    public int min() {
        return minRecur(root).key;
    }
    //returns smallest key recursive helper
    private Node minRecur(Node x) {
        if (x.left == null)
            return x;
        else
            return minRecur(x.left);
    }

    // traverse (prints out) the keys as levels, left to right.
    private void levelOrder() {
        int height = height();
        for (int i = 1; i <= height; i++)
            printLevel(root, i);
    }


    private void printLevel(Node root, int level) {
        if (root == null)
            return;
        if (level == 1)
            StdOut.println(root.element + " ");
        else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }


    //puts key-value pair into table, "overwriting the old value with the new value if the symbol table already contains the specified key."
    private Node put(Node node, int key, T element) {
        if (node == null)
            return new Node(key, element);
        if (key < node.key)
            node.left = put(node.left, key, element);
        else if (key > node.key)
            node.right = put(node.right, key, element);
        else
            node.element = element;
        return node;
    }

    //Returns the number of key-value pairs in this symbol table

    public static void main(String[]args) {
        BST<Integer> tree = new BST<Integer>();
        tree.put(7,5);
        tree.put(9,2);
        tree.put(12, 7);
        tree.put(4,3);
        tree.put(22,6);
        tree.put(3,1);

        tree.inOrder();
        StdOut.println();
        tree.levelOrder();
        StdOut.println();
        tree.postOrder();
        StdOut.println();
        tree.preOrder();
        StdOut.println();

        tree.delete(3);
        tree.inOrder();
    }


    class Node {
        private int key;
        private T element;
        private Node left, right;

        public Node(int key, T element) {
            this.key = key;
            this.element = element;
            this.left = this.right = null;
        }
    }

}

