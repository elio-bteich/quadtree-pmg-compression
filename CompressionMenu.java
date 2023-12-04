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
        System.out.println("1.Compression lambda");
        System.out.println("2.Compression RHO");
        System.out.println("Entrez le numéro de la compression que vous souhaitez appliquez: ");
    }

    public void applyCompression(){
       
        if(chooseCompression()){
            newImage.lambdaCompressTree(newImage);
        }else{
            newImage.rhoCompressTree(readRho());
        }
        Util.SaveImage(newImage);
    }

    public boolean chooseCompression(){
        Scanner scanner = new Scanner(System.in);
        String answer;

        answer = scanner.nextLine();
        while(answer != "1" && answer != "2"){
            System.out.println("Entrez le numéro de la compression que vous souhaitez appliquez: : ");
            answer = scanner.nextLine();
        }
        return answer == "1";
    }


    public int readRho(){
        Scanner scanner = new Scanner(System.in);
        int rho = scanner.nextInt();

         System.out.println("Entrez la valeur fu facteur ρ (compris entre 1 et 100 inclus): ");
        while(rho < 0 && rho > 100){
            System.out.println(" ρ doit etre compris entre 1 et 100: ");
            rho = scanner.nextInt();
        }
        return rho;
    }
    
}