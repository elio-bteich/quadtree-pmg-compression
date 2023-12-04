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
        System.out.println("Quel compression souhaitez vous appliquez?");
        System.out.println("1.Compression lambda");
        System.out.println("2.Compression RHO");
        System.out.println("Entrez le numéro de la compression que vous souhaitez appliquez: ");
    }

    public void applyCompression(){
        if(chooseCompression()){
            newImage.lambdaCompressTree(newImage);
        }else{
            //newImage.rhoCompressTree(readRho());
        }
    }

    public boolean chooseCompression() {
       
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = 0;
            if(scanner.hasNextInt()){
                answer = scanner.nextInt();
            }
            
    
            while (answer != 1  && answer != 2) {
                scanner.next();
                if(scanner.hasNextInt()){
                System.out.println("Numéro de compression invalide. Entrez 1 ou 2 : ");
                answer = scanner.nextInt();
            }

                
            }
            return answer == 1;
        }
    }

    public int readRho(){
        try (Scanner scanner = new Scanner(System.in)) {
            int rho = scanner.nextInt();

             System.out.println("Entrez la valeur fu facteur ρ (compris entre 1 et 100 inclus): ");
            while(rho < 0 && rho > 100){
                System.out.println(" ρ doit etre compris entre 1 et 100: ");
                rho = scanner.nextInt();
            }
            scanner.close();
            return rho;
        }
    }
    
}