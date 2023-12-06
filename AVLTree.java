public class AVLTree {
    private AVLNode root;

    public void insert(double epsilon, QuadTreeNode quadNode) {
        root = insert(root, epsilon, quadNode);
    }

    private AVLNode insert(AVLNode node, double epsilon, QuadTreeNode quadNode) {
        if (node == null) {
            return new AVLNode(epsilon, quadNode);
        }

        if (epsilon < node.epsilon) {
            node.left = insert(node.left, epsilon, quadNode);
        } else if (epsilon > node.epsilon) {
            node.right = insert(node.right, epsilon, quadNode);
        } else {
            return node;
        }

        updateHeight(node);

        return balance(node);
    }

    public void print() {
        print(root, 0);
    }

    private void print(AVLNode node, int level) {
        if (node != null) {
            print(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("   ");
            }
            System.out.println(node.epsilon + " (Balance: " + node.bal + ")");
            print(node.left, level + 1);
        }
    }

    public void delete(double epsilon) {
        root = delete(root, epsilon);
    }

    private AVLNode delete(AVLNode node, double epsilon) {
        if (node == null) {
            return null;
        }

        if (epsilon < node.epsilon) {
            node.left = delete(node.left, epsilon);
        } else if (epsilon > node.epsilon) {
            node.right = delete(node.right, epsilon);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.epsilon = temp.epsilon;
                node.right = delete(node.right, temp.epsilon);
            }
        }

        if (node == null) {
            return null;
        }

        updateHeight(node);

        return balance(node);
    }

    public QuadTreeNode getQuadTreeNodeByEpsilon(double epsilon) {
        return getQuadTreeNodeByEpsilon(root, epsilon);
    }

    private QuadTreeNode getQuadTreeNodeByEpsilon(AVLNode node, double epsilon) {
        if (node == null) {
            return null;
        }

        if (epsilon < node.epsilon) {
            return getQuadTreeNodeByEpsilon(node.left, epsilon);
        } else if (epsilon > node.epsilon) {
            return getQuadTreeNodeByEpsilon(node.right, epsilon);
        } else {
            // Epsilon matches, return the associated QuadTreeNode
            return node.quadNode;
        }
    }

    public AVLNode findMin(AVLNode node) {
        
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }
        
        return node;
    }

    public boolean search(double epsilon) {
        return search(root, epsilon);
    }

    private boolean search(AVLNode node, double epsilon) {
        if (node == null) {
            return false;
        }

        if (epsilon == node.epsilon) {
            return true;
        } else if (epsilon < node.epsilon) {
            return search(node.left, epsilon);
        } else {
            return search(node.right, epsilon);
        }
    }

    private int height(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.bal = balanceFactor(node);
    }
    

    private int balanceFactor(AVLNode node) {
        return (node != null) ? height(node.right) - height(node.left) : 0;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        x.right = y.left;
        y.left = x;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        y.left = x.right;
        x.right = y;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode doubleRotateLeft(AVLNode x) {
        x.right = rotateRight(x.right);
        return rotateLeft(x);
    }

    private AVLNode doubleRotateRight(AVLNode y) {
        y.left = rotateLeft(y.left);
        return rotateRight(y);
    }

    private AVLNode balance(AVLNode node) {
        int bal = balanceFactor(node);

        if (bal > 1) {
            if (balanceFactor(node.right) < 0) {
                return doubleRotateLeft(node);
            } else {
                return rotateLeft(node);
            }
        } else if (bal < -1) {
            if (balanceFactor(node.left) > 0) {
                return doubleRotateRight(node);
            } else {
                return rotateRight(node);
            }
        }

        return node;
    }

    public AVLNode getRoot() {
        return this.root;
    }
}