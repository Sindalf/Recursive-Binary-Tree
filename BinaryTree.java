
public class BinaryTree {

    Node root;
    int size;

    public boolean lookup(int data) {
        return lookup(root, data);
    }

    public void insert(int data) {
        root = insert(root, data);
        size++;
    }

    public int getSize() {
        return size;
    }

    public int calculateSize() {
        return calculateSize(root);
    }

    public int minValue() {
        return minValue(root);
    }

    public int maxValue() {
        return maxValue(root);
    }

    public void printTree() {
        printTree(root);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    public boolean hasPathSum(int sum) {
        return hasPathSum(root, sum);
    }

    public void printPaths() {
        int[] path = new int[50];
        printPaths(root, path, 0);
    }

    public void mirror() {
        mirror(root);
    }

    public void doubleTree() {
        doubleTree(root);
    }

    public boolean sameTree(BinaryTree first, BinaryTree second) {
        return sameTree(first.root, second.root);
    }

    public boolean isBST() {
        return isBST(root);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    /*
     * Given a binary tree, compute its "maxDepth" -- the number of nodes along
     * the longest path from the root node down to the farthest leaf node
     */
    private int maxDepth(Node current) {
        if (current == null) {
            return 0;
        }

        int sum1 = 1, sum2 = 1;
        sum1 = sum1 + maxDepth(current.left);
        sum2 = sum2 + maxDepth(current.right);

        return Math.max(sum1, sum2);
    }

    /*
     * Returns true if a binary tree is a binary search tree.
     */
    private boolean isBST(Node current) {
        if (current == null) {
            return true;
        }
        if ((current.left != null) && (current.data >= maxValue(current.right))) {
            return false;
        }

        if ((current.right != null) && (current.data < minValue(current.left))) {
            return false;
        }
        return isBST(current.left) && isBST(current.right);
    }

    /*
     * Given two binary trees, return true if they are structurally identical --
     * they are made of nodes with the same values arranged in the same way.
     */
    private boolean sameTree(Node first, Node second) {
        if ((first == null) && (second != null)) {
            return false;
        } else if ((first != null) && (second == null)) {
            return false;
        } else if ((first == null) && (second == null)) {
            return true;
        }

        if (first.data != second.data) {
            return false;
        }
        return sameTree(first.left, second.left) && sameTree(first.right, second.right);
    }

    /*
     * For each node in a binary search tree, create a new duplicate node, and
     * insert the duplicate as the left child of the original node.
     */
    private void doubleTree(Node current) {
        if (current == null) {
            return;
        }

        doubleTree(current.left);
        doubleTree(current.right);

        Node temp = current.left;
        current.left = new Node(current.data);
        current.left.left = temp;
    }

    /*
     * Change a tree so that the roles of the left and right pointers are
     * swapped at every node.
     */
    private void mirror(Node current) {
        if (current == null) {
            return;
        }

        Node temp = current.left;
        current.left = current.right;
        current.right = temp;

        mirror(current.left);
        mirror(current.right);
    }

    /*
     * Given a binary tree, print out all of its root-to-leaf paths, one per
     * line.
     */
    private void printPaths(Node current, int[] path, int pathLen) {
        if (current == null) {
            return;
        }

        path[pathLen] = current.data;
        pathLen++;
        if ((current.left == null) && (current.right == null)) {
            printPathsHelper(path, pathLen);
        } else {
            printPaths(current.left, path, pathLen);
            printPaths(current.right, path, pathLen);
        }

    }

    private void printPathsHelper(int[] path, int pathLen) {
        System.out.print("Path: ");
        for (int i = 0; i < pathLen; i++) {
            System.out.print(path[i] + "  ");
        }
        System.out.println();
    }

    /*
     * Given a binary tree and a sum, return true if the tree has a root-to-leaf
     * path such that adding up all the values along the path equals the given
     * sum. Return false if no such path can be found.
     */
    private boolean hasPathSum(Node current, int sum) {
        if (current == null) {
            return false;
        }

        sum = sum - current.data;
        if (sum == 0) {
            return true;
        } else if (sum < 0) {
            return false;
        }

        return hasPathSum(current.left, sum) || hasPathSum(current.right, sum);

    }

    /*
     * Given a binary tree, print out the nodes of the tree according to a
     * bottom-up "postorder" traversal -- both subtrees of a node are printed
     * out completely before the node itself is printed, and each left subtree
     * is printed before the right subtree.
     */
    private void printPostOrder(Node current) {
        if (current == null) {
            return;
        }

        printPostOrder(current.left);
        printPostOrder(current.right);
        System.out.println(current.data);
    }

    /*
     * Given a binary search tree (aka an "ordered binary tree"), iterate over
     * the nodes to print them out in increasing order
     */
    private void printTree(Node current) {
        if (current == null) {
            return;
        }
        printTree(current.left);
        System.out.println(current.data);
        printTree(current.right);
    }

    /*
     * Given a non-empty binary search tree (an ordered binary tree), return the
     * maximum data value found in that tree.
     */
    private int maxValue(Node current) {
        if (current == null) {
            return Integer.MAX_VALUE;
        }

        if (current.right == null) {
            return current.data;
        }
        return maxValue(current.right);
    }

    /*
     * Given a non-empty binary search tree (an ordered binary tree), return the
     * minimum data value found in that tree.
     */
    private int minValue(Node current) {
        if (current == null) {
            return Integer.MIN_VALUE;
        }

        if (current.left == null) {
            return current.data;
        }
        return minValue(current.left);
    }

    /*
     * This problem demonstrates simple binary tree traversal. Given a binary
     * tree, count the number of nodes in the tree.
     */
    private int calculateSize(Node current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + calculateSize(current.left) + calculateSize(current.right);
        }
    }

    /*
     * Given a binary search tree and a "target" value, search the tree to see
     * if it contains the target.
     */
    private boolean lookup(Node current, int data) {
        if (current == null) {
            return false;
        } else if (current.data == data) {
            return true;
        }

        if (data < current.data) {
            return lookup(current.left, data);
        } else if (data > current.data) {
            return lookup(current.right, data);
        }
        return false;
    }

    /*
     * Given a binary search tree and a number, insert a new node with the given
     * number into the tree in the correct place.
     */
    private Node insert(Node current, int data) {
        if (current == null) {
            current = new Node(data);
            return current;
        }

        if (data <= current.data) {
            current.left = insert(current.left, data);
        } else {
            current.right = insert(current.right, data);
        }
        return current;
    }

    private static class Node {

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
