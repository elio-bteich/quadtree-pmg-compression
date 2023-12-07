import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private ArrayList<String> images;
    private QuadTree compressedQuadtree;
    private CompressionMenu compMenu;


    public MainMenu(ArrayList<String> images){
        this.images = images;
    }

    public void start(){
        displayImages();
        compressedQuadtree = new QuadTree(images.get(readAnswer()));
        System.out.println("construction de l'arbre ok ");
        compMenu = new CompressionMenu(compressedQuadtree);
        compMenu.start();
    }


    public int readAnswer(){
         Scanner scanner = new Scanner(System.in);
            int answer;

            answer = scanner.nextInt();
            scanner.nextLine();
            while(answer < 1 || answer > images.size()){
                System.out.println("Entrez le numéro se trouvant devant l'image : ");
                answer = scanner.nextInt();
                scanner.nextLine();
            }
            
            return answer-1;
        
    }
    public void displayImages(){
        for (int i = 0 ;i < images.size();i++){
            System.out.println(i+1 + ". " + images.get(i));
        }
        System.out.println("Entrez le numéro de l'image à charger : ");
    }
    
}
    