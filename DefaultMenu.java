public class DefaultMenu {
    
    private int rho;
    private QuadTree newImage;
    


    public DefaultMenu(String image,int rho){
        this.rho = rho;
        this.newImage = new QuadTree(Util.loadImage(image));
    }


    public void start(){
        newImage.lambdaCompressTree(newImage);
       // newImage.rhoCompressTree(rho);
    }

}
