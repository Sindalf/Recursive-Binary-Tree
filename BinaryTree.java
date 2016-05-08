
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

    private void printPostOrder(Node current) {
        if (current == null) {
            return;
        }

        printPostOrder(current.left);
        printPostOrder(current.right);
        System.out.println(current.data);
    }

    private void printTree(Node current) {
        if (current == null) {
            return;
        }
        printTree(current.left);
        System.out.println(current.data);
        printTree(current.right);
    }

    private int maxValue(Node current) {
        if (current == null) {
            return Integer.MAX_VALUE;
        }

        if (current.right == null) {
            return current.data;
        }
        return maxValue(current.right);
    }

    private int minValue(Node current) {
        if (current == null) {
            return Integer.MIN_VALUE;
        }

        if (current.left == null) {
            return current.data;
        }
        return minValue(current.left);
    }

    private int calculateSize(Node current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + calculateSize(current.left) + calculateSize(current.right);
        }
    }

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
