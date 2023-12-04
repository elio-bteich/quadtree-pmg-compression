public class DefaultMenu {
    
    private int rho;
    private QuadTree newImage;
    


    public DefaultMenu(String image,int rho){
        this.rho = rho;
        this.newImage = new QuadTree(Util.readFile(image));
    }


    public void start(){
        newImage.lambdaCompressTree(newImage);
        Util.SaveImage(newImage);
        newImage.rhoCompressTree();
        Util.SaveImage(newImage);
    }

}
