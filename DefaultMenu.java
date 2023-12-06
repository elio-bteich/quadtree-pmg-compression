public class DefaultMenu {
    
    private int rho;
    private QuadTree newImage;
    


    public DefaultMenu(String image,int rho){
        this.rho = rho;
        this.newImage = new QuadTree(Util.loadImage(image).getTableau());
    }


    public void start(){
        System.out.println("PROCESSING LAMBDA COMPRESSION: ");
        newImage.lambdaCompressTree();
        Util.SaveImage(newImage, "testCompression1.pgm");
        System.out.println("COMPRESSION COMPLETETD!");
        System.out.println("PROCESSING RHO COMPRESSION: ");
        newImage.rhoCompressTree(rho);
        Util.SaveImage(newImage, "testCompression2.pgm");
        System.out.println("COMPRESSION COMPLETETD!");
    }

}
