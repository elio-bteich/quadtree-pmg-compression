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
            System.out.println("debut de la compression lambda: ");
            newImage.lambdaCompressTree(newImage);
        }else{
            System.out.println("debut de la compression rho: ");
            int rho = readRho();
            //newImage.rhoCompressTree(readRho());
        }
    }

    public boolean chooseCompression() {
       
        Scanner scanner = new Scanner(System.in) ;
            int answer = scanner.nextInt();
            
            while (answer != 1  && answer != 2) {
                
                System.out.println("Numéro de compression invalide. Entrez 1 ou 2 : ");
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            return answer == 1;
        
    }

    public int readRho(){
        System.out.println("Entrez la valeur du facteur ρ (compris entre 1 et 100 inclus): ");

        Scanner scanner = new Scanner(System.in);
            int rho = scanner.nextInt();
            scanner.nextLine();

            while(rho < 1 || rho > 100){
                System.out.println(" ρ doit etre compris entre 1 et 100: ");
                rho = scanner.nextInt();
                scanner.nextLine();
            }
            scanner.close();
            return rho;
    }
    
}