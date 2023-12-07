public class DefaultMenu {
    
    private int rho;
    private QuadTree newImage;
    


    public DefaultMenu(String image,int rho){
        this.rho = rho;
        this.newImage = new QuadTree(image);
    }


    public void start(){
        System.out.println("debut des compressions: ");
        newImage.lambdaCompressTree();
       // newImage.rhoCompressTree(rho);
    }

}
