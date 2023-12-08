import java.io.IOException;

public class DefaultMenu {

    // ρ factor for RHO compression
    private int rho;

    // QuadTree instance to hold the image
    private QuadTree newImageLambda;
    private QuadTree newImageRho;

    /**
     * Constructor to initialize DefaultMenu with an image file and ρ factor.
     *
     * @param image The path to the image file.
     * @param rho   The ρ factor for RHO compression.
     */
    public DefaultMenu(String image, int rho) {
        this.rho = rho;
        this.newImageLambda = new QuadTree(image);
        this.newImageRho = new QuadTree(image);
    }

    /**
     * Method to start the default compression process.
     * Applies Lambda compression, saves the image, then applies RHO compression and saves the image again.
     */
    public void start() {
        // Lambda compression
        System.out.println("PROCESSING LAMBDA COMPRESSION: ");
        newImageLambda.lambdaCompressTree();
         try {
               FileManager.saveQuatree((("Lambda"+ newImageLambda.getImageName().substring(0,newImageLambda.getImageName().length()- 3))) + "txt", newImageLambda.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newImageLambda.toPgm("Lambda"+newImageLambda.getImageName());
        System.out.println("COMPRESSION COMPLETED!");

        // RHO compression
        System.out.println("PROCESSING RHO COMPRESSION: ");
        System.out.println(this.rho);
        newImageRho.rhoCompressTree(this.rho);
        try {
            FileManager.saveQuatree(( ("RHO"+newImageRho.getImageName().substring(0,newImageRho.getImageName().length()- 3))) + "txt", newImageRho.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        newImageRho.toPgm("RHO"+newImageRho.getImageName());
        System.out.println("COMPRESSION COMPLETED!");
    }
}