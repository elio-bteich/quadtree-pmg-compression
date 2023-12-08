/**
 * Class that implements the test cases for our compression
 * 
 */
public class QuadTreeTest {

    public static void main(String[] args) {
        //testImageCompression();
        testImageConstruction();
    }

    private static void printQuadTree(QuadTreeNode node, int depth) {
        if (node != null) {
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println("Value: " + node.getValue());

            if (!node.isLeaf()) {
                printQuadTree(node.getChild(0), depth + 1);
                printQuadTree(node.getChild(1), depth + 1);
                printQuadTree(node.getChild(2), depth + 1);
                printQuadTree(node.getChild(3), depth + 1);
            }
        }
    }

    private static void testImageConstruction() {

        QuadTree tree = new QuadTree("tree.pgm");
        printQuadTree(tree.getRoot(), 0);
        System.out.println("-------------------------------");
    }
    
    private static void testImageCompression() {

        QuadTree lowCompressionQuadTree = new QuadTree("tree.pmg");
        int initialNodesLow = lowCompressionQuadTree.getNbNodes();
        lowCompressionQuadTree.rhoCompressTree(50);
        System.out.println("Custom Compression Result:");
        System.out.println("Initial Nodes: " + initialNodesLow);
        System.out.println("Final Nodes: " + lowCompressionQuadTree.getNbNodes());
        System.out.println("Tree structure after compression:");
        printQuadTree(lowCompressionQuadTree.getRoot(), 0);
        System.out.println("-------------------------------");
    }
    
}
