public class DefaultMenu {

    // ρ factor for RHO compression
    private int rho;

    // QuadTree instance to hold the image
    private QuadTree newImage;

    /**
     * Constructor to initialize DefaultMenu with an image file and ρ factor.
     *
     * @param image The path to the image file.
     * @param rho   The ρ factor for RHO compression.
     */
    public DefaultMenu(String image, int rho) {
        this.rho = rho;
        // Load the image, create a QuadTree, and initialize newImage
        this.newImage = new QuadTree(FileManger.loadImage(image).getTableau());
    }

    /**
     * Method to start the default compression process.
     * Applies Lambda compression, saves the image, then applies RHO compression and saves the image again.
     */
    public void start() {
        // Lambda compression
        System.out.println("PROCESSING LAMBDA COMPRESSION: ");
        newImage.lambdaCompressTree();
        FileManger.SaveImage(newImage, "testCompression1.pgm");
        System.out.println("COMPRESSION COMPLETED!");

        // RHO compression
        System.out.println("PROCESSING RHO COMPRESSION: ");
        newImage.rhoCompressTree(rho);
        FileManger.SaveImage(newImage, "testCompression2.pgm");
        System.out.println("COMPRESSION COMPLETED!");
    }
}