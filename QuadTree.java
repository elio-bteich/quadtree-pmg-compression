/**
 * Class that represents a quadtree of a PMG file
 * 
 */
public class QuadTree {

    /**
     * The twigs avl, it's gonna stay empty except when doing the dynamic compression
     * It's gonna be used by the dynamic compression to store the twigs with their epsilon
     * to be able to determine in a efficient way with a logarithmic complexity which twig 
     * has the less luminosity difference to give it compression priority
     * 
     */
    AVLTree twigs;

    /**
     * The root attribute of this quadtree
     * 
     */
    private QuadTreeNode root;

    /**
     * The height of the quadtree which is the log2 of the image side length.
     * We can get the image side length by doing 2^height
     * This height is used to reconstruct the image file
     * 
     */
    private int height;

    private static final int UNDEFINED_LOG_LUMINOSITY = Integer.MIN_VALUE;

    /**
     * Construct quadtree from 2d array representation of the image
     * 
     * @param arr The 2d representation of the image
     * 
     */
    public QuadTree(int[][] arr) {
        this.root = new QuadTreeNode();
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
    public void constructQuadtree(QuadTreeNode node, int[][] arr, int startLine, int startCol, int endLine, int endCol) {

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
            constructQuadtree(node.getChild(0), arr, startLine, startCol, startLine + (endLine - startLine) / 2,
            startCol + (endCol - startCol) / 2);
            constructQuadtree(node.getChild(1), arr, startLine, startCol + (endCol - startCol) / 2 + 1, startLine + (endLine - startLine) / 2,
                    endCol);
            constructQuadtree(node.getChild(2), arr, startLine + (endLine - startLine) / 2 + 1, startCol + (endCol - startCol) / 2 + 1,
                    endLine, endCol);
            constructQuadtree(node.getChild(3), arr, startLine + (endLine - startLine) / 2 + 1, startCol, endLine,
                    startCol + (endCol - startCol) / 2);

        }
    }

    /**
     * Compress the quadtree with lambda method
     * 
     * @param tree the tree to compress
     * 
     */
    public void lambdaCompressTree(QuadTree tree) {
        lambdaCompressTree(tree.root);
    }

    /**
     * Compress the quadtree with lambda method
     * 
     * @param node the node of the tree to compress, gonna change recursively to
     *             iterate through all the nodes of the tree
     * 
     */
    private void lambdaCompressTree(QuadTreeNode node) {
        if (!node.isLeaf()) {
            if (node.isTwigRoot()) {
            lambdaCompressTwig(node);
            } else {
                lambdaCompressTree(node.getChild(0));
                lambdaCompressTree(node.getChild(1));
                lambdaCompressTree(node.getChild(2));
                lambdaCompressTree(node.getChild(3));
            } 
        }
    }

    /**
     * Compress a twig by using the lambda compression algorithm
     * 
     * @param twig the twig that we want to compress
     * 
     */
    public static void lambdaCompressTwig(QuadTreeNode twigRoot) {
        twigRoot.destroyChildren();
        twigRoot.setValue(calculateAvgLogLuminosity(twigRoot));
    }

    /**
     * Méthode pour compresser le quadtree avec l'algorithme Rho.
     * 
     * @param rho La valeur de ρ pour la compression Rho.
     */
    public void rhoCompressTree(double rho) {
        twigs = new AVLTree();
        detectCompressableTwigs(root);
        twigs = null;
    }


    /**
     * Méthode récursive pour compresser le quadtree avec l'algorithme Rho.
     * 
     * @param node Le nœud du quadtree à compresser.
     */
    private void detectCompressableTwigs(QuadTreeNode node) {
        if (node != null && !node.isLeaf()) {
            if (node.isTwigRoot()) {
                double epsilon = calculateEpsilon(node);
                this.twigs.insert(epsilon, node);
            } else {
                for (int i = 0; i < 4; i++) {
                    detectCompressableTwigs(node.getChild(i));
                }
            }
        }
    }

    private static int calculateAvgLogLuminosity(QuadTreeNode node) {
        
        if (node.isTwigRoot()) {
            double res = 0;

            for (int i = 0; i < 4; i++) {
                res += Math.log(0.1 + node.getChildValue(i));
            }

            res = Math.round(Math.exp(0.25 * res));
            return (int)res;
        }

        return UNDEFINED_LOG_LUMINOSITY;
    }

    private static double calculateEpsilon(QuadTreeNode node) {
    
        double maxEpsilon = Double.MIN_VALUE;
    
        for (int i = 0; i < 4; i++) {
            QuadTreeNode child = node.getChild(i);
    
            if (child != null && child.isLeaf()) {

                double avgLogLuminosity = calculateAvgLogLuminosity(node);
    
                double epsilon = Math.abs(avgLogLuminosity - child.getValue());
    
                maxEpsilon = Math.max(maxEpsilon, epsilon);
            }
        }
    
        return maxEpsilon;
    }

    /**
     * Get the root of this quadtree
     * 
     * @return the root node of this quadtree
     * 
     */
    public QuadTreeNode getRoot() {
        return this.root;
    }

    /**
     * Set the root of this quadtree
     * 
     * @param root the node that we want to set as the root of this quadtree
     * 
     */
    public void setRoot(QuadTreeNode root) {
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
