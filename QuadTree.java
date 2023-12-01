/**
 * Class that represents a quadtree of a PMG file
 * 
 */
public class QuadTree {

    /**
     * The root attribute of this quadtree
     * 
     */
    private Node root;

    /**
     * The height of the quadtree which is the log2 of the image side length.
     * We can get the image side length by doing 2^height
     * This height is used to reconstruct the image file
     * 
     */
    private int height;

    /**
     * Construct quadtree from 2d array representation of the image
     * 
     * @param arr The 2d representation of the image
     * 
     */
    public QuadTree(int[][] arr) {

        this.root = new Node();
        this.height = (int) (Math.log(arr.length) / Math.log(2));

        constructQuadtree(root, arr, 0, 0, arr.length - 1, arr.length - 1);
    }

    /**
     * Construct quadtree from the 2D array representation of the image
     * 
     * @param node      The root node of the quadtree
     * @param arr       The 2D array that represents the image file body
     * @param startLine The starting line index of the image in the array
     * @param startCol  The starting column index of the image in the array
     * @param endLine   The ending line index of the image in the array
     * @param endCol    The ending column index of the image in the array
     * 
     */
    public void constructQuadtree(Node node, int[][] arr, int startLine, int startCol, int endLine, int endCol) {

        if ((endLine - startLine) == 1 && (endCol - startCol) == 1) {
            // The values of the twig's leaves are identical
            if (arr[startLine][startCol] == arr[startLine][endCol]
                    && arr[startLine][startCol] == arr[endLine][endCol]
                    && arr[startLine][startCol] == arr[endLine][startCol]) {
                node.setValue(arr[startLine][startCol]);
            }

            else {
                node.createChildren();
                node.setChildValue(0, arr[startLine][startCol]);
                node.setChildValue(1, arr[startLine][endCol]);
                node.setChildValue(2, arr[endLine][endCol]);
                node.setChildValue(3, arr[endLine][startCol]);
            }

        }

        else {
            node.createChildren();
            constructQuadtree(node.getChild(0), arr, startLine, startCol, (endLine - startLine) / 2,
                    (endCol - startCol) / 2);
            constructQuadtree(node.getChild(1), arr, startLine, (endCol - startCol) / 2 + 1, (endLine - startLine) / 2,
                    endCol);
            constructQuadtree(node.getChild(2), arr, (endLine - startLine) / 2 + 1, (endCol - startCol) / 2 + 1,
                    endLine, endCol);
            constructQuadtree(node.getChild(3), arr, (endLine - startLine) / 2 + 1, startCol, endLine,
                    (endCol - startCol) / 2);
        }
    }

    /**
     * Compress the quadtree with lambda method
     * 
     * @param tree the tree to compress
     * 
     */
    public void lambdaCompressTree(QuadTree tree) {
        lambdaCompressTree_(tree.root);
    }

    /**
     * Compress the quadtree with lambda method
     * 
     * @param node the node of the tree to compress, gonna change recursively to
     *             iterate through all the nodes of the tree
     * 
     */
    private void lambdaCompressTree_(Node node) {
        if (node.isTwigRoot()) {
            lambdaCompressTwig(node);
        } else {
            lambdaCompressTree_(node.getChild(0));
            lambdaCompressTree_(node.getChild(1));
            lambdaCompressTree_(node.getChild(2));
            lambdaCompressTree_(node.getChild(3));
        }
    }

    /**
     * Compress a twig by using the lambda compression algorithm
     * 
     * @param twig the twig that we want to compress
     * 
     */
    private static void lambdaCompressTwig(Node twigRoot) {
        double res = 0;

        for (int i = 0; i < 4; i++) {
            res += Math.log(0.1 + twigRoot.getChildValue(i));
        }

        res = Math.round(Math.exp(0.25 * res));

        twigRoot.destroyChildren();
        twigRoot.setValue((int)res);
    }

    /**
     * Get the root of this quadtree
     * 
     * @return the root node of this quadtree
     * 
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Set the root of this quadtree
     * 
     * @param root the node that we want to set as the root of this quadtree
     * 
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Get the height of this quadtree
     * 
     * @return the height of this quadtree
     * 
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Set the height of this quadtree
     * 
     * @param height the height of this quadtree
     *
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
