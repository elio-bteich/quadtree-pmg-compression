/**
 * Class that implements the test cases for our compression
 * 
 */
public class QuadTreeTest {

    public static void main(String[] args) {
        //testQuadTreeConstruction();
        //testLambdaCompressTree();
        //testRhoCompressTree();
        //testImageCompression();
        testImageConstruction();

       // Replace "path/to/your/file.txt" with the actual file path you want to test
        //String filePath = "test.txt";

        // Test the readfile method
        //testReadFile(filePath);
    }

    private static void testQuadTreeConstruction() {
        int[][] image = {
            {1, 1, 1 ,1 ,4 ,4 ,5 ,4},
            {1 ,1 ,2 ,2 ,5 ,5 ,5 ,5},
            {3 ,3 ,3 ,3 ,2, 2 ,2 ,2},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1}
        };

        QuadTree quadTree = new QuadTree(image);
        QuadTreeNode root = quadTree.getRoot();
        printQuadTree(root, 2);
        assert (root != null);
        assert (quadTree.getHeight() == 2);
    }

   
    private static void testLambdaCompressTree() {
        int[][] image = {
            {1, 1, 1 ,1 ,4 ,4 ,5 ,4},
            {1 ,1 ,2 ,2 ,5 ,5 ,5 ,5},
            {3 ,3 ,3 ,3 ,2, 2 ,2 ,2},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1},
            {1, 2 ,2 ,3 ,3 ,1 ,1 ,1}
        };

        QuadTree quadTree = new QuadTree(image);

        // Before compression
        System.out.println("Before Compression:");
        printQuadTree(quadTree.getRoot(), 0);

        // Compress the tree
        quadTree.lambdaCompressTree();

        // After compression
        System.out.println("\nAfter Compression:");
        printQuadTree(quadTree.getRoot(), 0);
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

    private static void testRhoCompressTree() {
    
        // Test with a low compression ratio, expecting minimal compression
        int[][] lowCompressionImage = {
            {1, 1, 1 ,1, 56, 199, 12, 45},
            {1 ,1 ,1 ,1, 76, 42, 86, 12},
            {2 ,3 ,4 ,5, 23, 87, 14, 48},
            {5, 4, 7 ,6, 65, 34, 21, 62},
            {23, 14, 78 ,25, 65, 34, 21, 62},
            {48, 144, 37 ,63, 45, 2, 53, 4},
            {52, 73, 71 ,53, 65, 3, 21, 6},
            {1, 42, 133 ,2, 6, 34, 58, 98}
        };
        QuadTree lowCompressionQuadTree = new QuadTree(lowCompressionImage);
        int initialNodesLow = lowCompressionQuadTree.getNbNodes();
        lowCompressionQuadTree.rhoCompressTree(0.8);
        System.out.println("Custom Compression Result:");
        System.out.println("Initial Nodes: " + initialNodesLow);
        System.out.println("Final Nodes: " + lowCompressionQuadTree.getNbNodes());
        System.out.println("Tree structure after compression:");
        printQuadTree(lowCompressionQuadTree.getRoot(), 0);
        System.out.println("-------------------------------");
    }

    private static void testImageConstruction() {

        int[][] image = Util.loadImage("train.pgm");

        QuadTree tree = new QuadTree(image);
        tree.lambdaCompressTree();
        printQuadTree(tree.getRoot(), 0);
        System.out.println("-------------------------------");
    }
    
    private static void testImageCompression() {

        int[][] image = Util.loadImage("train.pgm");

        QuadTree lowCompressionQuadTree = new QuadTree(image);
        int initialNodesLow = lowCompressionQuadTree.getNbNodes();
        lowCompressionQuadTree.lambdaCompressTree();
        System.out.println("Custom Compression Result:");
        System.out.println("Initial Nodes: " + initialNodesLow);
        System.out.println("Final Nodes: " + lowCompressionQuadTree.getNbNodes());
        System.out.println("Tree structure after compression:");
        printQuadTree(lowCompressionQuadTree.getRoot(), 0);
        System.out.println("-------------------------------");
    }
    
}
