/**
 * Class that implements the test cases for our compression
 * 
 */
public class QuadTreeTest {

    public static void main(String[] args) {
        testQuadTreeConstruction();
       // testLambdaCompressTree();
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
            {1,2 ,2 ,3 ,3 ,1 ,1 ,1 ,1}
        };

        QuadTree quadTree = new QuadTree(image);
        Node root = quadTree.getRoot();
        printQuadTree(root, 1);
        assert (root != null);
        assert (quadTree.getHeight() == 2);
    }

   
    private static void testLambdaCompressTree() {
        int[][] image = {
            {0, 1, 4, 5},
            {2, 3, 6, 7},
            {8, 9, 12, 13},
            {10, 11, 14, 15}
        };

        QuadTree quadTree = new QuadTree(image);

        // Before compression
        System.out.println("Before Compression:");
        printQuadTree(quadTree.getRoot(), 0);

        // Compress the tree
        quadTree.lambdaCompressTree(quadTree);

        // After compression
        System.out.println("\nAfter Compression:");
        printQuadTree(quadTree.getRoot(), 0);
    }

    private static void printQuadTree(Node node, int depth) {
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
}
