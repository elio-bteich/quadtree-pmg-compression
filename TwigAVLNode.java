import java.util.ArrayList;

/**
 * Represents a node in the AVL tree used for storing Twigs.
 * 
 */
class TwigAVLNode {

    /**
     * The epsilon value associated with the QuadTreeNode.
     * 
     */
    double epsilon;

    /**
     * Reference to the left child node.
     * 
     */
    TwigAVLNode left;

    /**
     * Reference to the right child node.
     * 
     */
    TwigAVLNode right;

    /**
     * Height of the node in the AVL tree.
     * 
     */
    int height;

    /**
     * Balance factor of the node in the AVL tree.
     * 
     */
    int bal;

    /**
     * List of references to the quadtree nodes associated with this AVL node.
     * In other terms, this list has all the twig roots of the quadtree that has the same epsilon
     * 
     */
    ArrayList<QuadTreeNode> quadNodes;

    /**
     * Constructs a new TwigAVLNode with the specified epsilon and QuadTreeNode.
     *
     * @param epsilon  The epsilon value associated with the QuadTreeNode.
     * @param quadNode The QuadTreeNode associated with this AVL node.
     * 
     */
    public TwigAVLNode(double epsilon, QuadTreeNode quadNode) {
        this.epsilon = epsilon;
        this.height = 1;
        this.bal = 0;
        this.left = null;
        this.right = null;
        this.quadNodes = new ArrayList<>();
        this.quadNodes.add(quadNode);    
    }
}
