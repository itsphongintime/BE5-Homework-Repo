package BalancedBST1stTry;

public class MyTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public MyTree() {
        root = null;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = x.left.isRed;
        x.left.isRed = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = x.right.isRed;
        x.right.isRed = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.isRed = !h.isRed;
        if (h.left != null) {
        	h.left.isRed = !h.left.isRed;
        }
        if (h.right != null) {
        	h.right.isRed = !h.right.isRed;
        }
    }

    public void addNode(int value) {
        root = addNode(root, value);
        root.isRed = BLACK;
    }

    private Node addNode(Node h, int value) {
        if (h == null) {
            return new Node(value);
        }
        if (value < h.value) {
            h.left = addNode(h.left, value);
        } else if (value > h.value) {
            h.right = addNode(h.right, value);
        } else {
            return h;
        }

        if (isRed(h.right) && !isRed(h.left)) {
        	h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
        	h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
        	flipColors(h);
        }

        return h;
    }

    private boolean isRed(Node node) {
        if (node == null) {
        	return false;
        }
        return node.isRed;
    }
}
