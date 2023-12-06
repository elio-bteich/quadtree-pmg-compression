import java.util.Scanner;

public class CompressionMenu {

    // QuadTree instance to hold the image
    private QuadTree newImage;

    /**
     * Constructor to initialize the CompressionMenu with a QuadTree.
     *
     * @param Q The QuadTree representing the image.
     */
    public CompressionMenu(QuadTree Q) {
        this.newImage = Q;
    }

    /**
     * Method to start the compression menu.
     */
    public void start() {
        displayCompMenu();
        applyCompression();
    }

    /**
     * Method to display the compression menu options.
     */
    public void displayCompMenu() {
        System.out.println("Choose compression by entering the number before (1 or 2):");
        System.out.println("1. Lambda compression");
        System.out.println("2. RHO compression");
    }

    /**
     * Method to apply compression based on user input.
     */
    public void applyCompression() {
        if (chooseCompression()) {
            System.out.println("PROCESSING LAMBDA COMPRESSION: ");
            newImage.lambdaCompressTree();
            FileManger.SaveImage(newImage, "testCompression.pgm");
        } else {
            System.out.println("PROCESSING RHO COMPRESSION: ");
            newImage.rhoCompressTree(readRho());
            FileManger.SaveImage(newImage, "testCompression.pgm");
        }
        System.out.println("COMPRESSION COMPLETED!");
    }

    /**
     * Method to prompt the user to choose compression type.
     *
     * @return true if Lambda compression is chosen, false if RHO compression is chosen.
     */
    public boolean chooseCompression() {
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();

        while (answer != 1 && answer != 2) {
            System.out.println("ERROR: INVALID INPUT! Enter 1 OR 2: ");
            answer = scanner.nextInt();
            scanner.nextLine();
        }
        return answer == 1;
    }

    /**
     * Method to read and validate the ρ factor for RHO compression.
     *
     * @return The validated ρ factor for RHO compression.
     */
    public int readRho() {
        System.out.println("Please Enter the factor ρ for the RHO compression: ");

        Scanner scanner = new Scanner(System.in);
        int rho = scanner.nextInt();
        scanner.nextLine();

        while (rho < 1 || rho > 100) {
            System.out.println("ERROR: INVALID INPUT! ρ value must be between 1 and 100: ");
            rho = scanner.nextInt();
            scanner.nextLine();
        }
        scanner.close();
        return rho;
    }
}