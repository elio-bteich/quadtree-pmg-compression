import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileManger{


    /**
     * Checks if a given number is a power of two.
     *
     * @param number The number to check.
     * @return true if the number is a power of two, otherwise false.
     */
    private static boolean isPowerOfTwo(int number) {
        // Vérifier si le number est strictement positif
        if (number <= 0) {
            return false;
        }

        // Utiliser l'opération de bitwise pour vérifier s'il y a un seul bit positionné à 1
        return (number & (number - 1)) == 0;
    }

/**
 * Checks if the image format is correct.
 *
 * @param magicNumber The magic number of the image format.
 * @param width       The width of the image.
 * @param height      The height of the image.
 * @return true if the format is correct, otherwise false.
 */
private static boolean isGoodFormat(String magicNumber, int width, int height) {
    // Variable to store the result of the format check
    boolean isGoodFormat;

    // Check if all conditions are met
    if (magicNumber.startsWith("P2")) {
        isGoodFormat = true;
        
        // Check if width and height are equal and power of two
        if (width == height && isPowerOfTwo(width)) {
            isGoodFormat = true;
        } else {
            System.out.println("Incorrect image size!");
            isGoodFormat = false;
        }
    } else {
        isGoodFormat = false;
        System.out.println("Incorrect file format!");
    }
    
    return isGoodFormat;
}
    /**
 * Reads the content of a file and returns a 2D array of integers.
 *
 * @param path The path of the file to read.
 * @return A 2D array of integers, or null in case of a file reading error.
 * @throws FileNotFoundException If the specified file is not found.
 */
public static QuadtreePrefab loadImage(String path) {
    try (Scanner scanner = new Scanner(new File(path))) {
        // Variables to store image properties
        int width;
        int height;
        int maxLuminosity = -1 ;
        QuadtreePrefab newImage;
        int[][] tQuadtree = null; // 2D array to store image data
        boolean isGood = true; // Flag to indicate if the image loading is successful
        String magicNumber = scanner.nextLine();
        String comment = scanner.nextLine(); // Reading the comment

        // Check if the file starts with the correct magic number
        if (scanner.hasNextInt()) {
            width = scanner.nextInt();
            height = scanner.nextInt();
            maxLuminosity = scanner.nextInt();

            // Check if the file format is correct
            if (isGoodFormat(magicNumber, width, height)) {
                // Initialize the 2D array based on the size read from the file
                tQuadtree = new int[width][height];
                boolean isGoodValue = true;
                int i = 0;
                int j = 0;
                int nbElements = 0;
                int value;

                // Read values from the file and populate the 2D array
                while (scanner.hasNextInt() && isGoodValue && i < width && (nbElements < (width * height))) {
                    while (j < height && isGoodValue) {
                        value = scanner.nextInt();
                        isGoodValue = (value >= 0) && (value <= maxLuminosity);

                        if (isGoodValue) {
                            tQuadtree[i][j] = value;
                            j++;
                            nbElements++;
                            isGood = true;
                        } else {
                            System.out.println("A value in the file exceeds the maximum luminosity.");
                            isGood = false;
                        }
                    }
                    j = 0;
                    i++;
                }

                // Check if the number of elements matches the expected size
                if (nbElements < (width * height)) {
                    System.out.println("Value missing in your file!");
                    isGood = false;
                }
            } else {
                isGood = false;
            }
            
        }
        // Create a QuadtreePrefab object with the loaded data and return it
        newImage = new QuadtreePrefab(tQuadtree, isGood,maxLuminosity);
        return newImage;
    } catch (FileNotFoundException e) {
        // Handle the error if the file is not found
        System.err.println("Error reading the file: " + e.getMessage());
        return null;
    }
}

    /**
     * Saves an image represented by a QuadTree to a PGM file.
     *
     * @param image The QuadTree representing the image.
     * @param file  The path to the file where the image will be saved.
     */
    public static void SaveImage(QuadTree image ,String file ){
        String mot;
        // Calculating the height of the image using the formula 2^tmpHeight
        double tmpHeight = (double) image.getHeight();
        double height = Math.pow(2, tmpHeight);

        
        int[][] grilleTemp = new int[(int)height][(int)height];// Initializing the array to the size stored in the quadtree
        createGrilleTemp(image.getRoot(),grilleTemp, 0, 0,((int)height)- 1, ((int)height )- 1);// Filling the array with values from the quadtree

        try ( FileWriter writer = new FileWriter(new File(file))){
            // PGM file header
            writer.write("P2");
            writer.write(System.lineSeparator()); // Adding a line break
            writer.write("# version comprésser!");
            writer.write(System.lineSeparator()); 
            mot = String.valueOf((int)height);
            writer.write(mot + " "+ mot);
            writer.write(System.lineSeparator()); 
            mot = String.valueOf(255);
            writer.write(mot);
            writer.write(System.lineSeparator()); 
            for (int i = 0; i < grilleTemp.length; i++) {
                for (int j = 0; j < grilleTemp[i].length; j++) {
                    mot = String.valueOf(grilleTemp[i][j]); 
                    writer.write(mot+ " ");
                }

                writer.write(System.lineSeparator()); 
               
            }
        }catch (IOException e) {
                // Handling error in case of file writing failure
            System.err.println("Error writing to the file: : " + e.getMessage());
        }
    }


    /**
     * Recursively fills a 2D array with values from a QuadTree starting from a specified root node.
     *
     * @param root      The root node of the QuadTree.
     * @param grid      The 2D array to be filled with QuadTree values.
     * @param startLine The starting index of the row in the array.
     * @param startCol  The starting index of the column in the array.
     * @param endLine   The ending index of the row in the array.
     * @param endCol    The ending index of the column in the array.
     */
    private static void createGrilleTemp(QuadTreeNode root, int[][] grille, int startLine, int startCol, int endLine, int endCol){
        
        if (root.isLeaf()){
            // Filling the area with values from the leaf
            for(int i = startLine ; i <= endLine;i++){
                for (int j = startCol; j <= endCol;j++){
                    grille[i][j] = root.getValue();
                }
            }
        }else{
            if(root.isTwigRoot()){
                // Special case for Twig nodes
                if((endLine - startLine) == 1 && (endCol - startCol) == 1 ){
                    grille[startLine][startCol] = root.getChildValue(0);
                    grille[startLine][endCol] = root.getChildValue(1);
                    grille[endLine][endCol] = root.getChildValue(2);
                    grille[endLine][startCol] = root.getChildValue(3);
                }else{
                    // Recursive call for the four children of the Twig node
                    createGrilleTemp(root.getChild(0), grille, startLine, startCol, startLine + (endLine - startLine) / 2,
                    startCol + (endCol - startCol) / 2);
                    createGrilleTemp(root.getChild(1), grille,startLine, startCol + (endCol - startCol) / 2 + 1, startLine + (endLine - startLine) / 2,
                    endCol);
                    createGrilleTemp(root.getChild(2), grille, startLine + (endLine - startLine) / 2 + 1, startCol + (endCol - startCol) / 2 + 1,
                    endLine, endCol);
                    createGrilleTemp(root.getChild(3), grille,startLine + (endLine - startLine) / 2 + 1, startCol, endLine,
                    startCol + (endCol - startCol) / 2);
                }
            }else{
                // Recursive call for the four children of the node
                    createGrilleTemp(root.getChild(0), grille, startLine, startCol, startLine + (endLine - startLine) / 2,
                    startCol + (endCol - startCol) / 2);
                    createGrilleTemp(root.getChild(1), grille,startLine, startCol + (endCol - startCol) / 2 + 1, startLine + (endLine - startLine) / 2,
                    endCol);
                    createGrilleTemp(root.getChild(2), grille, startLine + (endLine - startLine) / 2 + 1, startCol + (endCol - startCol) / 2 + 1,
                    endLine, endCol);
                    createGrilleTemp(root.getChild(3), grille,startLine + (endLine - startLine) / 2 + 1, startCol, endLine,
                    startCol + (endCol - startCol) / 2);
            }
        }
    }
} 
