import java.util.Scanner;

public class CompressionMenu {

    private QuadTree newImage;
    

    public CompressionMenu(QuadTree Q){
        this.newImage = Q;
    }

    public void start(){
        displayCompMenu();
        applyCompression();
    }

    public void displayCompMenu(){
        System.out.println("choose compression by enterring the number before (1 or 2 )?");
        System.out.println("1.Lambda compression");
        System.out.println("2.RHO compression ");
    }

    public void applyCompression(){
        if(chooseCompression()){
            System.out.println("PROCESSING LAMBDA COMPRESSION: ");
            newImage.lambdaCompressTree();
            Util.SaveImage(newImage, "testCompression.pgm");
        }else{
            System.out.println("PROCESSING RHO COMPRESSION: ");
            newImage.rhoCompressTree(readRho());
            Util.SaveImage(newImage, "testCompression.pgm");
        }
        System.out.println("COMPRESSION COMPLETETD!");
    }

    public boolean chooseCompression() {
       
        Scanner scanner = new Scanner(System.in) ;
            int answer = scanner.nextInt();
            
            while (answer != 1  && answer != 2) {
                
                System.out.println("ERROR: INVALID INPUT! Enter 1 OR 2 : ");
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            return answer == 1;
        
    }

    public int readRho(){
        System.out.println("Please Enter the factor ρ for the RHO compression : ");

        Scanner scanner = new Scanner(System.in);
            int rho = scanner.nextInt();
            scanner.nextLine();

            while(rho < 1 || rho > 100){
                System.out.println("ERROR: INVALID INPUT! ρ value must be between 1 and 100: ");
                rho = scanner.nextInt();
                scanner.nextLine();
            }
            scanner.close();
            return rho;
    }
    
}