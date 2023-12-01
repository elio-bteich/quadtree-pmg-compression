import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Util{


    /**
    * Vérifie si un nombre est une puissance de 2.
    *
    * @param nombre Le nombre à vérifier.
    * @return true si le nombre est une puissance de 2, sinon false.
    */
    private static boolean estPuissanceDeDeux(int nombre) {
        // Vérifier si le nombre est strictement positif
        if (nombre <= 0) {
            return false;
        }

        // Utiliser l'opération de bitwise pour vérifier s'il y a un seul bit positionné à 1
        return (nombre & (nombre - 1)) == 0;
    }

  /**
* Vérifie si le format d'image est correct.
*
* @param magicNumber Le numéro magique du format d'image.
* @param width La largeur de l'image.
* @param height La hauteur de l'image.
* @return true si le format est correct, sinon false.
*/
private static boolean isGoodFormat(String magicNumber, int width, int height) {
    // Variables
    boolean isGoodFormat;
    
   
    // Vérifier si toutes les conditions sont remplies
    if (magicNumber.startsWith("P2")){
        isGoodFormat = true;
        if(width == height && estPuissanceDeDeux(width) ){
            isGoodFormat = true;
        }else{
            System.out.println("taille de l'image incorrecte!");
            isGoodFormat = false;
        }
    }else{
        isGoodFormat = false;
        System.out.println("Format de fichier incorrecte!");
    }
    return isGoodFormat;
}
    /**
     * Lit le contenu d'un fichier et renvoie un tableau 2D d'entiers.
     *
     * @param Path Chemin du fichier à lire.
     * @return Un tableau 2D d'entiers, ou null en cas d'erreur de lecture du fichier.
     * @throws FileNotFoundException
     */
    public static int[][] readFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            // variables
            int width;
            int height;
            int maxLuminosity;
            int[][] tQuadtree = null;
            String magicNumber = scanner.next() ;

            if (scanner.hasNextInt()) {
                width = scanner.nextInt();
                height = scanner.nextInt();
                maxLuminosity = scanner.nextInt();

                if (isGoodFormat(magicNumber, width, height)) {
                    // initialise le tableau 2D en fonction de la taille lu dans le fichier
                    tQuadtree = new int[width][height];
                    boolean isGoodValue = true;
                    int i = 0;
                    int j = 0;
                    int nbElements = 0;
                    int value;

                    while (scanner.hasNextInt() && isGoodValue && i < width && (nbElements < (width * height))) {
                        while (j < height && isGoodValue) {
                            value = scanner.nextInt();
                            isGoodValue = (value >= 0) && (value <= maxLuminosity);
                            if (isGoodValue) {
                                tQuadtree[i][j] = value;
                                j++; 
                                nbElements++;
                            } else {
                                System.out.println("Une valeur du fichier est supérieure à la luminosité maximale");
                            }
                        }
                        j = 0; // remet le compteur colone a 0 pour la prochaine ligne
                        i++;   
                    }
                   if(nbElements < (width * height) ){
                    System.out.println("valeur manquante dans le fichier!");
                }
                }
                 
                
                
            }

            return tQuadtree;
        } catch (FileNotFoundException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            return null;
        }
    }
}
    
